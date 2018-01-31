package socilgirl.dell.mydemo.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import socilgirl.dell.mydemo.R;

/**
 * Created by Administrator on 2018/1/30/030.
 *
 * 有问题：侧滑时出现mCurrentView为空
 */

public class QQListView extends ListView {

    private int touchSlop;//滑动最小距离
    private boolean isSliding;//是否相应滑动
    private int xDown;
    private int yDown;
    private int xMove;
    private int yMove;
    private LayoutInflater mInflater;
    private PopupWindow mPopupWindows;
    private int mPopupWindowsHeight;
    private int mPopupWindowsWeight;
    private Button mDelBtn;

    private DelButtonClickListener mListener;

    private View mCurrentView;//当前手指触摸的View
    private int mCurrentViewPos;//当前手指触摸的位置

    //进行必要的初始化
    public QQListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        View view = mInflater.inflate(R.layout.net_view_item,null);
        mDelBtn = view.findViewById(R.id.id_item_btn);
        mPopupWindows = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        /**
        * 先调用下measure，否则拿不到宽和高
        * */

        mPopupWindows.getContentView().measure(0,0);
        mPopupWindowsHeight = mPopupWindows.getContentView().getMeasuredHeight();
        mPopupWindowsWeight = mPopupWindows.getContentView().getMeasuredWidth();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                xDown = x;
                yDown = y;
                //*如果当前popupWindow显示，则直接隐藏，然后屏蔽ListView的touch事件的下传
                if (mPopupWindows.isShowing()){
                    dismisspopWiondow();
                    return false;
                }
                //获得手指落下时item的位置
                mCurrentViewPos = pointToPosition(xDown,yDown);
                //获得当手指按下时的item
                View view = getChildAt(mCurrentViewPos - getFirstVisiblePosition());
                mCurrentView = view;
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = x;
                yMove = y;
                int dx = xMove - xDown;
                int dy = yMove - yDown;
                /**
                 * 判断是否是从右到左的滑动
                 */
                if (xMove < xDown && Math.abs(dx) > touchSlop && Math.abs(dy) < touchSlop)
                {
                    // Log.e(TAG, "touchslop = " + touchSlop + " , dx = " + dx +
                    // " , dy = " + dy);
                    isSliding = true;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (isSliding){
            switch (action){
                case MotionEvent.ACTION_MOVE:
                    int[] loction = new int[2];
                    //获得当前item的位置
                    mCurrentView.getLocationOnScreen(loction);
                    //设置popupWindow的动画
                    mPopupWindows.setAnimationStyle(R.style.umeng_socialize_popup_dialog);
                    mPopupWindows.update();
                    mPopupWindows.showAtLocation(mCurrentView, Gravity.LEFT | Gravity.TOP,
                            loction[0] + mCurrentView.getWidth(), loction[1] + mCurrentView.getHeight() / 2
                                    - mPopupWindowsWeight / 2);
                    //设置按钮的回调
                    mDelBtn.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mListener!=null){
                                mListener.clickHappend(mCurrentViewPos);
                                mPopupWindows.dismiss();
                            }
                        }
                    });
                    break;
                case MotionEvent.ACTION_UP:
                    isSliding = false;
                    break;
            }
        }
        return true;
    }

    private void dismisspopWiondow() {
        if (mPopupWindows!=null&& mPopupWindows.isShowing()){
            mPopupWindows.dismiss();
        }
    }

    public void setDelButtonClickListener(DelButtonClickListener listener)
    {
        mListener = listener;
    }

    public interface DelButtonClickListener
    {
        public void clickHappend(int position);
    }
}
