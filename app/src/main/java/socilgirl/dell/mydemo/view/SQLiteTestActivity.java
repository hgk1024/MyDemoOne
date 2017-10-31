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
import java.util.List;

import socilgirl.dell.mydemo.R;
import socilgirl.dell.mydemo.greendao.GreenDaoManager;
import socilgirl.dell.mydemo.greendao.bean.UserInfoTwo;
import socilgirl.dell.mydemo.greendao.gen.UserInfoTwoDao;

public class SQLiteTestActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUserid;
    EditText etUsername;
    EditText etUserphone;
    EditText etUspassworld;
    Button btnAdd;
    Button btnDelete;
    Button btnUalter;
    Button btnCheck;
    Button btnAdupdate;
    TextView tvShowResult;
    private String userid;
    private String userName;
    private String userPhone;
    private String passWord;
    private long id;
    private UserInfoTwoDao userDao;
    private UserInfoTwo userTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_test);
//        ButterKnife.bind(this);
        initDbHelp();
        initView();
        initData();
    }

    private void initView() {
        btnAdd = findViewById(R.id.btn_add);
        btnAdupdate = findViewById(R.id.btn_adupdate);
        btnCheck = findViewById(R.id.btn_check);
        btnDelete = findViewById(R.id.btn_delete);
        btnUalter = findViewById(R.id.btn_ualter);
        etUserid = findViewById(R.id.et_userid);
        etUsername = findViewById(R.id.et_username);
        etUserphone = findViewById(R.id.et_userphone);
        etUspassworld = findViewById(R.id.et_uspassworld);
        tvShowResult = findViewById(R.id.tv_show_result);

    }

    private void initData() {
        btnAdd.setOnClickListener(this);
        btnUalter.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCheck.setOnClickListener(this);
        btnAdupdate.setOnClickListener(this);
    }

    private void initDbHelp() {
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "userinfotwo-db", null);
//        SQLiteDatabase db = helper.getWritableDatabase();
//        DaoMaster daoMaster = new DaoMaster(db);
//        DaoSession daoSession = daoMaster.newSession();
//        userDao = daoSession.getUserInfoTwoDao();
        userDao = GreenDaoManager.getInstance().getNewSession().getUserInfoTwoDao();
    }


//    @OnClick({R.id.btn_add, R.id.btn_delete, R.id.btn_ualter, R.id.btn_check, R.id.btn_adupdate})
//    public void OnClick(View view) {
//    }

    private void setData() {
        userDao = GreenDaoManager.getInstance().getNewSession().getUserInfoTwoDao();
        userTwo = new UserInfoTwo(id,userid,userName,userPhone,passWord);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
//                setData();
//                userid = etUserid.getText().toString();
//                userName = etUsername.getText().toString();
//                userPhone = etUserphone.getText().toString();
//                passWord = etUspassworld.getText().toString();
//                if (isNotEmpty(userid) && isNotEmpty(passWord)){
//                    QueryBuilder qb = userDao.queryBuilder();
//                    ArrayList<UserInfoTwo> list = (ArrayList<UserInfoTwo>) qb.where(UserInfoTwoDao.Properties.Id.eq(id)).list();
//                    if (list.size()>0){
//                        Toast.makeText(this, "组建重复", Toast.LENGTH_SHORT).show();
//                    }else{
//                        userDao.insert(new UserInfoTwo(id,userid,userName,userPhone,passWord));
//                        Toast.makeText(this, "插入数据成功", Toast.LENGTH_SHORT).show();
//                    }
//                }else{
//                    if (isEmpty(userid) && isNotEmpty(userName)) {
//                        Toast.makeText(this, "id为空", Toast.LENGTH_SHORT).show();
//                    }
//                    if (isEmpty(userName) && isNotEmpty(userid)) {
//                        Toast.makeText(this, "姓名为空", Toast.LENGTH_SHORT).show();
//                    }
//                    if (isEmpty(userid) && isEmpty(userName)) {
//                        Toast.makeText(this, "请填写信息", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//                etUserid.setText("");
//                etUsername.setText("");
//                etUserphone.setText("");
//                etUspassworld.setText("");
//                userDao.insert(userTwo);
//                tvShowResult.setText(userDao.load(userid).toString());

                userid = etUserid.getText().toString();
                id = Long.valueOf(userid);
                userName = etUsername.getText().toString();
                userPhone = etUserphone.getText().toString();
                passWord = etUspassworld.getText().toString();
                if (isNotEmpty(userid) &&isNotEmpty(passWord)){
                    QueryBuilder qb = userDao.queryBuilder();
                    ArrayList<UserInfoTwo> list = (ArrayList<UserInfoTwo>) qb.where(UserInfoTwoDao.Properties.Id.eq(id)).list();
                    if (list.size()>0){
                        Toast.makeText(SQLiteTestActivity.this, "组件重复", Toast.LENGTH_SHORT).show();
                    }else{
                        userDao.insert(new UserInfoTwo(id,userid,userName,userPhone,passWord));
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
                break;
            case R.id.btn_delete:
//                setData();
//                userDao.delete(userTwo);
//                userDao.deleteAll();
                List<UserInfoTwo> list = userDao.queryBuilder().build().list();
                if (list.size()>0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (i == 0) {
                            userDao.deleteByKey(list.get(0).getId());//通过Id来删除数据
//                        userDao.delete(list.get(0));//通过传入实体类的实例删除数据
                            Toast.makeText(this, "删除数据成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case R.id.btn_ualter:
//                setData();
//                userDao.update(userTwo);
                List<UserInfoTwo> list1 = userDao.queryBuilder().build().list();
                if (list1.size()>0) {
                    for (int j = 0; j < list1.size(); j++) {
                        if (j == 0) {
                            list1.get(0).setUserName("你好");
                            list1.get(0).setUserPhone("159648568995");
                            userDao.update(list1.get(0));
                        }
                    }
                }
                break;
            case R.id.btn_check:
//                setData();
//                userDao.load(Long.valueOf(userid));
                userName = etUsername.getText().toString();
                List<UserInfoTwo> list2 = userDao.queryBuilder()
//                        .offset(1)//偏移量，相当于 SQL 语句中的 skip
//                        .limit(3)//只获取结果集的前 3 个数据
                        .orderAsc(UserInfoTwoDao.Properties.Id)//通过 id 这个属性进行正序排序
                        .where(UserInfoTwoDao.Properties.UserName.eq(userName))//数据筛选，只获取 Name = "zone" 的数据。
                        .build()
                        .list();
                if (list2.size()>0) {
                    for (int h=0;h<list2.size();h++) {
                        if (h == 0) {
                            etUspassworld.setText(list2.get(0).getPassWord());
                            etUserphone.setText(list2.get(0).getUserPhone());
                            etUsername.setText(list2.get(0).getUserName());
                            etUserid.setText(list2.get(0).getUserid());
                        }
                    }
                }else{
                    Toast.makeText(this, "没有你要找的数据", Toast.LENGTH_SHORT).show();
                }
                tvShowResult.setText(list2.size() + "");
                break;
            case R.id.btn_adupdate:
                break;
        }
    }
}
