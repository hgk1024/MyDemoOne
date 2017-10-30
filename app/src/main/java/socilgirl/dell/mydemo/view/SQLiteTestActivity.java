package socilgirl.dell.mydemo.view;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import socilgirl.dell.mydemo.R;
import socilgirl.dell.mydemo.greendao.GreenDaoManager;
import socilgirl.dell.mydemo.greendao.bean.UserInfoTwo;
import socilgirl.dell.mydemo.greendao.gen.DaoMaster;
import socilgirl.dell.mydemo.greendao.gen.DaoSession;
import socilgirl.dell.mydemo.greendao.gen.UserInfoTwoDao;

public class SQLiteTestActivity extends AppCompatActivity {

    @BindView(R.id.et_userid)
    EditText etUserid;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_userphone)
    EditText etUserphone;
    @BindView(R.id.et_uspassworld)
    EditText etUspassworld;
//    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_ualter)
    Button btnUalter;
    @BindView(R.id.btn_check)
    Button btnCheck;
    @BindView(R.id.btn_adupdate)
    Button btnAdupdate;
    @BindView(R.id.tv_show_result)
    TextView tvShowResult;
    private String userid;
    private String userName;
    private String userPhone;
    private String passWord;
    private UserInfoTwoDao userDao;
    private UserInfoTwo userTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_test);
        ButterKnife.bind(this);
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userid = etUserid.getText().toString();
                userName = etUsername.getText().toString();
                userPhone = etUserphone.getText().toString();
                passWord = etUspassworld.getText().toString();
                if (isNotEmpty(userid) &&isNotEmpty(passWord)){
                    QueryBuilder qb = userDao.queryBuilder();
                    ArrayList<UserInfoTwo> list = (ArrayList<UserInfoTwo>) qb.where(UserInfoTwoDao.Properties.Userid.eq(userid)).list();
                    if (list.size()>0){
                        Toast.makeText(SQLiteTestActivity.this, "组建重复", Toast.LENGTH_SHORT).show();
                    }else{
                        userDao.insert(new UserInfoTwo(Long.valueOf(userid),userName,userPhone,passWord));
                        Toast.makeText(SQLiteTestActivity.this, "插入数据成功", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if (isEmpty(userid) && isNotEmpty(userName)) {
                        Toast.makeText(SQLiteTestActivity.this, "id为空", Toast.LENGTH_SHORT).show();
                    }
                    if (isEmpty(userName) && isNotEmpty(userid)) {
                        Toast.makeText(SQLiteTestActivity.this, "姓名为空", Toast.LENGTH_SHORT).show();
                    }
                    if (isEmpty(userid) && isEmpty(userName)) {
                        Toast.makeText(SQLiteTestActivity.this, "请填写信息", Toast.LENGTH_SHORT).show();
                    }

                }
                etUserid.setText("");
                etUsername.setText("");
                etUserphone.setText("");
                etUspassworld.setText("");
            }
        });
        initDbHelp();

    }

    private void initDbHelp() {
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "userinfotwo-db", null);
//        SQLiteDatabase db = helper.getWritableDatabase();
//        DaoMaster daoMaster = new DaoMaster(db);
//        DaoSession daoSession = daoMaster.newSession();
//        userDao = daoSession.getUserInfoTwoDao();
        userDao = GreenDaoManager.getInstance().getNewSession().getUserInfoTwoDao();
    }


    @OnClick({R.id.btn_add, R.id.btn_delete, R.id.btn_ualter, R.id.btn_check, R.id.btn_adupdate})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.btn_add:
//                setData();
                userid = etUserid.getText().toString();
                userName = etUsername.getText().toString();
                userPhone = etUserphone.getText().toString();
                passWord = etUspassworld.getText().toString();
                if (isNotEmpty(userid) &&isNotEmpty(passWord)){
                    QueryBuilder qb = userDao.queryBuilder();
                    ArrayList<UserInfoTwo> list = (ArrayList<UserInfoTwo>) qb.where(UserInfoTwoDao.Properties.Userid.eq(userid)).list();
                    if (list.size()>0){
                        Toast.makeText(this, "组建重复", Toast.LENGTH_SHORT).show();
                    }else{
                        userDao.insert(new UserInfoTwo(Long.valueOf(userid),userName,userPhone,passWord));
                        Toast.makeText(this, "插入数据成功", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if (isEmpty(userid) && isNotEmpty(userName)) {
                        Toast.makeText(this, "id为空", Toast.LENGTH_SHORT).show();
                    }
                    if (isEmpty(userName) && isNotEmpty(userid)) {
                        Toast.makeText(this, "姓名为空", Toast.LENGTH_SHORT).show();
                    }
                    if (isEmpty(userid) && isEmpty(userName)) {
                        Toast.makeText(this, "请填写信息", Toast.LENGTH_SHORT).show();
                    }

                }
                etUserid.setText("");
                etUsername.setText("");
                etUserphone.setText("");
                etUspassworld.setText("");
//                userDao.insert(userTwo);
//                tvShowResult.setText(userDao.load(userid).toString());
                break;
            case R.id.btn_delete:
//                setData();
                userDao.delete(userTwo);
//                userDao.deleteAll();
                break;
            case R.id.btn_ualter:
//                setData();
                userDao.update(userTwo);
                break;
            case R.id.btn_check:
//                setData();
                userDao.load(Long.valueOf(userid));
                break;
            case R.id.btn_adupdate:
                break;
        }
    }

    private void setData() {
        userDao = GreenDaoManager.getInstance().getNewSession().getUserInfoTwoDao();
        userTwo = new UserInfoTwo(Long.valueOf(userid),userName,userPhone,passWord);
        userid = etUserid.getText().toString();
        userName = etUsername.getText().toString();
        userPhone = etUserphone.getText().toString();
        passWord = etUspassworld.getText().toString();
    }
    private boolean isNotEmpty(String s) {
        if (s != null && !s.equals("") || s.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isEmpty(String s) {
        if (isNotEmpty(s)) {
            return false;
        } else {
            return true;
        }
    }

}
