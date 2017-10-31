package socilgirl.dell.mydemo.greendao;

import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;

import socilgirl.dell.mydemo.MyApplication;
import socilgirl.dell.mydemo.greendao.gen.DaoMaster;
import socilgirl.dell.mydemo.greendao.gen.DaoSession;

/**
 * Created by dell on 2017/10/30.
 */

public class GreenDaoManager
{
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private SQLiteDatabase db;

    private static volatile GreenDaoManager mInstance = null;

    private GreenDaoManager(){
        if (mInstance == null) {
            // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
            // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
            // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
            // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。

            //原先默认更新，会丢失原先数据库的数据
//            DaoMaster.DevOpenHelper devOpenHelper = new
//                    DaoMaster.DevOpenHelper(MyApplication.getAppContext(), "socriluser.db",null);

            //使用更新数据库jar包：GreenDaoUpgradeHelper，实现了先复制原先的数据库数据，再清除原数据表，新建新的数据表，将存的数据添加到新的数据表中
            //不会使数据丢失
            MigrationHelper.DEBUG = false;
            MySQLiteOpenHelper devOpenHelper = new MySQLiteOpenHelper(MyApplication.getAppContext(),
                    "socriluser.db",null);//数据库更新时用到
            db = devOpenHelper.getWritableDatabase();
            // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
            mDaoMaster = new DaoMaster(db);
//            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }
    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

    public SQLiteDatabase getDb(){
        return db;
    }

}
