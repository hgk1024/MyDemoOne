package socilgirl.dell.mydemo.httpmanager.httpinterface;

import java.util.Map;

/**
 * Created by ${小强} on 2017/10/26.
 */

public interface IParameters {
    /**
     * 获取请求方式
     *
     * @return
     */
    String getMethob();

    /**
     * 获取url地址
     *
     * @return
     */
    String getUrl();

    /**
     * 局部写超时,单位毫秒
     *
     * @return
     */
    long getWriteTimeOut();

    /**
     * 局部读超时,单位毫秒
     *
     * @return
     */
    long getReadTimeOut();

    /**
     * 局部链接超时,单位毫秒
     *
     * @return
     */
    long getConnectTimeOut();


    /**
     * 获取请求头 不会覆盖之前的请求头
     *
     * @return
     */
    Map<String, String> getHeaders();

    /**
     * 获取请求参数
     *
     * @return
     */
    Map<String, String> getParameters();

    /**
     * 获取json 串
     *
     * @return
     */
    String getJson();

    /**
     * 是否签名
     *
     * @return
     */
    boolean getSign();

    /**
     * 是否需要缓存  0 不需要
     *
     * @return 缓存模式
     */
    int getCache();

    /**
     * 获取缓存key
     *
     * @return
     */
    String getCacheKey();

    /**
     * 获取缓存时间 -1 为永久缓存  返回值0 认为是-1
     *
     * @return
     */
    int getCacheTime();

}
