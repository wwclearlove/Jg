package cdictv.test.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cdictv.test.R;
import cdictv.test.adatpter.LudengAdatper;
import cdictv.test.bean.LudengBeen;
import cdictv.test.bean.SpinerBean;
import cdictv.test.network.LudengApi;
import cdictv.test.network.MyCall;
import cdictv.test.network.NetworkApi;
import cdictv.test.util.ListPaixu;

public class LudengActivity extends AppCompatActivity {
    private SpinerBean mSpinerBean=new SpinerBean();
    private Button query;
    private View topView;
    private TextView lukou;
    private TextView redLight;
    private TextView yellowLight;
    private TextView greenLight;
    private View bottomView;
    private Spinner mSpinner;
    private ListView listview;
    private Handler mHandler = new Handler();
    private LudengBeen been;
    private List<LudengBeen.DataBean> mList=new ArrayList<>();
    LudengAdatper myAdatper;
    Timer mTimer = new Timer();

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            mHandler.post(() -> {
                LudengApi.show("%5B%5D", "%5B%5D", new MyCall() {
                    @Override
                    public void success(String json) {
                        Log.e("json", "success: " + json);
                        been = NetworkApi.gson.fromJson(json, LudengBeen.class);
                        ListPaixu.luKouS(mSpinerBean.id, been.data);
                        mList.clear();
                        mList.addAll(been.data);
                        myAdatper = new LudengAdatper(mList, LudengActivity.this);
                        listview.setAdapter(myAdatper);
                    }

                    @Override
                    public void failed() {

                    }
                });
            });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTimer.cancel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTimer = new Timer();
        mTimer.schedule(new MyTask(), 0, 3000);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ludeng);
        findView();
        initListener();
    }

    private void initListener() {
        mSpinner.setOnItemSelectedListener
                (new AdapterView.OnItemSelectedListener() {
                     @Override
                     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                         mSpinerBean.id=position+1;
                         mSpinerBean.args=(String) parent.getItemAtPosition(position);
                     }

                     @Override
                     public void onNothingSelected(AdapterView<?> parent) {

                     }
                 }

                );
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mSpinerBean.id){
                    case 1:
                           ListPaixu.luKouS(1,mList);
                           myAdatper.notifyDataSetChanged();
                        break;
                    case 2:
                        ListPaixu.luKouS(2,mList);
                        myAdatper.notifyDataSetChanged();
                        break;
                    case 3:
                        ListPaixu.luKouS(3,mList);
                        myAdatper.notifyDataSetChanged();
                        break;
                    case 4:
                        ListPaixu.luKouS(4,mList);
                        myAdatper.notifyDataSetChanged();
                        break;
                    case 5:
                        ListPaixu.luKouS(5,mList);
                        myAdatper.notifyDataSetChanged();
                        break;
                    case 6:
                        ListPaixu.luKouS(6,mList);
                        myAdatper.notifyDataSetChanged();
                        break;
                    case 7:
                        ListPaixu.luKouS(7,mList);
                        myAdatper.notifyDataSetChanged();
                        break;
                    case 8:
                        ListPaixu.luKouS(8,mList);
                        myAdatper.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    private void findView() {
        query = (Button) findViewById(R.id.query);
        topView = (View) findViewById(R.id.top_view);
        lukou = (TextView) findViewById(R.id.lukou);
        redLight = (TextView) findViewById(R.id.red_light);
        yellowLight = (TextView) findViewById(R.id.yellow_light);
        greenLight = (TextView) findViewById(R.id.green_light);
        bottomView = (View) findViewById(R.id.bottom_view);
        listview = (ListView) findViewById(R.id.listview);
        mSpinner = findViewById(R.id.spinenr);
    }
}
