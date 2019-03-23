package cdictv.test.database;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cdictv.test.bean.CzliBeen;


public class CzliDao {
    Context context;


    //拿Hepler对象
    private static Dao<CzliBeen, ?> getHelperDao()  {
        try {
            return DatabaseHelper.getInstance().getDao(CzliBeen.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void insert1(Dao dao,int bh,String je,String ye,String name,String date){
        CzliBeen been=new CzliBeen();
        been.setBh(bh);
        been.setJe(je);
        been.setYe(ye);
        been.setName(name);
        been.setDate(date);
        try {
           dao.create(been);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insert(int bh,String je,String ye,String name,String date){
        CzliBeen been=new CzliBeen();
        been.setBh(bh);
        been.setJe(je);
        been.setYe(ye);
        been.setName(name);
        been.setDate(date);
        try {
            getHelperDao().create(been);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<CzliBeen> paixu(String arg){
        List<CzliBeen> beenlist=new ArrayList<>();
        if(arg.equals("时间升序")){

            try {
                beenlist= getHelperDao().queryBuilder().orderBy("date",true).query();
            } catch (Exception e) {
                e.printStackTrace();
            }
//            oder("date asc").find(CzliBeen.class);
        }else{
            try {
                beenlist= (List<CzliBeen>) getHelperDao().queryBuilder().orderBy("date",false).query();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  beenlist;
    }
}
