package socilgirl.dell.mydemo.view;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import socilgirl.dell.mydemo.R;
import socilgirl.dell.mydemo.model.DataBean;
import socilgirl.dell.mydemo.model.DataBeanThree;
import socilgirl.dell.mydemo.model.DataBeanTwo;
import socilgirl.dell.mydemo.service.CountTimerService;
import socilgirl.dell.mydemo.service.MyService;

public class EventTestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvActivity,tvFragment,tvService,tvTimeShow;
    private Button btnActivice,btnService,btnUnbind ,btnStartService,btStopService,btnStartTimer;
    private MyService.MyBind myBind;
    private int timeSurplus = 5000*1000;

    //首先创建了一个ServiceConnection的匿名类，在里面重写了onServiceConnected()方法和onServiceDisconnected()方法，
    // 这两个方法分别会在Activity与Service建立关联和解除关联的时候调用。
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBind = (MyService.MyBind) service;
            myBind.sendEventBusmsg();//测试Service发送EventBus信息
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("EventTestActivity", "----------连接已断开");
            tvService.setText("");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_test);
        initView();
        Log.d("EventTestActivity", "EventTestActivity 是运行在" + Thread.currentThread().getId());//判断serVice和Activity的线程是否相同====》都在主线程上运行
        Log.d("EventTestActivity", "类名是：" + this.getClass().getName());
    }

    private void initView() {
        tvActivity = findViewById(R.id.tv_ac_event);
        tvFragment = findViewById(R.id.tv_fragment_event);
        tvService = findViewById(R.id.tv_service_event);
        tvTimeShow = findViewById(R.id.tv_show_time);
        btnStartTimer = findViewById(R.id.btn_start_time);
        btnActivice = findViewById(R.id.btn_jump_ac);
        btnService = findViewById(R.id.btn_relized_service);
        btnStartService = findViewById(R.id.btn_start_service);
        btStopService = findViewById(R.id.btn_stop_service);
        btnUnbind = findViewById(R.id.btn_unbind_service);
        btnUnbind.setOnClickListener(this);
        btnService.setOnClickListener(this);
        btnActivice.setOnClickListener(this);
        btnStartService.setOnClickListener(this);
        btStopService.setOnClickListener(this);
        btnStartTimer.setOnClickListener(this);
        //使用官方的方法注册不可以正常使用,EventBus再在onCreate中注册可以收到信息，
        // 如果在onStart和onResume中注册则不会收到消息
        EventBus.getDefault().register(this);
    }


    @Subscribe
    public void onEventMainThread(DataBean dataBean){
        tvActivity.setText(dataBean.getName());
        Log.d("EventTestActivity", "onEvent接收到了消息:" + dataBean.toString());
    }

    @Subscribe
    public void onFragmentEvent(DataBeanTwo dataBeanTwo){
        tvFragment.setText(dataBeanTwo.toString());
    }

    @Subscribe
    public void onEventFromService(DataBeanThree dataBeanThree){
        tvService.setText(dataBeanThree.toString());
        Log.d("EventTestActivity", "实现了Service发送消息");
    }

    @Subscribe
    public void showTime(int time){
        timeSurplus = time;
        tvTimeShow.setText(timeSurplus+"");
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_jump_ac:
                startActivity(new Intent(this,EventPostActivity.class));
                break;

            case R.id.btn_start_service:
                Intent intent4 = new Intent(this,MyService.class);
                startService(intent4);//
                break;

            //这里应注意当startService和startBind都被点击了后，只有当stopService和UNbind都点一下才能将service销毁，
            // 否则不能达到销毁service的目的
            //一个Service必须要在既没有和任何Activity关联又处理停止状态的时候才会被销毁。
            case R.id.btn_stop_service:
                stopService(new Intent(this,MyService.class));
                stopService(new Intent(this,CountTimerService.class));
                tvService.setText("");
                break;
            case R.id.btn_relized_service:
                //建立service与Activity之间的连接
                Intent intent = new Intent(this,MyService.class);
                bindService(intent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service:
//                if (connection.)
                unbindService(connection);
                tvService.setText("");
                break;
            case R.id.btn_start_time:
//                Intent intent1 = new Intent(this,MyService.class);
//                bindService(intent1,connection,BIND_AUTO_CREATE);
                Intent intent2 = new Intent(this,CountTimerService.class);
                intent2.putExtra("time",timeSurplus);
                intent2.putExtra("acName",this.getClass().getName());
                startService(intent2);//

                break;
        }
    }
}
