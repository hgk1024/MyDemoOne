package socilgirl.dell.mydemo.view;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import socilgirl.dell.mydemo.R;
import socilgirl.dell.mydemo.adapter.MyAdapter;
import socilgirl.dell.mydemo.datapackage.DataResource;
import socilgirl.dell.mydemo.weight.UpandDownRefreshLayout;

/**
 * Create by "hou"
 * @date 2017/11/1
 * 测试自定义UpandDownRefreshLayout是否可用
 */
public class UpRefreshActivity extends AppCompatActivity {
    private UpandDownRefreshLayout refreshLayout;
    private ListView listView;
    private List<String> mList;
    private MyAdapter adapter;
    private boolean isFrist = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_refresh);
        initView();
    }

    private void initView() {
        refreshLayout = findViewById(R.id.up_refreshlayout);
        listView = findViewById(R.id.list_view_upActivity);


        mList = new ArrayList<String>();
//        mList = DataResource.getData();
        adapter = new MyAdapter(mList,this);
        listView.setAdapter(adapter);
        refreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        //设置下拉进度主题色
        refreshLayout.setColorSchemeResources(R.color.colorAccent,
                android.R.color.holo_blue_bright, R.color.colorPrimaryDark,
                android.R.color.holo_orange_dark, android.R.color.holo_red_dark,
                android.R.color.holo_purple);
        refreshLayout.setItemCount(10);
        refreshLayout.measure(0,0);//手动调用，通知系统去测量
        initEvent();
        if (isFrist) {//第一次加载数据不执行延时操作
            initData();
        }
    }

    private void initEvent() {
        refreshLayout.setOnLoadMoreListener(new UpandDownRefreshLayout.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.d("UpRefreshActivity", "上拉加载更多被执行了");
                loadMoreData();
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("UpRefreshActivity", "下拉加载被执行了");
                initData();
            }
        });
    }

    private void loadMoreData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mList.clear();//因为DataResource中的方法返回的集合已经添加了所有的数据，所以这里要进行清空
                mList.addAll(DataResource.getMoreData());
                adapter.notifyDataSetChanged();

                // 加载完数据设置为不加载状态，将加载进度收起来
                refreshLayout.isLoadingMore(false);
            }
        },3000);
    }

    private void initData() {
        if (isFrist){
            //isFrist==true时不写延时，若有延时的话，会使第一次加载数据也显示refresh刷新标志
            isFrist = false;
            mList.clear();
            mList.addAll(DataResource.getData());
            adapter.notifyDataSetChanged();
            if (refreshLayout.isRefreshing()) {//加载完数据后，收起进度显示
                refreshLayout.setRefreshing(false);
            }
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mList.clear();
                    mList.addAll(DataResource.getData());
                    adapter.notifyDataSetChanged();
                    if (refreshLayout.isRefreshing()) {//加载完数据后，收起进度显示
                        refreshLayout.setRefreshing(false);
                    }
                }
            }, 3000);
        }
    }
}
