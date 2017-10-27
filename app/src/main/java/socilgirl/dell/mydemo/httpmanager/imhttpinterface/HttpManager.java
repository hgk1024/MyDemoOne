package socilgirl.dell.mydemo.httpmanager.imhttpinterface;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.CallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.HttpParams;
import com.zhouyou.http.request.GetRequest;
import com.zhouyou.http.request.PostRequest;

import java.lang.reflect.Type;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import socilgirl.dell.mydemo.httpmanager.HttpManagerConstant;
import socilgirl.dell.mydemo.httpmanager.RequestTast;
import socilgirl.dell.mydemo.httpmanager.RxEasManager;
import socilgirl.dell.mydemo.httpmanager.httpinterface.IHttpManager;
import socilgirl.dell.mydemo.httpmanager.httpinterface.IParameters;
import socilgirl.dell.mydemo.httpmanager.httpinterface.IRequestCallback;
import socilgirl.dell.mydemo.httpmanager.httpinterface.IRequestTask;

import static socilgirl.dell.mydemo.httpmanager.HttpManagerConstant.ERRORCODE;


/**
 * Created by ${小强} on 2017/10/26.
 */

public class HttpManager implements IHttpManager {
    private static HttpManager ourInstance;

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
    public <T> IRequestTask get(IParameters parameters, IRequestCallback<T> callback) {
        try {
            callback.onStart();
            return toGet(parameters, callback);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onError(ERRORCODE, e.getMessage() + "", e);
        }
        return null;
    }

    private <T> IRequestTask toGet(IParameters parameters, final IRequestCallback<T> callback) throws Exception {

        final Type genericityType = callback.getGenericityType();
        boolean isString = false;
        if (genericityType instanceof Class) {
            switch (((Class) genericityType).getSimpleName()) {
                case HttpManagerConstant.OBJECT:
                    isString = true;
                    break;
                case HttpManagerConstant.STRING:
                    isString = true;
                    break;
                default:
                    break;
            }
        } else {
            isString = true;
        }
        final boolean stringJson = isString;


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
                        callback.onSuccess((T) s);
                    } else {
                        T t = null;
                        try {
                            t = (new Gson()).fromJson(s, genericityType);
                            callback.onSuccess(t);
                        } catch (Exception e) {
                            e.printStackTrace();
                            callback.onError(ERRORCODE,
                                    HttpManagerConstant.ERRORMSG, e);
                        }

                    }
                }
            });
        }

        return new RequestTast(execute);
    }

    @Override
    public <T> IRequestTask post(IParameters parameters, IRequestCallback<T> callback) {
        try {
            callback.onStart();
            return toPost(parameters, callback);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onError(ERRORCODE, e.getMessage() + "", e);
        }
        return null;
    }

    private <T> IRequestTask toPost(IParameters parameters, final IRequestCallback<T> callback) {

        final Type genericityType = callback.getGenericityType();

        boolean isString = false;
        if (genericityType instanceof Class) {
            switch (((Class) genericityType).getSimpleName()) {
                case HttpManagerConstant.OBJECT:
                    isString = true;
                    break;
                case HttpManagerConstant.STRING:
                    isString = true;
                    break;
                default:
                    break;
            }
        } else {
            isString = true;
        }
        final boolean stringJson = isString;
        Disposable execute = null;
        PostRequest postRequest = null;
        if (HttpManagerConstant.POST.equals(parameters.getMethob())) {//post方法
            postRequest = EasyHttp.post(parameters.getUrl());
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
                        callback.onSuccess((T) s);
                    } else {
                        T t = null;
                        try {
                            t = (new Gson()).fromJson(s, genericityType);
                            callback.onSuccess(t);
                        } catch (Exception e) {
                            e.printStackTrace();
                            callback.onError(ERRORCODE,
                                    HttpManagerConstant.ERRORMSG, e);
                        }

                    }
                }
            });

        }

        return new RequestTast(execute);
    }

}
