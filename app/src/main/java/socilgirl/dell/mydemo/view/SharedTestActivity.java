package socilgirl.dell.mydemo.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import socilgirl.dell.mydemo.R;
import socilgirl.dell.mydemo.sharedprefrence.IPreference;
import socilgirl.dell.mydemo.sharedprefrence.PreferenceImpl;

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
    @BindView(R.id.check_boy)
    CheckBox checkBoy;
    @BindView(R.id.btn_save_data)
    Button btnSaveData;
    @BindView(R.id.btn_show_data)
    Button btnShowData;
    @BindView(R.id.btn_delete_data)
    Button btnDeleteData;
    @BindView(R.id.show_test)
    Button btnShowTest;
    @BindView(R.id.tv_show_test)
    TextView tvShowTest;

    private IPreference preferencesImpl;
    private String name;
    private String age;
    private boolean isBoy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_test);
        ButterKnife.bind(this);
        preferencesImpl = new PreferenceImpl(this, "dataInfo");


        checkBoy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isBoy = false;
                } else {
                    isBoy = true;
                }
            }
        });
    }

    @OnClick({R.id.btn_save_data, R.id.btn_show_data, R.id.btn_delete_data, R.id.show_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save_data:
                name = etName.getText().toString();
                age = etAge.getText().toString();
                preferencesImpl.put("name", name);
                preferencesImpl.put("age", age);
                preferencesImpl.put("isBoy", isBoy);
                etName.setText("");
                etAge.setText("");
                if (checkBoy.isChecked()) {
                    checkBoy.setChecked(false);
                }
                break;
            case R.id.btn_show_data:
                if (preferencesImpl.contains("name"))
                    name = preferencesImpl.get("name", IPreference.DataType.STRING);
                if (preferencesImpl.contains("age"))
                    age = preferencesImpl.get("age", IPreference.DataType.STRING);
                if (preferencesImpl.contains("isBoy"))
                    isBoy = preferencesImpl.get("isBoy", IPreference.DataType.BOOLEAN);

                etAge.setText(age);
                etName.setText(name);
                if (isBoy) {
                    checkBoy.setChecked(false);
                } else {
                    checkBoy.setChecked(true);
                }

                break;
            case R.id.btn_delete_data:
                preferencesImpl.clear();
                etName.setText("");
                etAge.setText("");
                if (checkBoy.isChecked()) {
                    checkBoy.setChecked(false);
                }
                break;
            case R.id.show_test:
                SharedPreferences preferences = getSharedPreferences("dataInfo",MODE_PRIVATE);
                String dataName = preferences.getString("name",null);
                String dataAge = preferences.getString("age",null);
                boolean isboy = preferences.getBoolean("isBoy",false);
                String sex;
                if (isboy){
                    sex = "男";
                }else{
                    sex = "女";
                }
                tvShowTest.setText("姓名："+dataName+"; 年龄："+dataAge+"; "+sex);
                break;
        }
    }
}
