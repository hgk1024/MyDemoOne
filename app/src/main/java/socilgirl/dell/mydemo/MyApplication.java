package socilgirl.dell.mydemo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.umeng.socialize.UMShareAPI;
import com.zhouyou.http.EasyHttp;


import socilgirl.dell.mydemo.greendao.GreenDaoManager;
import socilgirl.dell.mydemo.imagemanger.ImageManager;
import socilgirl.dell.mydemo.utils.tenutil.LogUtils;

/**
 * Created by dell on 2017/10/25.
 */

public class MyApplication extends MultiDexApplication {
    private static Application app = null;


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
//        initRxEasyHttp();
        ImageManager.getInstance().init(this);//初始化图片加载框架
        EasyHttp.init(this);
        LogUtils.isDebug = true;
        isDebug();
        EasyHttp.getInstance().setBaseUrl("https://api.zaiwaner.cn/1.0.1/").debug("网络访问：",true);
        GreenDaoManager.getInstance();//Greendao初始化
        UMShareAPI.get(this);
    }

    private void isDebug() {
//        if(LogUtils.isDebug){
//            Config.DEBUG = true;
//        }else{
//            Config.DEBUG = false;
//        }
    }

    {
        //配置三方平台的appkey：
//        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
//        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }

    /**
     * 获取Application的Context
     **/
    public static Context getAppContext() {
        if (app == null)
            return null;
        return app.getApplicationContext();
    }
}
