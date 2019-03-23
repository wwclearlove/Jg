package cdictv.test.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cdictv.test.R;
import cdictv.test.adatpter.LudengAdatper;
import cdictv.test.bean.LudengBeen;
import cdictv.test.network.LudengApi;
import cdictv.test.network.MyCall;
import cdictv.test.util.ListPaixu;

public class LudengActivity extends AppCompatActivity {
    private Button query;
    private View topView;
    private TextView lukou;
    private TextView redLight;
    private TextView yellowLight;
    private TextView greenLight;
    private View bottomView;
    private Spinner mSpinner;
    private ListView listview;
    private Handler mHandler=new Handler();
    private  LudengBeen been;
    private List<LudengBeen.DataBean> mList;
  LudengAdatper myAdatper;
    Timer mTimer=new Timer();
    private class MyTask extends TimerTask {
        @Override
        public void run() {
            mHandler.post(() -> {
                LudengApi.show("%5B%5D", "%5B%5D", new MyCall() {
                    @Override
                    public void success(String json) {
                        Log.e("json", "success: "+json);
                        been=new Gson().fromJson(json,LudengBeen.class);
                        mList= ListPaixu.luKouS(1,been.data);
                        myAdatper=new LudengAdatper(mList,LudengActivity.this);
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
        mTimer= new Timer();
        mTimer.schedule(new MyTask(),0,3000);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ludeng);
        findView();
        initListener();
    }

    private void initListener() {

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
        mSpinner=findViewById(R.id.spinenr);

    }
}
