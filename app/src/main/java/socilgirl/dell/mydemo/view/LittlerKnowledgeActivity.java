package socilgirl.dell.mydemo.view;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import socilgirl.dell.mydemo.R;
import socilgirl.dell.mydemo.weight.QQListView;

public class LittlerKnowledgeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LittlerKnowledgeActivit";

    private ListView qqListView;
    private ArrayAdapter<String> adapter;
    private List<String> mDatas;
    private FloatingActionButton floatButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_littler_knowledge);

        qqListView = findViewById(R.id.listview_cehua);
        floatButton = findViewById(R.id.btn_float);
        findViewById(R.id.btn_connect_net).setOnClickListener(this);
        floatButton.setOnClickListener(this);
        mDatas = new ArrayList<String>(Arrays.asList("HelloWorld", "Welcome", "Java", "Android", "Servlet", "Struts",
                "Hibernate", "Spring", "HTML5", "Javascript", "Lucene"));
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mDatas);
        qqListView.setAdapter(adapter);

//        qqListView.setDelButtonClickListener(new QQListView.DelButtonClickListener() {
//            @Override
//            public void clickHappend(int position) {
//                adapter.remove(adapter.getItem(position));
//            }
//        });
        qqListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LittlerKnowledgeActivity.this, position+";"+adapter.getItem(position), Toast.LENGTH_SHORT).show();
            adapter.remove(adapter.getItem(position));
            }
        });
    }

    private void judgeNet() {
        if (isNetworkAvailable(this)){
            Toast.makeText(this, "当前网络可用", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "当前网络不可用", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable(LittlerKnowledgeActivity littlerKnowledgeActivity) {
        Context context  = getApplicationContext();
        //获取收集所有链接管理对象（WiFi和net等链接）
        ConnectivityManager manager =
                        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null){
            return false;
        }else{
            //获取netWorkInfo对象
            NetworkInfo[] networkInfos = manager.getAllNetworkInfo();//获取net对象
            if (networkInfos != null&&networkInfos.length>0) {
                for (int i = 0; i < networkInfos.length; i++) {
                    Log.d(TAG, "isNetworkAvailable:状态 " +networkInfos[i].getState());
                    Log.i(TAG, "isNetworkAvailable: 类型"+networkInfos[i].getTypeName());
                    //判断当前网络是否连接状态
                    if (networkInfos[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_float:
                Toast.makeText(this, "floatingActionButton按钮被点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_connect_net:
                judgeNet();
                break;
        }
    }
}
