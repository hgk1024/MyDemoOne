package socilgirl.dell.mydemo.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

import socilgirl.dell.mydemo.R;
import socilgirl.dell.mydemo.adapter.RecyclerviewChatAdapter;
import socilgirl.dell.mydemo.model.ChatMessage;

public class RecycleTest extends Activity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

//    @BindView(R.id.btn_recycle_one)
    Button btnRecycleOne;
//    @BindView(R.id.btn_recycle_two)
    Button btnRecycleTwo;
//    @BindView(R.id.btn_recycle_three)
    Button btnRecycleThree;
//    @BindView(R.id.btn_recycle_four)
    Button btnRecycleFour;
//    @BindView(R.id.recycler_recyclerview)
    RecyclerView recyclerRecyclerview;
//    @BindView(R.id.refresh_layouview)
    SwipeRefreshLayout swipeRefreshLayout;

    List<ChatMessage> mData = new ArrayList<>();
    private LoadMoreWrapper loadMoreWrapper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_test);
//        ButterKnife.bind(this);
        initView();

        initDataTypeOne();
    }

    private void initView() {
        recyclerRecyclerview = findViewById(R.id.recycler_recyclerview);
        swipeRefreshLayout = findViewById(R.id.refresh_layouview);
        btnRecycleFour = findViewById(R.id.btn_recycle_four);
        btnRecycleOne = findViewById(R.id.btn_recycle_one);
        btnRecycleTwo = findViewById(R.id.btn_recycle_two);
        btnRecycleThree = findViewById(R.id.btn_recycle_three);
        btnRecycleThree.setOnClickListener(this);
        btnRecycleTwo.setOnClickListener(this);
        btnRecycleOne.setOnClickListener(this);
        btnRecycleFour.setOnClickListener(this);
    }

    private void initDataTypeOne() {

        recyclerRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mData.addAll(ChatMessage.MOCK_DATAS);
        RecyclerviewChatAdapter adapter = new RecyclerviewChatAdapter(this,mData);

        HeaderAndFooterWrapper headerAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        headerAndFooterWrapper.addHeaderView(LayoutInflater.from(this).inflate(R.layout.default_loading,recyclerRecyclerview,false));
        headerAndFooterWrapper.addFootView(LayoutInflater.from(this).inflate(R.layout.default_loading,recyclerRecyclerview,false));
        loadMoreWrapper = new LoadMoreWrapper(adapter);
        loadMoreWrapper.setLoadMoreView(LayoutInflater.from(this).inflate(R.layout.default_loading, recyclerRecyclerview, false));
        loadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        boolean coming = Math.random() > 0.5;
                        ChatMessage msg = null;
                        msg = new ChatMessage(coming ? R.drawable.renma : R.drawable.xiaohei, coming ? "人马" : "xiaohei", "where are you " + mData.size(),
                                null, coming);
                        mData.add(msg);
                        loadMoreWrapper.notifyDataSetChanged();

                    }
                }, 3000);
            }
        });

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(RecycleTest.this, "item点击事件", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(RecycleTest.this, "item长按点击事件", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        recyclerRecyclerview.setAdapter(loadMoreWrapper);
        swipeRefreshLayout.setOnRefreshListener(this);
    }


    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RecycleTest.this, "下拉刷新执行", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        },2000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_recycle_one:
                break;
            case R.id.btn_recycle_two:
                break;
            case R.id.btn_recycle_three:
                break;
            case R.id.btn_recycle_four:
                break;
        }
    }
}
