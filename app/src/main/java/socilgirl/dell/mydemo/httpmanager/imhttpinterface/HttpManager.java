package socilgirl.dell.mydemo.httpmanager.imhttpinterface;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.body.UIProgressResponseCallBack;
import com.zhouyou.http.cache.model.CacheMode;
import com.zhouyou.http.cache.model.CacheResult;
import com.zhouyou.http.callback.CallBack;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.HttpParams;
import com.zhouyou.http.request.GetRequest;
import com.zhouyou.http.request.PostRequest;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import socilgirl.dell.mydemo.httpmanager.DownData;
import socilgirl.dell.mydemo.httpmanager.HttpManagerConstant;
import socilgirl.dell.mydemo.httpmanager.RxEasManager;
import socilgirl.dell.mydemo.httpmanager.callback.IRequestCallback;
import socilgirl.dell.mydemo.httpmanager.httpinterface.IDataParameter;
import socilgirl.dell.mydemo.httpmanager.httpinterface.IDowmLoadParameter;
import socilgirl.dell.mydemo.httpmanager.httpinterface.IHttpManager;
import socilgirl.dell.mydemo.httpmanager.httpinterface.IParameters;
import socilgirl.dell.mydemo.httpmanager.httpinterface.IRequestTask;
import socilgirl.dell.mydemo.httpmanager.utils.HttpUtils;


/**
 * Created by ${小强} on 2017/10/26.
 */

public class HttpManager implements IHttpManager {
    private static HttpManager ourInstance;
    private Context mContext;

    public static HttpManager getInstance() {
        if (ourInstance == null) {
            synchronized (HttpManager.class) {
                if (ourInstance == null) {
                    ourInstance = new HttpManager();
                }
            }
        }
        return ourInstance;
    }

    private HttpManager() {
    }


    @Override
    public void init(Application context) {
        EasyHttp.init(context);
        mContext = context;
        EasyHttp.getInstance().setBaseUrl("https://api.zaiwaner.cn/1.0.1/");
    }

    @Override
    public void inspectContent() {
        if (mContext == null) {
            throw new RuntimeException(HttpManagerConstant.INITANAGER);
        }
    }

    @Override
    public <T> IRequestTask get(IParameters parameters, IRequestCallback<T> callback) {
        inspectContent();
        try {
            callback.onStart();
            return toGet(parameters, callback);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onError(HttpManagerConstant.ERRORCODE, e.getMessage() + "", e);
        }
        return null;
    }


    @Override
    public <T> IRequestTask post(IParameters parameters, IRequestCallback<T> callback) {
        inspectContent();
        try {
            callback.onStart();
            return toPost(parameters, callback);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onError(HttpManagerConstant.ERRORCODE, e.getMessage() + "", e);
        }
        return null;
    }

    @Override
    public IRequestTask downLoad(IDowmLoadParameter parameter, final IRequestCallback callback) {
        inspectContent();
        Disposable execute = EasyHttp.downLoad(parameter.getDownLoadUrl())
                .savePath(parameter.getFilePath())
                .saveName(parameter.getFlieName())
                .execute(new DownloadProgressCallBack<String>() {
                    @Override
                    public void update(long bytesRead, long contentLength, boolean done) {
                        int progress = (int) (bytesRead * 100 / contentLength);
                        if (done) {//下载完成
                            progress = 100;
                        }
                        callback.onProgress(progress);
                    }

                    @Override
                    public void onComplete(String path) {
                        callback.onSuccess(path, false);
                    }

                    @Override
                    public void onStart() {
                        callback.onStart();
                    }

                    @Override
                    public void onError(ApiException e) {
                        callback.onError(e.getCode(), e.getMessage(), e);
                    }
                });

        return new RequestTast(execute);
    }


    @Override
    public <T> IRequestTask postData(IDataParameter parameter, IRequestCallback<T> callback) {

        try {
            callback.onStart();
            return toData(parameter, callback);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onError(HttpManagerConstant.ERRORCODE, e.getMessage(), e);
            return null;
        }

    }

    /**
     * 上传文件
     *
     * @param parameter 文件参数类
     * @param callback  回调
     * @return
     */
    private IRequestTask toData(IDataParameter parameter, final IRequestCallback callback) throws Exception {

        final Type genericityType = callback.getGenericityType();
        final boolean isString = HttpUtils.isString(genericityType);

        int method = parameter.getMethod();
        PostRequest post = EasyHttp.post(parameter.getUrl());
        UIProgressResponseCallBack listener = new UIProgressResponseCallBack() {
            @Override
            public void onUIResponseProgress(long bytesRead, long contentLength, boolean done) {
                int progress = (int) (bytesRead * 100 / contentLength);
                if (done) {//完成
                    progress = 100;
                }
                callback.onProgress(progress);
            }
        };
        if (HttpManagerConstant.ONE_FILE == method) {//单个文件
            if (parameter.getFile() != null) {
                DownData.DownFile file = parameter.getFile();
                if (file.getMediaType() != null) {
                    post.params(file.getKey(), file.getFile(), file.getFlieName(), file.getMediaType(),
                            listener);
                } else {
                    post.params(file.getKey(), file.getFile(), file.getFlieName(), listener);
                }

            }
        } else if (HttpManagerConstant.MORE_FILE == method) {//多个文件 多个key
            if (parameter.getFiles() != null) {
                DownData files = parameter.getFiles();
                List<DownData.DownFile> downFiles = files.getDownFiles();
                if (downFiles != null) {
                    for (int i = 0; i < downFiles.size(); i++) {
                        DownData.DownFile downFile = downFiles.get(i);
                        if (downFile.getMediaType() != null) {
                            post.params(downFile.getKey(), downFile.getFile(), downFile.getFlieName(),
                                    downFile.getMediaType(), listener);
                        } else {
                            post.params(downFile.getKey(), downFile.getFile(), downFile.getFlieName(),
                                    listener);
                        }

                    }
                }
            }
        } else if (HttpManagerConstant.MORE_FILE_KEYS == method) {//多个文件 一个key
            if (parameter.getFileKeys() != null) {
                DownData fileKeys = parameter.getFileKeys();
                String key = fileKeys.getKey();
                post.addFileParams(key, fileKeys.toFilesList(), listener);
            }
        } else if (HttpManagerConstant.FILE_INPUTSTREAM == method) { // 上传数据流
            if (parameter.getInputStream() != null) {
                DownData inputStream = parameter.getInputStream();
                List<DownData.DownInputStream> downInputStreams = inputStream.getDownInputStreams();
                if (downInputStreams != null) {
                    for (int i = 0; i < downInputStreams.size(); i++) {
                        DownData.DownInputStream downInputStream = downInputStreams.get(i);
                        post.params(downInputStream.getKey(), downInputStream.getInputStream(),
                                downInputStream.getName(), downInputStream.getMediaType(), listener);
                    }
                }
            }
        } else if (HttpManagerConstant.FILE_IBYTE == method) {//上传 byte
            if (parameter.getBytes() != null) {
                DownData bytes = parameter.getBytes();
                List<DownData.DownByte> downBytes = bytes.getDownBytes();
                if (downBytes != null) {
                    for (int i = 0; i < downBytes.size(); i++) {
                        DownData.DownByte downByte = downBytes.get(i);
                        post.params(downByte.getKey(), downByte.getBytes(), downByte.getName(),
                                downByte.getMediaType(), listener);
                    }
                }
            }

        }

        Disposable execute = post.execute(new CallBack<String>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(ApiException e) {
                callback.onError(e.getCode(), e.getMessage(), e);
            }

            @Override
            public void onSuccess(String s) {
                if (isString) {
                    callback.onSuccess(s, false);
                } else {
                    callback.onSuccess(new Gson().fromJson(s, genericityType), false);
                }
            }
        });


        return new RequestTast(execute);
    }


    /**
     * post请求
     *
     * @param parameters 请求体参数
     * @param callback   回调
     * @param <T>        泛型
     * @return 返回请求标识符
     */
    private <T> IRequestTask toPost(IParameters parameters, final IRequestCallback<T> callback) throws Exception {
        final Type genericityType = callback.getGenericityType();
        final boolean stringJson = HttpUtils.isString(genericityType);
        Disposable execute = null;
        PostRequest postRequest = null;
        if (HttpManagerConstant.POST.equals(parameters.getMethob())) {//post方法
            postRequest = RxEasManager.post(parameters.getUrl());
            if (parameters.getConnectTimeOut() > 0) {
                postRequest.connectTimeout(parameters.getConnectTimeOut());
            }
            if (parameters.getReadTimeOut() > 0) {
                postRequest.readTimeOut(parameters.getReadTimeOut());
            }
            if (parameters.getWriteTimeOut() > 0) {
                postRequest.writeTimeOut(parameters.getWriteTimeOut());
            }
            if (parameters.getHeaders() != null) {
                for (Map.Entry<String, String> entry : parameters.getHeaders().entrySet()) {
                    postRequest.headers(entry.getKey(), entry.getValue());
                }
            }
            if (TextUtils.isEmpty(parameters.getJson())) {
                postRequest.upJson(parameters.getJson());
            } else if (parameters.getParameters() != null) {
                HttpParams httpParams = new HttpParams();
                httpParams.put(parameters.getParameters());
                postRequest.params(httpParams);
            }

            if (parameters.getSign()) {//执行签名
                //TODO 加密签名
            }
            if (parameters.getCache() != 0) {//需要缓存
                CacheMode cacheMode;
                switch (parameters.getCache()) {
                    case HttpManagerConstant.CACHE_NO://走默认okhttp 的cache 缓存
                        cacheMode = CacheMode.NO_CACHE;
                        break;
                    case HttpManagerConstant.CACHE_NET_CACHE://先请求网络，请求网络失败后再加载缓存
                        cacheMode = CacheMode.FIRSTREMOTE;
                        break;
                    case HttpManagerConstant.CACHE_CACHE_NET://先加载缓存，缓存没有再去请求网络
                        cacheMode = CacheMode.FIRSTCACHE;
                        break;
                    case HttpManagerConstant.CACHE_NET://仅加载网络，但数据依然会被缓存
                        cacheMode = CacheMode.ONLYREMOTE;
                        break;
                    case HttpManagerConstant.CACHE_CACHE_NET_TOW://先使用缓存，不管是否存在，仍然请求网络，会回调两次
                        cacheMode = CacheMode.CACHEANDREMOTE;
                        break;
                    case HttpManagerConstant.CACHE_CACHE_NET_ONLY://先使用缓存，不管是否存在，仍然请求网络，会先把缓存回调给你，
                        cacheMode = CacheMode.CACHEANDREMOTEDISTINCT; //* 等网络请求回来发现数据是一样的就不会再返回，否则再返回
                        break;
                    case HttpManagerConstant.CACHE_CACHE://只读取缓存
                        cacheMode = CacheMode.ONLYCACHE;
                        break;
                    default:
                        cacheMode = CacheMode.NO_CACHE;
                        break;
                }

                execute = postRequest.cacheMode(cacheMode).cacheKey(parameters.getCacheKey())
                        .cacheTime(parameters.getCacheTime() == 0 ? -1 : parameters.getCacheTime())
                        .execute(new SimpleCallBack<CacheResult<String>>() {
                            @Override
                            public void onError(ApiException e) {
                                callback.onError(e.getCode(), e.getMessage(), e);
                            }

                            @Override
                            public void onSuccess(CacheResult<String> stringCacheResult) {
                                boolean fromCache = stringCacheResult.isFromCache;//true 来自缓存 false自网络
                                if (stringJson) {
                                    callback.onSuccess((T) stringCacheResult.data, fromCache);
                                } else {
//                                    callback.onSuccess(new Gson().fromJson(stringCacheResult.data, genericityType), fromCache);
                                }

                            }
                        });
            } else {
                execute = postRequest.execute(new CallBack<String>() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(ApiException e) {
                        callback.onError(e.getCode(), e.getMessage(), e);
                    }

                    @Override
                    public void onSuccess(String s) {
                        if (stringJson) {
                            callback.onSuccess((T) s, false);
                        } else {
                            T t = null;
                            try {
                                t = (new Gson()).fromJson(s, genericityType);
                                callback.onSuccess(t, false);
                            } catch (Exception e) {
                                e.printStackTrace();
                                callback.onError(HttpManagerConstant.ERRORCODE,
                                        HttpManagerConstant.ERRORMSG, e);
                            }

                        }
                    }
                });
            }


        }

        return new RequestTast(execute);
    }


    /**
     * get请求方式
     *
     * @param parameters 请求携带参数
     * @param callback   请求回调
     * @param <T>        请求回调泛型
     * @return 返回请求标识符
     * @throws Exception 抛出异常
     */
    private <T> IRequestTask toGet(IParameters parameters, final IRequestCallback<T> callback) throws Exception {
        final Type genericityType = callback.getGenericityType();
        final boolean stringJson = HttpUtils.isString(genericityType);
        Disposable execute = null;
        GetRequest getRequest = null;
        if (HttpManagerConstant.GET.equals(parameters.getMethob())) {//get方法
            getRequest = RxEasManager.get(parameters.getUrl());
            if (parameters.getConnectTimeOut() > 0) {
                getRequest.connectTimeout(parameters.getConnectTimeOut());
            }
            if (parameters.getReadTimeOut() > 0) {
                getRequest.readTimeOut(parameters.getReadTimeOut());
            }
            if (parameters.getWriteTimeOut() > 0) {
                getRequest.writeTimeOut(parameters.getWriteTimeOut());
            }
            if (parameters.getHeaders() != null) {
                for (Map.Entry<String, String> entry : parameters.getHeaders().entrySet()) {
                    getRequest.headers(entry.getKey(), entry.getValue());
                }
            }

            if (parameters.getParameters() != null) {
                HttpParams httpParams = new HttpParams();
                httpParams.put(parameters.getParameters());
                getRequest.params(httpParams);
            }

            if (parameters.getSign()) {//执行签名
                //TODO 加密签名
            }
            if (parameters.getCache() != 0) {//需要缓存
                CacheMode cacheMode;
                switch (parameters.getCache()) {
                    case HttpManagerConstant.CACHE_NO://走默认okhttp 的cache 缓存
                        cacheMode = CacheMode.NO_CACHE;
                        break;
                    case HttpManagerConstant.CACHE_NET_CACHE://先请求网络，请求网络失败后再加载缓存
                        cacheMode = CacheMode.FIRSTREMOTE;
                        break;
                    case HttpManagerConstant.CACHE_CACHE_NET://先加载缓存，缓存没有再去请求网络
                        cacheMode = CacheMode.FIRSTCACHE;
                        break;
                    case HttpManagerConstant.CACHE_NET://仅加载网络，但数据依然会被缓存
                        cacheMode = CacheMode.ONLYREMOTE;
                        break;
                    case HttpManagerConstant.CACHE_CACHE_NET_TOW://先使用缓存，不管是否存在，仍然请求网络，会回调两次
                        cacheMode = CacheMode.CACHEANDREMOTE;
                        break;
                    case HttpManagerConstant.CACHE_CACHE_NET_ONLY://先使用缓存，不管是否存在，仍然请求网络，会先把缓存回调给你，
                        cacheMode = CacheMode.CACHEANDREMOTEDISTINCT; //* 等网络请求回来发现数据是一样的就不会再返回，否则再返回
                        break;
                    case HttpManagerConstant.CACHE_CACHE://只读取缓存
                        cacheMode = CacheMode.ONLYCACHE;
                        break;
                    default:
                        cacheMode = CacheMode.NO_CACHE;
                        break;
                }

                execute = getRequest.cacheMode(cacheMode).cacheKey(parameters.getCacheKey())
                        .cacheTime(parameters.getCacheTime() == 0 ? -1 : parameters.getCacheTime())
                        .execute(new SimpleCallBack<CacheResult<String>>() {
                            @Override
                            public void onError(ApiException e) {
                                callback.onError(e.getCode(), e.getMessage(), e);
                            }

                            @Override
                            public void onSuccess(CacheResult<String> stringCacheResult) {
                                boolean fromCache = stringCacheResult.isFromCache;//true 来自缓存 false自网络
                                if (stringJson) {
                                    callback.onSuccess((T) stringCacheResult.data, fromCache);
                                } else {
//                                    callback.onSuccess(new Gson().fromJson(stringCacheResult.data, genericityType), fromCache);
                                }

                            }
                        });
            } else {
                execute = getRequest.execute(new CallBack<String>() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(ApiException e) {
                        callback.onError(e.getCode(), e.getMessage(), e);
                    }

                    @Override
                    public void onSuccess(String s) {
                        if (stringJson) {
                            callback.onSuccess((T) s, false);
                        } else {
                            T t = null;
                            try {
                                t = (new Gson()).fromJson(s, genericityType);
                                callback.onSuccess(t, false);
                            } catch (Exception e) {
                                e.printStackTrace();
                                callback.onError(HttpManagerConstant.ERRORCODE,
                                        HttpManagerConstant.ERRORMSG, e);
                            }

                        }
                    }
                });
            }


        }


        return new RequestTast(execute);
    }


}
