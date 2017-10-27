package socilgirl.dell.mydemo.httpmanager.httpinterface;

/**
 * Created by ${小强} on 2017/10/26.
 */

public interface IHttpManager {
    /**
     * get请求
     * @param parameters
     * @param callback
     * @return
     */
    <T> IRequestTask get(IParameters parameters, IRequestCallback<T> callback);
    /**
     * post请求
     * @param parameters
     * @param callback
     * @return
     */
    <T> IRequestTask post(IParameters parameters, IRequestCallback<T> callback);
}
