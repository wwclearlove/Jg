package cdictv.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import cdictv.test.R;


public class ContentFragment2 extends Fragment {
    private View view;
    BarChart barchart;
    List<String>xAxisValue;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.contentfragment2, container, false);
        barchart = view.findViewById(R.id.chart);
//        showChart(getBarData());
        initchart();
        return view;
    }

    private void initchart() {
        barchart.setDrawBarShadow(false);//true绘画的Bar有阴影。
        barchart.setDrawValueAboveBar(true);//true文字绘画在bar上
        barchart.getDescription().setEnabled(false);
        barchart.setMaxVisibleValueCount(60);
        barchart.setPinchZoom(false);//false只能单轴缩放
        barchart.setDrawGridBackground(false);
        //x坐标轴设置
     xAxisValue=new ArrayList<>();
        xAxisValue.add("1月");
        xAxisValue.add("2月");
        xAxisValue.add("3月");
        xAxisValue.add("4月");
        xAxisValue.add("5月");
        xAxisValue.add("6月");
        XAxis xAxis = barchart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(xAxisValue.size());
        xAxis.setCenterAxisLabels(true);//设置标签居中
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValue));

        //设置Y轴
        barchart.getAxisRight().setEnabled(false);
        YAxis leftAxis = barchart.getAxisLeft();
        leftAxis.setLabelCount(6, false);
//        leftAxis.setValueFormatter();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(10f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(50f);

        Legend l = barchart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

//        XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
//        mv.setChartView(barchart); // For bounds control
//        barchart.setMarker(mv); // Set the marker to the chart
        setData(6, 50);

    }


    private void setData(int count, float range) {
        float start = 1f;
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        for (int i = (int) start; i < start + count; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);
            float val2 = (float) (Math.random() * mult);
            yVals1.add(new BarEntry(i, val));
            yVals2.add(new BarEntry(i, val2));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "数据说明1");
        set1.setDrawIcons(false);
        set1.setColor(ColorTemplate.rgb("#2ecc71"));
        BarDataSet set2 = new BarDataSet(yVals2, "数据说明2");
        set2.setDrawIcons(false);
        set2.setColor(ColorTemplate.rgb("#f1c40f"));
        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
        dataSets.add(set2);
        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        barchart.setData(data);

        float groupSpace = 0.2f;
        float barSpace = 0.1f;
        barchart.getBarData().setBarWidth(0.3f);
        barchart.getXAxis().setAxisMinimum(0);
        barchart.getXAxis().setAxisMaximum(barchart.getBarData().getGroupWidth(groupSpace, barSpace) * xAxisValue.size() + 0);
        barchart.groupBars(0, groupSpace, barSpace);
//        barchart.animateY(1000, Easing.Linear);
//        barchart.animateX(1000, Easing.Linear);
    }
}