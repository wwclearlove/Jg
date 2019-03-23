package cdictv.test.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cdictv.test.adatpter.DataAnalysisVPAdapter;
import cdictv.test.R;
import cdictv.test.fragment.ContentFragment;
import cdictv.test.fragment.ContentFragment2;
import cdictv.test.fragment.ContentFragment3;


public class ViewPagerActivity extends AppCompatActivity {
    private ViewPager vp;
    private TextView c1;
    private TextView c2;
    private TextView c3;
    private TextView[] ys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        vp.setAdapter(new DataAnalysisVPAdapter(getSupportFragmentManager(),getDataAnalysiss()));
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < ys.length; i++) {
                    if (i==position){
                        ys[i].setBackgroundResource(R.drawable.tv_circular_red);
                    }else {
                        ys[i].setBackgroundResource(R.drawable.tv_circular);
                    }
                }



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void init() {
        setContentView(R.layout.activity_view_pager);
        vp = findViewById(R.id.da_vp);
        c1 = findViewById(R.id.tv_circular_1);
        c2 = findViewById(R.id.tv_circular_2);
        c3 = findViewById(R.id.tv_circular_3);
        ys= new TextView[]{c1,c2,c3};
        ys[0].setBackgroundResource(R.drawable.tv_circular_red);

    }

    public List<Fragment> getDataAnalysiss(){
        List< Fragment> fragments = new ArrayList<>();
        ContentFragment f1 = new  ContentFragment();
        ContentFragment2 f2 = new  ContentFragment2();
        ContentFragment3 f3 = new  ContentFragment3();
        fragments.add(f1);
        fragments.add(f2);
        fragments.add(f3);
        return fragments;
    }

}
