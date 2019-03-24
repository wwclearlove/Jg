package cdictv.test.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cdictv.test.R;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private RelativeLayout toolBar;
    private ImageView leftMenu;
    private TextView title;
    private TextView zuxiao;
    private LinearLayout navigationView;
    private ImageView topBac;
    private LinearLayout myAccount;
    private LinearLayout    myFzzhi;
    private LinearLayout rglManger;
    private LinearLayout dataAnalysis;
    private LinearLayout gongjiaoche;
    private LinearLayout lukuang;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        initListener();
    }
    private void initListener() {
        leftMenu.setOnClickListener(view -> {
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        //账户管理界面
        myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,Test1Activity.class));
            }
        });
        //阀值设置
        myFzzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TimerActivity.class));
            }
        });
        //红绿灯
        rglManger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LudengActivity.class));
            }
        });
        //数据分析
        dataAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ViewPagerActivity.class));
            }
        });
        //公交车
        gongjiaoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PaixuActivity.class));
            }
        });
        //路况
        lukuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LukuangActivity.class));
            }
        });
        //注销
        zuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolBar =  findViewById(R.id.tool_bar);
        leftMenu = (ImageView) findViewById(R.id.left_menu);
        title = (TextView) findViewById(R.id.title);

        navigationView = (LinearLayout) findViewById(R.id.navigation_view);
        topBac = (ImageView) findViewById(R.id.top_bac);
        myAccount = (LinearLayout) findViewById(R.id.my_account);
        rglManger = (LinearLayout) findViewById(R.id.rgl_manger);
        dataAnalysis = (LinearLayout) findViewById(R.id.dataAnalysis);
        gongjiaoche= (LinearLayout) findViewById(R.id.gongjiao);
       lukuang= (LinearLayout) findViewById(R.id.lukuang);
        zuxiao=findViewById(R.id.zhuxiao);
        myFzzhi=findViewById(R.id.my_fz);

    }
}
