package socilgirl.dell.mydemo.httpmanager.httpinterface;


import android.app.Application;

import socilgirl.dell.mydemo.httpmanager.callback.IRequestCallback;


/**
 * Created by ${小强} on 2017/10/26.
 */

public interface IHttpManager {
    /**
     * 初始化
     *
     * @param context
     */
    void init(Application context);

    /**
     * 检查是否初始化
     */
    void inspectContent();

    /**
     * get请求
     *
     * @param parameters 参数接口类
     * @param callback   回调
     * @return
     */
    <T> IRequestTask get(IParameters parameters, IRequestCallback<T> callback);

    /**
     * post请求
     *
     * @param parameters 参数接口类
     * @param callback   回调
     * @return
     */
    <T> IRequestTask post(IParameters parameters, IRequestCallback<T> callback);

    /**
     * 下载文件
     *
     * @param parameter 参数接口类
     * @param callback  回调
     * @return
     */
    <T>IRequestTask downLoad(IDowmLoadParameter parameter, IRequestCallback<T> callback);

    /**
     * 上传 文件 包括图片 文件 数据流 byte数组
     *
     * @param parameter
     * @param <T>
     * @return
     */
    <T> IRequestTask postData(IDataParameter parameter, IRequestCallback<T> callback);

}
