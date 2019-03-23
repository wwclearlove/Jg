package cdictv.test.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import cdictv.test.R;


public class ContentFragment3 extends Fragment {
    private View view;
    private LineChart mChart;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.contentfragment3, container, false);
        mChart = view.findViewById(R.id.chart3);
        init();
        return view;
    }

    private void init(){
//        mChart.setDescription("评分");//描述
//        mChart.setDescriptionTextSize(30);//描述大小
        String xl[] ={"1","2","3","4","5"}; //横轴数据
        String yl[] ={"80","85","80","90","95"}; //竖轴数据
        LineData data = getData(xl,yl);
        mChart.setData(data);
        mChart.animateX(3500);//动画
    }
    private LineData getData(String[] xx, String[] yy) {
        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < xx.length; i++) {
            // x轴显示的数据
            xVals.add(xx[i]);
        }
        for (int i = 0; i < yy.length; i++) {
            // y轴显示的数据
            yVals.add(new Entry(Float.parseFloat(yy[i]), i));
        }
        LineDataSet set1 = new LineDataSet(yVals, "前五次的评分");
//        set1.setDrawCubic(false);  //设置曲线为圆滑的线
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(false);  //设置包括的范围区域填充颜色
        set1.setDrawCircles(true);  //设置有圆点
        set1.setLineWidth(2f);    //设置线的宽度
        set1.setCircleSize(5f);   //设置小圆的大小
        set1.setCircleColor(Color.rgb(244, 117, 117));
        set1.setColor(Color.rgb(244, 117, 117));
        LineData data = new LineData( set1);
        return data;
    }

}