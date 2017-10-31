package socilgirl.dell.mydemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import socilgirl.dell.mydemo.R;
/**原先butterknife编译不通过---报错
 * 添加了：
 * javaCompileOptions {//butterknife添加配置 使java中兼容compile和implementation
        annotationProcessorOptions {
        includeCompileClasspath = true
        }
    }
 * */
public class ButterKnifeTestActivity extends AppCompatActivity {

    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button)
    Button button;
    //logt
    private static final String TAG = "ButterKnifeTestActivity";
//    const
    private static final int DATE = 839;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button2, R.id.editText3,R.id.button4, R.id.button3, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button2:
                Toast.makeText(this, "按钮2被点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.editText3:
                //todo 填写EditView的点击事件
                // TODO-HOU: 2017/10/31 此处有可能要修改
                break;

            case R.id.button4:
                Toast.makeText(this, "按钮4被点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                Toast.makeText(this, "按钮3被点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button:
                Toast.makeText(this, "按钮被点击了", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
