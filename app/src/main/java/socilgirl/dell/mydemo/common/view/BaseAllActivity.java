package socilgirl.dell.mydemo.common.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;

import com.sina.weibo.sdk.constant.WBConstants;

import socilgirl.dell.mydemo.R;
import socilgirl.dell.mydemo.view.GestureActivity;

public class BaseAllActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_all);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        Intent intent = new Intent(this, GestureActivity.class);
//        intent.putExtra("type",2);
//        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (event.getAction()){
            case KeyEvent.KEYCODE_HOME:
                Toast.makeText(this, "HOME键被点击了", Toast.LENGTH_SHORT).show();
                break;
            case KeyEvent.KEYCODE_BACK:
                Toast.makeText(this, "BACK返回键被点击了", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    //其他键按键方法
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
//        if (android.os.Build.VERSION.SDK_INT > 14){
//            this.getWindow().setType(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
//        }
    }
}
