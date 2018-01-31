package socilgirl.dell.mydemo.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import socilgirl.dell.mydemo.R;
//手机验证得到重设手势密码的权限
public class VerificationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        findViewById(R.id.btn_setpassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerificationActivity.this,GestureActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction()==KeyEvent.KEYCODE_BACK){
            Toast.makeText(this, "再次点击退出程序", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
