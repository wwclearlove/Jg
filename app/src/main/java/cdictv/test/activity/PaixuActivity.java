package cdictv.test.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import cdictv.test.R;
import cdictv.test.adatpter.BusQueryAdapter;
import cdictv.test.bean.GongjiaoBean;
import cdictv.test.network.MyCall;
import cdictv.test.network.NetworkApi;
import cdictv.test.network.OkhttpApi;
import cdictv.test.util.ListPaixu;

public class PaixuActivity extends AppCompatActivity {
    ExpandableListView expand_listview;
    GongjiaoBean mGongjiaoBean;
    BusQueryAdapter busQueryAdapter;
    List<GongjiaoBean.DataBean> mlist=new ArrayList<>();
    private int TIME = 3000;
    Handler mHandler=new Handler();
    Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(this,TIME);
            OkhttpApi.gongjiao(new MyCall() {
                @Override
                public void success(String json) {
//                    Log.d("公交",json);
                    mGongjiaoBean= NetworkApi.gson.fromJson(json,GongjiaoBean.class);

                    for (GongjiaoBean.DataBean data: mGongjiaoBean.data) {
                        Log.d("list1", "success: "+data.zhantai);
                        ListPaixu.gongJiaoPaixu(data.bus);
//                        for (GongjiaoBean.DataBean.BusBean bean:data.bus) {
//                            Log.d("list2", "success: "+bean.distance);
//                        }
                    }
                    mlist.clear();
                    mlist.addAll(mGongjiaoBean.data);
                    busQueryAdapter.notifyDataSetChanged();

                }
                @Override
                public void failed() {

                }
            });
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paixu);
        expand_listview = findViewById(R.id.alarm_clock_expandablelist);
//
        busQueryAdapter=new BusQueryAdapter(PaixuActivity.this,mlist);
        expand_listview.setAdapter(busQueryAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler.postDelayed(mRunnable,TIME);

    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(mRunnable);
    }
}
