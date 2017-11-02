package socilgirl.dell.mydemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import socilgirl.dell.mydemo.model.DataBeanThree;

public class MyService extends Service {
    private static final String TAG = "MyService";
    MyBind mBind = new MyBind();


    public MyService() {
    }

   /*
   * service是运行在主线程中的，与Thread没有任何关系
   * Thread是新建一个子线程，进行一些耗时操作
   *
   * Android的后台就是指，它的运行是完全不依赖UI的。即使Activity被销毁，或者程序被关闭，只要进程还在，Service就可以继续运行
   *
   * */
    @Override
    public void onCreate() {//只用在service第一次创建的时候被调用
        super.onCreate();
        Log.d(TAG, "MyService thread id is运行在" + Thread.currentThread().getId());
    }
    //每次启动service都会被调用
    //在使用ServiceConnection与bind建立Activity和Service连接时OnCreate（）方法执行了，
    // 但是onStartCommand方法不执行
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommoand Service");
        EventBus.getDefault().post(new DataBeanThree("nihao",03,"女"));
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy Service");
    }

    /*
    *用于Service和Activity建立联系的方法
    *使用将Service绑定到Activity中，Service最好是在activity销毁的时候一起销毁，
    * 因为如果不这样的话就会造成内存泄漏，service不会再被找到
    * */
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d(TAG, "onBindService");
//        throw new UnsupportedOperationException("Not yet implemented");
        return mBind;
    }

   public class MyBind extends Binder{

        public void sendEventBusmsg(){
            EventBus.getDefault().post(new DataBeanThree("zhang",76,"男"));
        }

//        public void showTime(final int mTime){
//            final Handler handler = new Handler(){
//                @Override
//                public void handleMessage(Message msg) {
//                   switch (msg.what){
//                       case 100:
//
//                           EventBus.getDefault().post();
//                           break;
//                   }
//                    super.handleMessage(msg);
//                }
//            };
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(1000);
//                        Message message = new Message();
//                        message.what = 100;
//                        handler.sendMessage(message);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }
    }
}
