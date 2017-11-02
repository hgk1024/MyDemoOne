package socilgirl.dell.mydemo.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;

import socilgirl.dell.mydemo.R;
import socilgirl.dell.mydemo.fragment.TwoFradgment;
import socilgirl.dell.mydemo.model.DataBean;

public class EventPostActivity extends FragmentActivity {

    private Button btnPostMsg;
    private Button btnShow;
    private LinearLayout linearLayout;
    DataBean dataBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_post);
        btnPostMsg = findViewById(R.id.btn_post);
        btnShow = findViewById(R.id.show_two_fragment);
        linearLayout = findViewById(R.id.move_add_fragment);
        btnPostMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean = new DataBean("第一条Ac",25,"男",true);
                EventBus.getDefault().post(dataBean);
                Log.d("EventPostActivity", "Activitiy发送了一个DataBean数据");
                finish();
            }
        });

        addFragment();
        /*btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment();
            }
        });*/
    }

    private void addFragment() {
        //添加一个FragmentTransaction的实例
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //用add方法添加Fragment对象
        TwoFradgment twoFradgment = TwoFradgment.newInstance();
        transaction.add(R.id.move_add_fragment,twoFradgment);
        //调用commit方法使FragmentTransaction实例的改变生效
        transaction.commit();
    }
}
