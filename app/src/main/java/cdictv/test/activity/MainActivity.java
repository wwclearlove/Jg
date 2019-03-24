package cdictv.test.activity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cdictv.test.R;
import cdictv.test.adatpter.GradViweAdatper;
import cdictv.test.bean.GradBean;
import cdictv.test.bean.LukuangBean;
import cdictv.test.network.MyCall;
import cdictv.test.network.NetworkApi;
import cdictv.test.network.OkhttpApi;
import cdictv.test.util.Sputil;

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
    private GridView mGridView;
    private ProgressBar mProgressBar;
    private AlertDialog mDialog;
    GradViweAdatper gradViweAdatper;
    LukuangBean mLukuangBean;
    LukuangBean.DataBean.GetsenseBean mDataBean;
    List<GradBean> mList=new ArrayList<>();
    @SuppressLint("HandlerLeak")
    Handler mHandler=new Handler();
    Runnable runnable= new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(this,10000);
            for (GradBean bean :
                 mList) {
                switch (bean.title){
                    case"温度":
                        if(Integer.parseInt(bean.value)<
                                Integer.parseInt(Sputil.getString("温度"))){
                            showNOtif(bean,Sputil.getString("温度"),1);
                        }
                        break;
                    case"湿度":
                        if(Integer.parseInt(bean.value)<
                                Integer.parseInt(Sputil.getString("湿度"))){
                            showNOtif(bean,Sputil.getString("湿度"),2);
                        }
                        break;
                    case"光照":
                        if(Integer.parseInt(bean.value)<
                                Integer.parseInt(Sputil.getString("光照"))){
                            showNOtif(bean,Sputil.getString("光照"),3);
                        }
                        break;
                    case"CQ2":
                        if(Integer.parseInt(bean.value)<
                                Integer.parseInt(Sputil.getString("co2"))){
                            showNOtif(bean,Sputil.getString("co2"),4);
                        }
                        break;
                    case"PM2.5":
                        if(Integer.parseInt(bean.value)<
                                Integer.parseInt(Sputil.getString("pm"))){
                            showNOtif(bean,Sputil.getString("pm"),5);
                        }
                        break;
                    case"道路状态":
                        if(Integer.parseInt(bean.value)<
                                Integer.parseInt(Sputil.getString("道路"))){
                            showNOtif(bean,Sputil.getString("道路"),6);
                        }
                        break;
                }
            }

        }
    };
    private void showNOtif(GradBean been, String fz, int id) {
        NotificationManager manager= (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        Notification noti = new NotificationCompat.Builder(this)
                .setContentTitle(been.title+"报警")
                .setContentText("阀值:"+fz+"当前值"+been.value)//短内容
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true) //点击咯就会自动撤销
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                .build();
        manager.notify(id, noti);

    }
    Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
            getData();
            mHandler.postDelayed(this,30000);
        }
    };

    private void getData() {
        OkhttpApi.luKuang(new MyCall() {
            @Override
            public void success(String json) {
                try {
                    mLukuangBean= NetworkApi.gson.fromJson(json,LukuangBean.class);
                }catch (Exception ex){
                    return;
                }


                mDataBean= mLukuangBean.data.getsense;
                List<GradBean> ms=fengzhuang(mDataBean);
                mList.clear();
                mList.addAll(ms);
                gradViweAdatper.notifyDataSetChanged();


            }

            @Override
            public void failed() {

            }
        });

    }

    private List<GradBean> fengzhuang(LukuangBean.DataBean.GetsenseBean dataBean) {
        GradBean gradBean1=new GradBean();
        GradBean gradBean2=new GradBean();
        GradBean gradBean3=new GradBean();
        GradBean gradBean4=new GradBean();
        GradBean gradBean5=new GradBean();
        GradBean gradBean6=new GradBean();
        gradBean1.title="温度";
        gradBean2.title="湿度";
        gradBean3.title="光照";
        gradBean4.title="CQ2";
        gradBean5.title="PM2.5";
        gradBean6.title="道路状态";

        gradBean1.value=dataBean.temperature+"";
        gradBean2.value=dataBean.humidity+"";
        gradBean3.value=dataBean.LightIntensity+"";
        gradBean4.value=dataBean.co2+"";
        gradBean5.value=dataBean.pm2_5+"";
        gradBean6.value=dataBean.address+"";
        List<GradBean>gradBeans=new ArrayList<>();
        gradBeans.add(gradBean1);
        gradBeans.add(gradBean2);
        gradBeans.add(gradBean3);
        gradBeans.add(gradBean4);
        gradBeans.add(gradBean5);
        gradBeans.add(gradBean6);
        return  gradBeans;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        //默认道路阀值
        sputil();
        initView();
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        initListener();
        gradViweAdatper=new GradViweAdatper(this,mList);
        mGridView.setAdapter(gradViweAdatper);
    }

    private void sputil() {
        Sputil.putString("温度","100");
        Sputil.putString("湿度","100");
        Sputil.putString("光照","100");
        Sputil.putString("co2","100");
        Sputil.putString("pm","100");
        Sputil.putString("道路","100");
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler.postDelayed(mRunnable,30000);
        if(Sputil.getBoolean("dlfz",false)){
            mHandler.postDelayed(runnable,10000);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(mRunnable);
        mHandler.removeCallbacks(runnable);
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
                startActivity(new Intent(MainActivity.this,FazhiActivity.class));
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
        mGridView=findViewById(R.id.gv_main);
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
