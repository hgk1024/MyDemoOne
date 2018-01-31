package socilgirl.dell.mydemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.taobao.sophix.SophixApplication;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhouyou.http.EasyHttp;


import java.math.BigDecimal;
import java.util.Date;

import socilgirl.dell.mydemo.greendao.GreenDaoManager;
import socilgirl.dell.mydemo.imagemanger.ImageManager;
import socilgirl.dell.mydemo.utils.tenutil.LogUtils;
import socilgirl.dell.mydemo.utils.tenutil.SharedPreferencesUtils;
import socilgirl.dell.mydemo.view.GestureActivity;
import socilgirl.dell.mydemo.view.VerificationActivity;

/**
 * Created by dell on 2017/10/25.
 */
//public class MyApplication extends MultiDexApplication {
public class MyApplication extends Application {
    private static Application app = null;
    private static final String TAG = "MyApplication";

    private long time = -2;
    private  int count = 0;
    private int number = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
//        initRxEasyHttp();
        ImageManager.getInstance().init(this);//初始化图片加载框架
        EasyHttp.init(this);
        LogUtils.isDebug = true;
        isDebug();//友盟推送消息是否打印log
        EasyHttp.getInstance().setBaseUrl("https://api.zaiwaner.cn/1.0.1/").debug("网络访问：",true);
        GreenDaoManager.getInstance();//Greendao初始化
        UMShareAPI.get(this);//初始化友盟SDK
        setPatternPass();//设置手势密码
        setUMPushMessage();
        PushAgent.getInstance(this).onAppStart();
    }

    //友盟推送注册
    private void setUMPushMessage() {
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.d(TAG, "onSuccess:deviceToken =  "+deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Toast.makeText(MyApplication.this, "错误信息"+s+" ___ "+s1, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void isDebug() {
        if(LogUtils.isDebug ){
            Config.DEBUG = true;
        }else{
            Config.DEBUG = false;
        }
    }

    //手势密码每个都跳出方法
    private void setPatternPass() {
        //使用registerActivityLifecycleCallbacks实现方法引用的匿
        // 名类中的方法可以实现对应用的所有Activity进行状态统计
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//                Log.d(TAG, "onActivityCreated: ");
            }

            @Override
            public void onActivityStarted(Activity activity) {
//                Log.d(TAG, "onActivityStarted: ");
                if (count == 0) {
                    //Log.v("tag", ">>>>>>>>>>>>>>>>>>>切到前台  lifecycle");
                    // 首先计算时间 网络请求判断是否进入手势密码验证界面
                    Log.d(TAG, ">>>>>>>>>>>>>>>>>>>切到前台  lifecycle");
                    if (-2 == time) {
                        //判断进入解锁手势密

                    }else {
                        Date date2 = new Date();
                        long returnTime = date2.getTime();
                        double sub = new BigDecimal(returnTime).subtract(new BigDecimal(time)).doubleValue();
                        number = (int) SharedPreferencesUtils.getData(activity,"puss_num",1);
                        Log.d(TAG, "onActivityStarted: 之间时间差为:"+sub);
                        if (sub >= 3000) {
                            //此处是判断应用到后台多久时间以后需要开启手势密码
                            //判断进入解锁手势密码\
                            if (number > 5 ){
                                Intent intent = new Intent(activity, VerificationActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                activity.startActivity(intent);
                            }else {
                                Toast.makeText(activity, "进入手绘密码页面", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(activity, GestureActivity.class);
                                intent.putExtra("type", 2);
                                intent.putExtra("puss_pass", number);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                activity.startActivity(intent);
                            }
                        } else {
                            Toast.makeText(activity, "不进入手势密码页面", Toast.LENGTH_SHORT).show();
                            time = -1;
                        }
                    }
                } else {
                    if (-2 == time) {
                        //判断进入解锁手势密

                    }
                    time = -1;
                }
                count++;
            }

            @Override
            public void onActivityResumed(Activity activity) {
//                Log.d(TAG, "onActivityResumed: ");
            }

            @Override
            public void onActivityPaused(Activity activity) {
//                Log.d(TAG, "onActivityPaused: ");
            }

            @Override
            public void onActivityStopped(Activity activity) {
//                Log.d(TAG, "onActivityStopped: ");
                count--;
                if (count == 0) {
                    Log.d(TAG, ">>>>>>>>>>>>>>>>>>>切到后台  lifecycle");
                    Date date = new Date();
                    time = date.getTime();
                } else {
                    time = -1;
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.d(TAG, "onActivitySaveInstanceState: ");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.d(TAG, "onActivityDestroyed: ");
            }
        });
    }

    {
        //配置三方平台的appkey：
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
//        PlatformConfig.setQQZone("1106687792", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setQQZone("1106687792", "ehlPUAnn12aifGwE");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
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
