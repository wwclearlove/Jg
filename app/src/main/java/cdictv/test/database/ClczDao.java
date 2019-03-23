package cdictv.test.database;

import com.j256.ormlite.dao.Dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cdictv.test.bean.ClczBeen;

public class ClczDao implements Serializable{


    //拿Hepler对象
    private static Dao<ClczBeen, ?> getHelperDao()  {
        try {
            return DatabaseHelper.getInstance().getDao(ClczBeen.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void insertnet(ClczBeen been) {
        try {
            getHelperDao().update(been);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static List<ClczBeen> add(){
        List<ClczBeen> beenlist=new ArrayList<>();
        try {
            beenlist= getHelperDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  beenlist;
    }
    public static void savall(List<ClczBeen> mlist){
        try {
            getHelperDao().create(mlist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


