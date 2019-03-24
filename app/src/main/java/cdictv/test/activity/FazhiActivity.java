package cdictv.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import cdictv.test.R;
import cdictv.test.util.Sputil;

public class FazhiActivity extends AppCompatActivity {
    private Switch btnSwitch;
    private EditText edWd;
    private EditText edSd;
    private EditText edGz;
    private EditText edCo2;
    private EditText edPm;
    private EditText edRoad;
    private Button save;
    private Button cancel;
    private String wd;
    private String sd;
    private String gz;
    private String co2;
    private String pm;
    private String dl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazhi);
        initView();
        initLinser();
    }

    private void initLinser() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wd=edWd.getText().toString().trim();
                sd=edSd.getText().toString().trim();
                gz=edGz.getText().toString().trim();
                co2=edCo2.getText().toString().trim();
                pm=edPm.getText().toString().trim();
                dl=edRoad.getText().toString().trim();
                //保存是否发通知
                Sputil.putBoolean("dlfz",btnSwitch.isChecked());
                Sputil.putString("温度",wd);
                Sputil.putString("湿度",sd);
                Sputil.putString("光照",gz);
                Sputil.putString("co2",co2);
                Sputil.putString("pm",pm);
                Sputil.putString("道路",dl);
                 Toast.makeText(getApplicationContext(),"阀值设置成功",Toast.LENGTH_LONG).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        btnSwitch = (Switch) findViewById(R.id.btn_switch);
        edWd = (EditText) findViewById(R.id.ed_wd);
        edSd = (EditText) findViewById(R.id.ed_sd);
        edGz = (EditText) findViewById(R.id.ed_gz);
        edCo2 = (EditText) findViewById(R.id.ed_co2);
        edPm = (EditText) findViewById(R.id.ed_pm);
        edRoad = (EditText) findViewById(R.id.ed_road);
        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);

    }
}
