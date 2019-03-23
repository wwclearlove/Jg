package cdictv.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import cdictv.test.R;

public class IetentActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgview;
    private TextView textLogo;
    private TextView network;
    private EditText edName;
    private EditText edPassword;
    private CheckBox checkJz;
    private CheckBox checkZd;
    private Button logo;
    private Button regist;
    private String name;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ietent);
        initViwe();
        initLister();
        getStringNamePass();
    }

    private void getStringNamePass() {
        name=edName.getText().toString().trim();
        password=edPassword.getText().toString().trim();
    }

    private void initLister() {
        logo.setOnClickListener(this);
    }
    private void initViwe() {
        imgview = (ImageView) findViewById(R.id.imgview);
        textLogo = (TextView) findViewById(R.id.text_logo);
        network = (TextView) findViewById(R.id.network);
        edName = (EditText) findViewById(R.id.ed_name);
        edPassword = (EditText) findViewById(R.id.ed_password);
        checkJz = (CheckBox) findViewById(R.id.check_jz);
        checkZd = (CheckBox) findViewById(R.id.check_zd);
        logo = (Button) findViewById(R.id.logo);
        regist = (Button) findViewById(R.id.regist);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logo:
                startActivity(new Intent(this,MainActivity.class));
        }
    }
}
