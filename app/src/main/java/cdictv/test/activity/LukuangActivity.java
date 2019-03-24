package cdictv.test.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cdictv.test.R;
import cdictv.test.bean.LukuangBean;
import cdictv.test.network.MyCall;
import cdictv.test.network.NetworkApi;
import cdictv.test.network.OkhttpApi;

public class LukuangActivity extends AppCompatActivity {
    private RelativeLayout toolBar;
    private ImageView leftMenu;
    private TextView title;
    private TextView etPm;
    private TextView etWd;
    private TextView etSd;
    private TextView etAddress1;
    private TextView etAddress2;
    private TextView etAddress3;
    private LinearLayout liner1;
    private LinearLayout liner2;
    private LinearLayout liner3;
    LukuangBean mLukuangBean;
    LukuangBean.DataBean.GetsenseBean mDataBean;
    private Handler mHandler=new Handler();
    Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(this,3000);
            OkhttpApi.luKuang(new MyCall() {
                @Override
                public void success(String json) {
                    Log.d("++", "success: "+json);
                    mLukuangBean= NetworkApi.gson.fromJson(json,LukuangBean.class);
                   mDataBean= mLukuangBean.data.getsense;
                   etPm.setText(mDataBean.pm2_5+"");
                   etWd.setText(mDataBean.temperature+"");
                   etSd.setText(mDataBean.humidity+"");
                   switch (mDataBean.address){
                       case 1:
                           etAddress1.setText("通畅");
                           liner1.setBackgroundColor(Color.parseColor("#0ebd12"));
                           break;
                       case 2:
                           etAddress1.setText("较通畅");
                           liner1.setBackgroundColor(Color.parseColor("#98ed1f"));
                           break;
                       case 3:
                           etAddress1.setText("拥挤");
                           liner1.setBackgroundColor(Color.parseColor("#ffff01"));
                           break;
                       case 4:
                           etAddress1.setText("堵塞");
                           liner1.setBackgroundColor(Color.parseColor("#ff0103"));
                           break;
                       case 5:
                           etAddress1.setText("爆表");
                           liner1.setBackgroundColor(Color.parseColor("#4c060e"));
                           break;

                   }
                    switch (mDataBean.address1){
                        case 1:
                            etAddress2.setText("通畅");
                            liner2.setBackgroundColor(Color.parseColor("#0ebd12"));
                            break;
                        case 2:
                            etAddress2.setText("较通畅");
                            liner2.setBackgroundColor(Color.parseColor("#98ed1f"));
                            break;
                        case 3:
                            etAddress2.setText("拥挤");
                            liner2.setBackgroundColor(Color.parseColor("#ffff01"));
                            break;
                        case 4:
                            etAddress2.setText("堵塞");
                            liner2.setBackgroundColor(Color.parseColor("#ff0103"));
                            break;
                        case 5:
                            etAddress2.setText("爆表");
                            liner2.setBackgroundColor(Color.parseColor("#4c060e"));
                            break;

                    }
                    switch (mDataBean.address2){
                        case 1:
                            etAddress3.setText("通畅");
                            liner3.setBackgroundColor(Color.parseColor("#0ebd12"));
                            break;
                        case 2:
                            etAddress3.setText("较通畅");
                            liner3.setBackgroundColor(Color.parseColor("#98ed1f"));
                            break;
                        case 3:
                            etAddress3.setText("拥挤");
                            liner3.setBackgroundColor(Color.parseColor("#ffff01"));
                            break;
                        case 4:
                            etAddress3.setText("堵塞");
                            liner3.setBackgroundColor(Color.parseColor("#ff0103"));
                            break;
                        case 5:
                            etAddress3.setText("爆表");
                            liner3.setBackgroundColor(Color.parseColor("#4c060e"));
                            break;

                    }
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
            setContentView(R.layout.activity_lukuang);
            initView();
    }

    private void initView() {
        toolBar = (RelativeLayout) findViewById(R.id.tool_bar);
        leftMenu = (ImageView) findViewById(R.id.left_menu);
        title = (TextView) findViewById(R.id.title);
        etPm = (TextView) findViewById(R.id.et_pm);
        etWd = (TextView) findViewById(R.id.et_wd);
        etSd = (TextView) findViewById(R.id.et_sd);
        etAddress1 = (TextView) findViewById(R.id.et_address1);
        etAddress2 = (TextView) findViewById(R.id.et_address2);
        etAddress3 = (TextView) findViewById(R.id.et_address3);
        liner1 = (LinearLayout) findViewById(R.id.liner_1);
        liner2 = (LinearLayout) findViewById(R.id.liner_2);
        liner3 = (LinearLayout) findViewById(R.id.liner_3);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler.postDelayed(mRunnable,3000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(mRunnable);
    }
}
