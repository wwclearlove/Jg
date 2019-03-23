package cdictv.test.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cdictv.test.bean.ClczBeen;

public class JsonObjecj {
    public static List<ClczBeen> getList(String json){
        List<ClczBeen> mlist=new ArrayList<>();
        try {
            JSONObject jsonObjec1=new JSONObject(json);
            JSONArray jsonArray=jsonObjec1.getJSONArray("data");
            for (int i = 0; i <jsonArray.length(); i++) {
                ClczBeen been=new ClczBeen();
                JSONObject jsonObject2=jsonArray.getJSONObject(i);
                been.setBh(jsonObject2.getInt("num"));
                been.setYe(jsonObject2.getInt("money"));
                mlist.add(been);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (ClczBeen been :
                mlist) {
            Log.d("json1", been.toString());
        }
        return mlist;
    }
}
