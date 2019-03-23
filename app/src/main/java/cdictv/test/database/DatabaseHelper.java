package cdictv.test.database;

import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import cdictv.test.App;
import cdictv.test.bean.ClczBeen;
import cdictv.test.bean.CzliBeen;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    //1.私有构造函数
    private DatabaseHelper() {
        //参数1：上下文 参数2：数据库的名称 参数3：游标 参数4 数据库的版本
        super(App.INSTANCE, "test.db", null, 1);
    }

    //2.私有静态的对象
    private static DatabaseHelper sHeler = null;

    //3.共有静态的获取对象实例的方法
    public static synchronized DatabaseHelper getInstance() {
        if (sHeler == null) {
            sHeler = new DatabaseHelper();
        }
        return sHeler;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        //建表的方法
        try {
            //参数1：连接资源 参数2：定义好的实体类
            TableUtils.createTable(connectionSource, ClczBeen.class);
            TableUtils.createTable(connectionSource, CzliBeen.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果再建表，可以通过上面的方法，在写一个实体类就行了
    }

    //升级数据库的方法
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        //1如果数据库升级，要先删除之前的表
        try {
            //参数1：连接资源，参数2：实体类 参数3：忽略错误
            TableUtils.dropTable(connectionSource, ClczBeen.class, true);

            //2.重新执行oncreate
            onCreate(database, connectionSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
