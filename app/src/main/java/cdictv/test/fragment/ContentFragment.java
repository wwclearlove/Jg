package cdictv.test.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import cdictv.test.R;



public class ContentFragment extends Fragment {
    private View view;
    PieChart mChart;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.contentfragment,container,false);
        mChart=view.findViewById(R.id.chart1);
        showChart(getPieData());
        return view;
    }
    private void showChart(PieData pieData) {
        mChart.setUsePercentValues(true);
        mChart.setHoleRadius(60f);  //内环半径
        mChart.setTransparentCircleRadius(64f); // 半透明圈半径
         mChart.setHoleRadius(0);  // 实心圆
//        mChart.setDescription("测试饼状图");
        mChart.setRotationEnabled(false);
        mChart.getDescription().setEnabled(false);//设置描述
        mChart.setDrawCenterText(true);  //饼状图中间可以添加文字
        mChart.setCenterText("2017年季度收入");  //饼状图中间的文字
        //实心饼图
        mChart.setDrawHoleEnabled(true);
        mChart.setRotationAngle(90); // 初始旋转角度
        // mChart.setUsePercentValues(true);  //显示成百分比
        // 设置可触摸
        mChart.setTouchEnabled(true);
        // 设置数据
        mChart.setData(pieData);
        mChart.setDrawHoleEnabled(false);
        // 取消高亮显示
        mChart.highlightValues(null);
        mChart.invalidate();

        Legend mLegend = mChart.getLegend();  //设置比例图
//        mLegend.setEnabled(false);
        mLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);  //最右边显示
        mLegend.setForm(Legend.LegendForm.LINE);  //设置比例图的形状，默认是方形
        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(5f);

        //设置动画
        mChart.animateXY(1000, 1000);
    }

    private PieData getPieData() {
        // xVals用来表示每个饼块上的文字
     List<String> xValues = new ArrayList<String>();

        for (int i = 0; i < 4; i++) {
            xValues.add((i + 1) + "季度");
        }

        // yVals用来表示封装每个饼块的实际数据
       List<PieEntry> yValues = new ArrayList<PieEntry>();

        // 饼图数据
        float quarterly1 = 456787;
        float quarterly2 = 534283;
        float quarterly3 = 345734;
        float quarterly4 = 658465;

        yValues.add(new PieEntry(quarterly1, 0));
        yValues.add(new PieEntry(quarterly2, 1));
        yValues.add(new PieEntry(quarterly3, 2));
        yValues.add(new PieEntry(quarterly4, 3));

        // y轴集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "2017年季度收入");
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离
        //展示数据 线
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        //设置线长度
        pieDataSet.setValueLinePart1Length(0.4f);
        pieDataSet.setValueLinePart2Length(0.4f);
        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        colors.add(Color.rgb(205, 205, 205));
        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(57, 135, 200));

        // 设置饼图颜色
        pieDataSet.setColors(colors);

        // 设置选中态多出的长度
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px);

        // 创建饼图数据
        PieData pieData = new PieData( pieDataSet);

        return pieData;
    }

}