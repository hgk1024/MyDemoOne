package socilgirl.dell.mydemo.weight;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;

import socilgirl.dell.mydemo.R;

/**
 * Create by "hou" on 2017/11/1.
 * 实现listView上拉加载应该可以，recycleView应该不行
 */

public class UpandDownRefreshLayout extends SwipeRefreshLayout {
    private static final String TAG = "UpandDownRefreshLayout";

    private OnLoadMoreListener moreListener;
    private ListView mListView;
    private RecyclerView recyclerView;
    private View mFootView;
    private int mScaledTouchSlop;
    private int mItemCount;
    private boolean isLoading;

    public UpandDownRefreshLayout(Context context) {
        super(context);
        initView(context);
    }

    public UpandDownRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    //上拉加载更多接口
    public interface OnLoadMoreListener{
        void onLoadMore();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener){
        this.moreListener = listener;

    }

    private void initView(Context context) {
        //填充底部加载位置
        mFootView = View.inflate(context,R.layout.refresh_foot_view,null);
        //设置触发加载更多事件的最短位置
        mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();//16
        Log.d(TAG, "默认的执行加载距离为" + mScaledTouchSlop);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mListView ==null || recyclerView ==null){
            if (getChildCount()>0){
                if (getChildAt(0) instanceof ListView) {
                    mListView = (ListView) getChildAt(0);
                    setListViewOnScroll();//设置list View滑动监听事件
                }else if (getChildAt(0) instanceof RecyclerView){
                    recyclerView = (RecyclerView) getChildAt(0);
                    setRecyclerOnScroll();
                }
            }
        }
    }
    /**
     * 在分发事件的时候处理子控件的触摸事件
     */
    private float mDownY, mUpY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();//开始滑动Y坐标
                break;
            case MotionEvent.ACTION_MOVE:
                //移动过程中判断是否能够加载更多
                if (canoadMore()){
                    loadData();
                }
                break;
            case MotionEvent.ACTION_UP:
                mUpY = ev.getY();//结束滑动Y坐标
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    //判断执行加载更多的条件
    private boolean canoadMore() {
        //确定是上来状态
        boolean conditon1 = mDownY - mUpY >= mScaledTouchSlop;
//boolean conditon1 = true;
        if (conditon1){
            Log.d(TAG, "=============================>是上拉状态");
        }
        //
        boolean conditon2 = false;
        if (mListView != null && mListView.getAdapter() != null) {
//            Log.d(TAG, "该判断被执行了");
            if (mItemCount > 0) {
                if (mListView.getAdapter().getCount() < mItemCount) {
//                    Log.d(TAG, "第一页未满，禁止上拉加载");
                    // 第一页未满，禁止下拉
                    conditon2 = false;
                }else {
//                    Log.d(TAG, "第一页数据是最后一项，可以执行上拉加载更多");
                    conditon2 = mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
                }
            } else {
                // 未设置数据长度，则默认第一页数据不满时也可以上拉
//                Log.d(TAG, "未设置数据长度，则默认第一页数据不满时也可以上拉");
                conditon2 = mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
            }

        }
        // 3. 正在加载状态
        boolean condition3 = !isLoading;
        if (condition3) {
//            Log.d(TAG, "------->  不是正在加载状态");
        }
        return conditon1 && conditon2 && condition3;
    }

    //执行加载更多
    private void loadData() {
        if (moreListener != null) {
            Log.d(TAG, "loadData（）方发被执行了");
            isLoadingMore(true);//设置加载状态
            moreListener.onLoadMore();
        }
    }

    public void setItemCount(int itemCount){
        this.mItemCount = itemCount;
    }

    private void setListViewOnScroll() {
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (canoadMore()){
                    Log.d(TAG, "符合加载更多的条件");
                    loadData();
                }
                Log.d(TAG, "listView 滑动方法on---ScrollStateChanged()正在被执行");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                Log.d(TAG, "listView 滑动方法onScroll()正在被执行");
            }
        });
    }

    private void setRecyclerOnScroll() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //移动过程中判断什么时候进行上拉加载更多
                if(canoadMore()){
                    loadData();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public void isLoadingMore(boolean isLoad){
        isLoading = isLoad;
        Log.d(TAG, "isLoading值为true");
        if (isLoading){
            mListView.addFooterView(mFootView);
        }else{
            Log.d(TAG, "isLoading值为false");
            mListView.removeFooterView(mFootView);
        }
        // 重置滑动的坐标
        mDownY = 0;
        mUpY = 0;
    }
}
