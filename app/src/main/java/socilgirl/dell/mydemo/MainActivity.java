package socilgirl.dell.mydemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fingdo.statelayout.StateLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import socilgirl.dell.mydemo.adapter.MyRecyclerViewAdapter;
import socilgirl.dell.mydemo.adapter.SecondViewAdapter;
import socilgirl.dell.mydemo.common.viewweight.CommonTopBar;
import socilgirl.dell.mydemo.httpmanager.callback.BaseCallback;
import socilgirl.dell.mydemo.httpmanager.imhttpinterface.GetParameters;
import socilgirl.dell.mydemo.httpmanager.imhttpinterface.HttpManager;
import socilgirl.dell.mydemo.model.UserInfo;

public class MainActivity extends Activity {

    private Button btnClick;
    private StateLayout stateLayout;
    private RecyclerView recyclerView;
    private List<String> mList;
    CommonTopBar topBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        topBar = findViewById(R.id.com_main_view);
        topBar.setTitle(false,"主页面",null);
        topBar.setRlLeft(true,null,R.mipmap.fanhui);
        topBar.setRlRight(false,"跳转",0);
        topBar.setOnLeftClickListener(new CommonTopBar.OnLeftClickListener() {
            @Override
            public void onClickLeft() {
                Toast.makeText(MainActivity.this, "左侧按钮被点击了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickRight() {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }

            @Override
            public void onClickTitle() {
                topBar.setTitle(false,"点击显示",null);
                Toast.makeText(MainActivity.this, "标题栏被点击了", Toast.LENGTH_SHORT).show();
            }
        });
        btnClick = (Button) findViewById(R.id.btn_one);
        stateLayout = (StateLayout) findViewById(R.id.statelayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                stateLayout.showEmptyView();
//                DownloadThread thread = new DownloadThread();
//                thread.start();
                GetParameters parameters = new GetParameters.Builder()
                        .setUrl("user/get_user_info.json")
                        .setParameter("userid","1048")
                        .build();

                HttpManager.getInstance().get(parameters, new BaseCallback<UserInfo>() {
                    @Override
                    public void onStart() {
                        Log.d("MainActivity", "TAG--start");
                        Toast.makeText(MainActivity.this, "start==开始", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(UserInfo s) {
                        Toast.makeText(MainActivity.this, "获取数据成功", Toast.LENGTH_SHORT).show();
                        Log.d("MainActivity:====", s.toString());
                    }

                    @Override
                    public void onError(int code, String msg, Exception e) {
                        Log.d("MainActivity", msg+"-----"+e.getMessage());
                        Toast.makeText(MainActivity.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyRecyclerViewAdapter(this,mList));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,5));
    }

    private void initData() {
        mList = new ArrayList<>();
        mList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1380084653,2448555822&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=227953490,3054069314&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2302918630,1086443006&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2068546062,2852291024&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4219594110,2716012792&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2586885873,577264777&fm=27&gp=0.jpg");
        mList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1380084653,2448555822&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=227953490,3054069314&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2302918630,1086443006&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2068546062,2852291024&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4219594110,2716012792&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2586885873,577264777&fm=27&gp=0.jpg");
        mList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1380084653,2448555822&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=227953490,3054069314&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2302918630,1086443006&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2068546062,2852291024&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4219594110,2716012792&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2586885873,577264777&fm=27&gp=0.jpg");

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 100:
                    stateLayout.showEmptyView(R.string.secerror,R.mipmap.ugc_delete_last_part_hover);
                    break;
            }
        }
    };
    class DownloadThread extends Thread{
        @Override
        public void run() {
            try{
                System.out.println("DownloadThread id " + Thread.currentThread().getId());
                System.out.println("开始下载文件");
                //此处让线程DownloadThread休眠5秒中，模拟文件的耗时过程
                Thread.sleep(2000);
                System.out.println("文件下载完成");
                //文件下载完成后更新UI
                Message msg = new Message();
                //虽然Message的构造函数式public的，我们也可以通过以下两种方式通过循环对象获取Message
                //msg = Message.obtain(uiHandler);
                //msg = uiHandler.obtainMessage();

                //what是我们自定义的一个Message的识别码，以便于在Handler的handleMessage方法中根据what识别
                //出不同的Message，以便我们做出不同的处理操作
                msg.what = 100;

                //我们可以通过arg1和arg2给Message传入简单的数据
                msg.arg1 = 123;
                msg.arg2 = 321;
                //我们也可以通过给obj赋值Object类型传递向Message传入任意数据
                //msg.obj = null;
                //我们还可以通过setData方法和getData方法向Message中写入和读取Bundle类型的数据
                //msg.setData(null);
                //Bundle data = msg.getData();

                //将该Message发送给对应的Handler
                handler.sendMessage(msg);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

//    private class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHodler>{
//
//        private Context mContext;
//        private LayoutInflater inflater;
//        public MyRecyclerViewAdapter(Context context) {
//            this.mContext = context;
//            inflater = LayoutInflater.from(mContext);
//        }
//
//
//        @Override
//        public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = inflater.inflate(R.layout.recycler_main_item,null);
//            ViewHodler hodler = new ViewHodler(view);
//            return hodler;
//        }
//
//        @Override
//        public void onBindViewHolder(ViewHodler holder, int position) {
//            holder.tvName.setText(mList.get(position));
//        }
//
//
//        @Override
//        public int getItemCount() {
//            return mList.size();
//        }
//        public class ViewHodler extends RecyclerView.ViewHolder{
//
//            TextView tvName;
//            public ViewHodler(View itemView) {
//                super(itemView);
//                tvName = itemView.findViewById(R.id.tv_item_main_recy);
//            }
//        }
//    }
}
