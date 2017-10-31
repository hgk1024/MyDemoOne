package socilgirl.dell.mydemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import socilgirl.dell.mydemo.R;

/**
 * Create by "hou"
 *
 * @date 2017/10/31
 * 练习ShredPreference存储
 */
public class SharedTestActivity extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.et_sex)
    EditText etSex;
    @BindView(R.id.btn_save_data)
    Button btnSaveData;
    @BindView(R.id.btn_show_data)
    Button btnShowData;
    @BindView(R.id.btn_delete_data)
    Button btnDeleteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_save_data, R.id.btn_show_data, R.id.btn_delete_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save_data:
                break;
            case R.id.btn_show_data:
                break;
            case R.id.btn_delete_data:
                break;
        }
    }
}
