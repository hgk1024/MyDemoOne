package socilgirl.dell.mydemo.view;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fingdo.statelayout.StateLayout;
import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import socilgirl.dell.mydemo.R;
import socilgirl.dell.mydemo.adapter.MyRecycleAdapterTwo;
import socilgirl.dell.mydemo.androidtest.TestActivity;
import socilgirl.dell.mydemo.common.viewweight.CommonTopBar;
import socilgirl.dell.mydemo.httpmanager.callback.BaseCallback;
import socilgirl.dell.mydemo.httpmanager.imhttpinterface.GetParameters;
import socilgirl.dell.mydemo.httpmanager.imhttpinterface.HttpManager;
import socilgirl.dell.mydemo.model.UserInfo;


public class MainActivity extends Activity {

    private static final int REQUEST_CODE_SETTING = 300;
    private static final int REQUEST_CODE_PERMISSION_SINGLE = 400;
    private static final String TAG = "MainActivity";
    private Button btnClick;
    private StateLayout stateLayout;
    private RecyclerView recyclerView;
    private List<String> mList;
    CommonTopBar topBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//        getPermission();
        initData();
        setTitleData();
        setOnEvent();
    }

    private void getPermission() {
        AndPermission.with(this)
                .requestCode(REQUEST_CODE_PERMISSION_SINGLE)
                .permission(Manifest.permission.CAMERA,Manifest.permission.CALL_PHONE)
                .rationale(listener2)
                .callback(listener)
                .start();
    }
    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            // 权限申请成功回调。
            // 这里的requestCode就是申请时设置的requestCode。
            // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
            if (requestCode == REQUEST_CODE_PERMISSION_SINGLE) {
                // TODO ...
                Toast.makeText(MainActivity.this, "申请权限成功", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            // 权限申请失败回调。
            if (requestCode == REQUEST_CODE_PERMISSION_SINGLE) {
                // TODO ...
                Toast.makeText(MainActivity.this, "申请权限失败", Toast.LENGTH_SHORT).show();
            }

            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(MainActivity.this, deniedPermissions)) {
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(MainActivity.this, REQUEST_CODE_SETTING).show();

                // 第二种：用自定义的提示语。
//             AndPermission.defaultSettingDialog(this, REQUEST_CODE_SETTING)
//                     .setTitle("权限申请失败")
//                     .setMessage("我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！")
//                     .setPositiveButton("好，去设置")
//                     .show();

//            第三种：自定义dialog样式。
//            SettingService settingService = AndPermission.defineSettingDialog(this, REQUEST_CODE_SETTING);
//            你的dialog点击了确定调用：
//            settingService.execute();
//            你的dialog点击了取消调用：
//            settingService.cancel();
            }
        }
    };

        //因此Rationale功能是在用户拒绝一次权限后，再次申请时检测到已经申请过一次该权限了，允许开发者弹窗说明申请权限的目的，
        // 获取用户的同意后再申请权限，避免用户勾选不再提示，导致不能再次申请权限。
        private RationaleListener listener2 = new RationaleListener() {
            @Override
            public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
                AlertDialog.newBuilder(MainActivity.this)
                        .setTitle("友好提醒")
                        .setMessage("你已拒绝过定位权限，沒有定位定位权限无法为你推荐附近的妹子，你看着办！")
                        .setPositiveButton("好，给你", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                rationale.resume();
                            }
                        })
                        .setNegativeButton("我拒绝", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                rationale.cancel();
                            }
                        }).show();
            }
        };

        private void setOnEvent() {
            btnClick = (Button) findViewById(R.id.btn_one);
            stateLayout = (StateLayout) findViewById(R.id.statelayout);
            recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
            btnClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //测试stateLayout默认状态显示view
//                stateLayout.showEmptyView();
//                DownloadThread thread = new DownloadThread();
//                thread.start();
                    //测试使用封装的联网框架
                    GetParameters parameters = new GetParameters.Builder()
                            .setUrl("user/get_user_info.json")
                            .setParameter("userid", "1048")
                            .build();

                    //网络访问EasyRetrofit封装应用
//                HttpManager.getInstance().get(parameters, new BaseCallback<UserInfo>() {
//                    @Override
//                    public void onStart() {
//                        Log.d("MainActivity", "TAG--start");
//                        Toast.makeText(MainActivity.this, "start==开始", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onSuccess(UserInfo s) {
//                        Toast.makeText(MainActivity.this, "获取数据成功", Toast.LENGTH_SHORT).show();
//                        Log.d("MainActivity:====", s.toString());
//                    }
//
//                    @Override
//                    public void onError(int code, String msg, Exception e) {
//                        Log.d("MainActivity", msg+"-----"+e.getMessage());
//                        Toast.makeText(MainActivity.this, "获取数据失败", Toast.LENGTH_SHORT).show();
//                    }
//                });
                }
            });
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            layoutManager.setOrientation(OrientationHelper.VERTICAL);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
            recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(new MyRecyclerViewAdapter(this,mList));//自己写的adapter
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
            MyRecycleAdapterTwo adapter = new MyRecycleAdapterTwo(MainActivity.this, R.layout.recycler_main_item, mList);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    switch (position) {
                        case 10:
                            Toast.makeText(MainActivity.this, "ButterKnife测试", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, ButterKnifeTestActivity.class));
                            break;
                        case 11:
                            Toast.makeText(MainActivity.this, "SharedPreferences测试", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, SharedTestActivity.class));
                            break;
                        case 12:
                            Toast.makeText(MainActivity.this, "SwipRefreshView测试", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, UpRefreshActivity.class));
                            break;
                        case 13:
                            Toast.makeText(MainActivity.this, "EventBus测试", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, EventTestActivity.class));
                            break;
                        case 14:
                            Toast.makeText(MainActivity.this, "高德地图使用", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(MainActivity.this,ShowMapActivity.class));
                            break;
                        case 5:
                            Log.d(TAG, "onItemClick: 5  Menu使用");
//                        startActivity(new Intent(MainActivity.this, TestActivity.class));
                            Intent intent = new Intent(MainActivity.this, TestActivity.class);
                            intent.putExtra("data", "phone:18115486953");
                            startActivityForResult(intent, 100);
                            break;
                        case 4:
                            //启动服务permission
                            Toast.makeText(MainActivity.this, "图片4被点击了", Toast.LENGTH_SHORT).show();
                            getPermission();
                            break;
                        case 0:
                            //手势密码
                            startActivity(new Intent(MainActivity.this,GestureActivity.class));
                                break;
                    }
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,5));
        }

        private void setTitleData() {
            topBar = findViewById(R.id.com_main_view);
            topBar.setTitle(false, "主页面", null);
            topBar.setRlLeft(true, null, R.mipmap.fanhui);
            topBar.setRlRight(false, "跳转", 0);
            topBar.setOnLeftClickListener(new CommonTopBar.OnLeftClickListener() {
                @Override
                public void onClickLeft() {
                    Toast.makeText(MainActivity.this, "左侧按钮被点击了", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, RecycleTest.class));
                }

                @Override
                public void onClickRight() {
                    startActivity(new Intent(MainActivity.this, Main2Activity.class));
                }

                @Override
                public void onClickTitle() {
                    topBar.setTitle(false, "点击显示", null);
                    Toast.makeText(MainActivity.this, "标题栏被点击了", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, SQLiteTestActivity.class));
                }
            });
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            switch (requestCode) {
                case 100:
                    if (resultCode == RESULT_OK) {
                        String resultData = data.getStringExtra("data_return");
                        Toast.makeText(MainActivity.this, "返回数据是：" + resultData, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case REQUEST_CODE_SETTING:
                    Toast.makeText(this, "The user came back from the settings", Toast.LENGTH_LONG).show();
                    break;
            }
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

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 100:
                        stateLayout.showEmptyView(R.string.secerror, R.mipmap.ugc_delete_last_part_hover);
                        break;
                }
            }
        };

        class DownloadThread extends Thread {
            @Override
            public void run() {
                try {
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
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onRestart() {
            super.onRestart();
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_left:
                    Toast.makeText(this, "leftbutton被点击了", Toast.LENGTH_SHORT).show();

                    break;
                case R.id.menu_right:
                    Toast.makeText(this, "rightButton被点击了", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
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
