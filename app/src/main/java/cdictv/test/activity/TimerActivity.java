package cdictv.test.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cdictv.test.R;
import cdictv.test.util.Sputil;

public class TimerActivity extends AppCompatActivity {
    private TextView tvBalanceWarning;
    private TextView tvWarning;
    private TextView tvSettingTitle;
    private EditText etSettingWarning;
    private Button btnSetting;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        findview();
        String war=Sputil.getString("warning");
        if((!("".equals(war)))){
            tvWarning.setText(war+"元");
        }



    }


    @Override
    protected void onStart() {
        super.onStart();
//
    }

    private void findview() {
        tvBalanceWarning =  findViewById(R.id.tv_balance_warning);
        tvWarning =  findViewById(R.id.tv_warning);
        tvSettingTitle =  findViewById(R.id.tv_setting_title);
        etSettingWarning =  findViewById(R.id.et_setting_warning);
        btnSetting =  findViewById(R.id.btn_setting);


    }
    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    public void shezhi(View view) {
        String waning=etSettingWarning.getText().toString().trim();
       tvWarning.setText(waning+"元");
       etSettingWarning.setText("");
        Toast.makeText(getApplicationContext(),"设置成功",Toast.LENGTH_LONG).show();
        Sputil.putString("warning",waning);
    }
}
