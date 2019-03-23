package cdictv.test.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cdictv.test.R;
import cdictv.test.adatpter.MyAdapter;
import cdictv.test.bean.ClczBeen;
import cdictv.test.database.ClczDao;
import cdictv.test.database.CzliDao;
import cdictv.test.network.LudengApi;
import cdictv.test.network.MyCall;
import cdictv.test.network.Upadate;
import cdictv.test.util.DateUtil;
import cdictv.test.util.JsonObjecj;
import cdictv.test.util.Sputil;

public class Test1Activity extends AppCompatActivity {
    private List<ClczBeen> mClczBeens = new ArrayList<>();
    RecyclerView mRecyclerView;
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    List<ClczBeen> mlist= (List<ClczBeen>) msg.obj;
                    int i=1;
                    for (ClczBeen benn :
                            mlist) {
                        String fz=Sputil.getString("warning");
                        myAdapter.notifyDataSetChanged();
                        if(benn.getYe()<Integer.parseInt(fz)){
                            showNOtif(benn,fz,i);

                            i++;
                        }
                    }
                    break;
            }
        }
    };
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showNOtif(ClczBeen been,String fz,int id) {
        NotificationManager manager= (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        Notification noti = new NotificationCompat.Builder(this)
                .setContentTitle("余额警告")
                .setContentText("车辆编号:"+been.getBh()+"余额"+been.getYe()+"元"+"阀值"+fz+"元")//短内容
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true) //点击咯就会自动撤销
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                .build();
        manager.notify(id, noti);

    }
    MyAdapter myAdapter;
    TextView mTextView;
    TextView mTextView2;
    TextView mTextView3;
        Timer mTimer=new Timer();
//    TimerTask mTimerTask=new TimerTask() {
//        @Override
//        public void run() {
//
//
//        }
//    };
     private class MyTask extends TimerTask{
    @Override
    public void run() {
        mHandler.post(() -> {
                List<ClczBeen> list=ClczDao.add();
                Message ms=new Message();
                ms.what=1;
                ms.obj=list;
                mHandler.sendMessage(ms);
            });
    }
}
    @Override
    protected void onStart() {
        super.onStart();
       mTimer= new Timer();
        mTimer.schedule(new MyTask(),0,30000);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTimer.cancel();

//        mTimer.purge();

//        mTimer = null;
    }

    Upadate mUpadate = new Upadate() {
        @Override
        public void success() {
            myAdapter.notifyDataSetChanged();
        }

        @Override
        public void failed() {

        }
    };
    int j;
    private List<ClczBeen> mList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        //第一次进入 设置阀值为50
        boolean b= Sputil.getBoolean("isFa",false);
        if(!b){
            Sputil.putString("warning","50");
            Sputil.putBoolean("isFa",true);
        }
//        LitePal.getDatabase();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        mTextView = findViewById(R.id.cq);
        mTextView3 = findViewById(R.id.fazhi);
        mTextView2 = findViewById(R.id.jilu);
        mTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Test1Activity.this, LishiActivity.class));
            }
        });
        mTextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Test1Activity.this, TimerActivity.class));
            }
        });
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cxTrue();
                View view = View.inflate(Test1Activity.this, R.layout.dialog, null);
                TextView view1 = view.findViewById(R.id.dailog_carid);
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < mList.size(); i++) {
                    stringBuilder.append(mList.get(i).getBh() + ",");
                    j = i;

                }
                view1.setText("车辆编号:" + stringBuilder.toString());

                AlertDialog dialog = new AlertDialog.Builder(Test1Activity.this).setView(view).show();
                Button button1 = view.findViewById(R.id.quxiao);
                Button button2 = view.findViewById(R.id.ok);
                EditText editText = view.findViewById(R.id.ed_text);
                Log.d("++", "onClick: " + editText.toString());
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editText.getText().toString().equals("")) {
                            Toast.makeText(Test1Activity.this, "充值失败", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            return;

                        }
                        int cq = Integer.parseInt(editText.getText().toString());
                        if (cq > 0 && cq <= 999) {
                            for (int i = 0; i < mList.size(); i++) {
                                int ye = Integer.parseInt(String.valueOf(mList.get(i).getYe()));
                                ClczBeen been=new ClczBeen();
                                been.setBh(mList.get(i).getBh());
                                been.setYe(cq+ye);
                                ClczDao.insertnet(been);
                                //保存历史记录
                                CzliDao.insert(mList.get(i).getBh(), editText.getText().toString(), cq + ye + "", "王", DateUtil.show());
                                Toast.makeText(Test1Activity.this, "充值成功", Toast.LENGTH_LONG).show();
                                mList.get(i).setYe(cq + ye);
                                if (mUpadate != null) {
                                    mUpadate.success();
                                }

                            }

                        } else {
                            Toast.makeText(Test1Activity.this, "充值失败，金额为1-999", Toast.LENGTH_LONG).show();
                        }

                        dialog.dismiss();
                    }
                });


            }
        });

        initdata();
        //没有数据
        if(mClczBeens.size()==0){
            LudengApi.cheliang(new MyCall() {
                @Override
                public void success(String json) {
                    List list = JsonObjecj.getList(json);
                    mClczBeens.addAll(list);
                    //保存到数据库
                    ClczDao.savall(mClczBeens);
                    myAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(),"没数据",Toast.LENGTH_LONG).show();
                }

                @Override
                public void failed() {

                }
            });
//
        }

       mRecyclerView = findViewById(R.id.recy);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        myAdapter = new MyAdapter(mClczBeens, this, mUpadate);
        mRecyclerView.setAdapter(myAdapter);
    }

    private void initdata() {
//        Toast.makeText(getApplicationContext(),"有数据",Toast.LENGTH_LONG).show();
        List list =ClczDao.add();
        mClczBeens.addAll(list);
//        myAdapter.notifyDataSetChanged();
    }

    private void cxTrue() {
        mList.clear();
        for (int i = 0; i < mClczBeens.size(); i++) {
            if (mClczBeens.get(i).isXz()) {
                mList.add(mClczBeens.get(i));

            }
        }
    }

}

