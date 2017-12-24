package socilgirl.dell.mydemo.httpmanager;

/**
 * Created by ${小强} on 2017/10/26.
 */

public class HttpManagerConstant {
    /**
     * 上传单个文件
     */
    public static final int ONE_FILE = 1;
    /**
     * 上传多个文件 多个key
     */
    public static final int MORE_FILE = 2;
    /**
     * 上传多个文件 一个key
     */
    public static final int MORE_FILE_KEYS = 3;
    /**
     * 上传数据流
     */
    public static final int FILE_INPUTSTREAM = 4;
    /**
     * 上传byte[]
     */
    public static final int FILE_IBYTE = 5;
    /**
     * 缓存模式 默认okhttp缓存
     */
    public static final int CACHE_NO = 1;
    /**
     * 缓存模式 先请求网络，请求网络失败后再加载缓存
     */
    public static final int CACHE_NET_CACHE = 2;
    /**
     * 缓存模式 先加载缓存，缓存没有再去请求网络
     */
    public static final int CACHE_CACHE_NET = 3;
    /**
     * 缓存模式 仅加载网络，但数据依然会被缓存
     */
    public static final int CACHE_NET = 4;
    /**
     * 缓存模式 先使用缓存，不管是否存在，仍然请求网络，会回调两次
     */
    public static final int CACHE_CACHE_NET_TOW = 5;
    /**
     * 缓存模式 先使用缓存，不管是否存在，仍然请求网络，会先把缓存回调给你，
     * 等网络请求回来发现数据是一样的就不会再返回，否则再返回
     */
    public static final int CACHE_CACHE_NET_ONLY = 6;
    /**
     * 缓存模式 只读取缓存
     */
    public static final int CACHE_CACHE= 7;

    /**
     * get请求方式
     */
    public static final String GET = "GET";
    /**
     * post请求方式
     */
    public static final String POST = "POST";
    /**
     * 回调的泛型类型
     */
    public static final String OBJECT = "Object";
    /**
     * 回调 的泛型类型 字符串
     */
    public static final String STRING = "String";
    /**
     * 未知异常
     */
    public static final int ERRORCODE = 0;
    /**
     * 解析异常时返回的字符串
     */
    public static final String ERRORMSG = "服务器返回数据解析异常";
    /**
     * url地址不能为空
     */
    public static final String URL_NULL = "url地址不能为空";
    /**
     * 请求头map 不能为空
     */
    public static final String HEADERMAP_NULL = "请求头map 不能为空";
    /**
     * 请求参数map 不能为空
     */
    public static final String PARAMETERMAP_NULL = "请求参数map 不能为空";
    /**
     * 参数key 不能为空
     */
    public static final String PARAMETERKEY_NULL = "参数key 不能为空";
    /**
     * 下载文件名字为空
     */
    public static final String DOWNLOADNAME_NULL = "下载文件名字为空";
    /**
     * 下载文件路径为空
     */
    public static final String DOWNLOADPATH_NULL = "下载文件路径为空";
    /**
     * 没有初始化
     */
    public static final String INITANAGER = "没有初始化本框架";
    /**
     * 你没有填入文件
     */
    public static final String FILE_NULL = "你没有填入文件";
    /**
     * 缓存key为空
     */
    public static final String CATCHKEY_NULL = "缓存key为空";
}
