package cdictv.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cdictv.test.adatpter.BusQueryAdapter;
import cdictv.test.bean.TestUser;
import cdictv.test.R;

public class PaixuActivity extends AppCompatActivity {
    private List<TestUser> mList=new ArrayList();
    ExpandableListView expand_listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paixu);
        expand_listview = findViewById(R.id.alarm_clock_expandablelist);
        expand_listview.setAdapter(new BusQueryAdapter(this));

        initData();
        //按int字段排序
//        intpaixu();
        //按时间来排序
        datepaixu();
        baifenzhuanhuan();
    }

    private void baifenzhuanhuan() {
        double percent = (double)5/15;
        double percent3 = (double)0/1;
        NumberFormat nt = NumberFormat.getPercentInstance();//获取格式化对象
        nt.setMinimumFractionDigits(2);//设置百分数精确度2即保留两位小数
        Log.d("zh", "baifenzhuanhuan: "+nt.format(percent));
        Log.d("zh", "baifenzhuanhuan: "+nt.format(percent3));

    }

    private void datepaixu() {
        Collections.sort(mList, new Comparator<TestUser>() {
            @Override
            public int compare(TestUser o1, TestUser o2) {
              int diff=o1.getTime().compareTo(o2.getTime());
              return diff;//为正序 -diff为反序
        }
        });
        Log.d("===",mList.toString());
    }

    private void intpaixu() {
        Collections.sort(mList, new Comparator<TestUser>() {
            @Override
            public int compare(TestUser o1, TestUser o2) {
                int diff=o1.getShichang()-o2.getShichang();
                return diff;//为正序 -diff为反序

            }
        });
        Log.d("===",mList.toString());
    }


    private void initData() {
        TestUser testUser1=new TestUser();
        testUser1.setName("红灯");
        testUser1.setShichang(3);
        testUser1.setTime("2019-03-18 17:47:26");
        TestUser testUser2=new TestUser();
        testUser2.setName("红灯");
        testUser2.setShichang(5);
        testUser2.setTime("2019-03-19 17:37:26");
        TestUser testUser3=new TestUser();
        testUser3.setName("绿灯");
        testUser3.setShichang(5);
        testUser3.setTime("2019-03-19 17:37:16");
        TestUser testUser4=new TestUser();
        testUser4.setName("绿灯");
        testUser4.setShichang(4);
        testUser4.setTime("2019-03-19 12:37:16");
        TestUser testUser5=new TestUser();
        testUser5.setName("黄灯");
        testUser5.setShichang(2);
        testUser5.setTime("2019-03-19 14:37:16");
        mList.add(testUser1);
        mList.add(testUser2);
        mList.add(testUser3);
        mList.add(testUser4);
        mList.add(testUser5);
    }
}
