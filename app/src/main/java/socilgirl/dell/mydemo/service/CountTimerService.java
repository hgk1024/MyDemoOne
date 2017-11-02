package socilgirl.dell.mydemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class CountTimerService extends Service {
    private static final String TAG = "CountTimerService";
    private  int timer;
    private String AcName;
    public CountTimerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timer = intent.getIntExtra("time",0);
        AcName = intent.getStringExtra("acName");
        switch (AcName){
            case "socilgirl.dell.mydemo.view.EventTestActivity":
                coundDownTime(timer);
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }
    private void coundDownTime(final int timer) {
        CountDownTimer countDownTimer = new CountDownTimer(timer,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //固定间隔时间被调用
//                ToastUtils.showShort(getApplicationContext(),"==========被执行了========");
                Log.d(TAG, "onTick: "+millisUntilFinished);
                EventBus.getDefault().post(timer/1000);
            }

            @Override
            public void onFinish() {
                //倒计时完成后被调用的方法
                Log.d(TAG, "onFinish: ");
            }
        };
        countDownTimer.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
