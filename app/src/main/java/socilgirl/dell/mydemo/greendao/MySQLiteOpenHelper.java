package socilgirl.dell.mydemo.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.database.Database;

import socilgirl.dell.mydemo.greendao.gen.DaoMaster;
import socilgirl.dell.mydemo.greendao.gen.UserDao;
import socilgirl.dell.mydemo.greendao.gen.UserInfoTwoDao;

/**
 * Created by dell on 2017/10/30.
 */

public class MySQLiteOpenHelper extends DaoMaster.DevOpenHelper {

    public MySQLiteOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, final int oldVersion, final int newVersion) {
        Log.d("MySQLite", "数据库从" + oldVersion + "升级到" + newVersion);
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db,false);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db,true);
            }
        },UserDao.class, UserInfoTwoDao.class);

    }
}
