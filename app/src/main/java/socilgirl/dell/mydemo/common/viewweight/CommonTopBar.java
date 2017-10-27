package socilgirl.dell.mydemo.common.viewweight;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import socilgirl.dell.mydemo.R;


/**
 * Created by dell on 2017/10/26.
 */

public class CommonTopBar extends FrameLayout implements View.OnClickListener {
    private static final String TAG = CommonTopBar.class.getSimpleName();

    RelativeLayout rlLeft;
    TextView tvTitle;
    RelativeLayout rlRight;
    ImageView ivCommonLeft;
    TextView tvCommonLeft;
    ImageView ivCommonRight;
    TextView tvCommonRight;
    RelativeLayout rlTitle;
    FrameLayout flTitle;

    String textLeft;
    String textRight;
    String textTitle;
    int ivLeft;
    int ivRight;


    private Activity mContext;
    OnLeftClickListener listener;
//    SetOnRightClickListener rightClickListener;
//    SetOnTitleClickListener titleClickListener;

    public CommonTopBar(Context context,AttributeSet attrs) {
        super(context, attrs);
        this.mContext = (Activity) context;
        View view = mContext.getLayoutInflater().inflate(R.layout.title_common, this, true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CommonTopBar);
        textLeft = typedArray.getString(R.styleable.CommonTopBar_textLeft);
        textRight = typedArray.getString(R.styleable.CommonTopBar_textright);
        textTitle = typedArray.getString(R.styleable.CommonTopBar_textTitle);
        ivLeft = typedArray.getInteger(R.styleable.CommonTopBar_imgLeft,0);
        ivLeft = typedArray.getInteger(R.styleable.CommonTopBar_imaRight,0);
        typedArray.recycle();
        initView(view);
    }

    private void initView(View view) {
        rlLeft = view.findViewById(R.id.rl_common_left);
        rlRight = view.findViewById(R.id.rl_common_right);
        rlTitle = view.findViewById(R.id.rl_common_title);
        tvTitle = view.findViewById(R.id.tv_common_title);
        flTitle = view.findViewById(R.id.fl_common_title);
        tvCommonRight = view.findViewById(R.id.tv_common_right);
        tvCommonLeft = view.findViewById(R.id.tv_common_left);
        ivCommonRight = view.findViewById(R.id.iv_common_right);
        ivCommonLeft = view.findViewById(R.id.iv_common_left);
        rlTitle.setOnClickListener(this);
        rlRight.setOnClickListener(this);
        rlLeft.setOnClickListener(this);
    }


//    public void setTitle(String tv){
//        rlTitle.setVisibility(VISIBLE);
//        tvTitle.setVisibility(VISIBLE);
//        flTitle.setVisibility(GONE);
//        tvTitle.setText(tv);
//    }
    //flag:true图片显示，文字隐藏，false图片隐藏，文字显示
    //str:textView赋值
    //a：ImageView显示图片
    public void setRlLeft(boolean flag,String str,int a){
        if (flag){
            ivCommonLeft.setVisibility(VISIBLE);
            tvCommonLeft.setVisibility(GONE);
            ivCommonLeft.setImageResource(a);
        }else{
            ivCommonLeft.setVisibility(GONE);
            tvCommonLeft.setVisibility(VISIBLE);
            tvCommonLeft.setText(str);
        }
    }
    //flag:true图片显示，文字隐藏，false图片隐藏，文字显示
    //str:textView赋值
    //a：ImageView显示图片
    public void setRlRight(boolean flag,String str,int a){
        if (flag){
            ivCommonRight.setVisibility(VISIBLE);
            tvCommonRight.setVisibility(GONE);
            ivCommonRight.setImageResource(a);
        }else{
            ivCommonRight.setVisibility(GONE);
            tvCommonRight.setVisibility(VISIBLE);
            tvCommonRight.setText(str);
        }
    }

    //flag:true图片显示，文字隐藏，false图片隐藏，文字显示
    //str:textView赋值
    //a：ImageView显示图片
    public void setTitle(boolean flag,String str,View view){
        if (flag){
            flTitle.setVisibility(VISIBLE);
            tvTitle.setVisibility(GONE);
            flTitle.addView(view);
        }else{
            flTitle.setVisibility(GONE);
            tvTitle.setVisibility(VISIBLE);
            tvTitle.setText(str);
        }
    }
    public void setLeftVisibale(boolean boo){
        if (boo){
            rlLeft.setVisibility(VISIBLE);
        }else{
            rlLeft.setVisibility(GONE);
        }
    }
    public void setRightVisibale(boolean boo){
        if (boo){
            rlRight.setVisibility(VISIBLE);
        }else{
            rlRight.setVisibility(GONE);
        }
    }
    public void setTitleVisibale(boolean boo){
        if (boo){
            rlTitle.setVisibility(VISIBLE);
        }else{
            rlTitle.setVisibility(GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_common_left:
                listener.onClickLeft();
                break;
            case R.id.rl_common_title:
                listener.onClickTitle();
                break;
            case R.id.rl_common_right:
                listener.onClickRight();
                break;
        }
    }

    public void setOnLeftClickListener(OnLeftClickListener listener){
        this.listener = listener;
    }

    public interface OnLeftClickListener{
        void onClickLeft();
        void onClickRight();
        void onClickTitle();
    }
}
