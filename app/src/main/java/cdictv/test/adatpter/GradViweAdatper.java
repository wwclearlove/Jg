package cdictv.test.adatpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import cdictv.test.R;
import cdictv.test.bean.GradBean;
import cdictv.test.util.Sputil;

public class GradViweAdatper extends BaseAdapter {
    private Context mContext;
    List<GradBean> mList;
    public GradViweAdatper(Context context,List<GradBean> List){
    mContext=context;
    mList=List;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        GradBean bean=mList.get(position);
        if(convertView==null){
            convertView=View.inflate(mContext, R.layout.item_mainactivity,null);
            viewHolder=new ViewHolder();
         viewHolder.title=convertView.findViewById(R.id.tv_title);
         viewHolder.tv_value=convertView.findViewById(R.id.tv_value);
         viewHolder.mLayout=convertView.findViewById(R.id.layout);
         convertView.setTag(viewHolder);
        }else {
           viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(bean.title);
        viewHolder.tv_value.setText(bean.value);
        switch (bean.title){
            case"温度":
                        if(Integer.parseInt(bean.value)<
                                Integer.parseInt(Sputil.getString("温度"))){
                            viewHolder.mLayout.setBackgroundColor(Color.RED);
                        }else {
                            viewHolder.mLayout.setBackgroundColor(Color.GREEN);
                        }
                break;
            case"湿度":
                if(Integer.parseInt(bean.value)<
                        Integer.parseInt(Sputil.getString("湿度"))){
                    viewHolder.mLayout.setBackgroundColor(Color.RED);
                }else {
                    viewHolder.mLayout.setBackgroundColor(Color.GREEN);
                }
                break;
            case"光照":
                if(Integer.parseInt(bean.value)<
                        Integer.parseInt(Sputil.getString("光照"))){
                    viewHolder.mLayout.setBackgroundColor(Color.RED);
                }else {
                    viewHolder.mLayout.setBackgroundColor(Color.GREEN);
                }
                break;
            case"CQ2":
                if(Integer.parseInt(bean.value)<
                        Integer.parseInt(Sputil.getString("co2"))){
                    viewHolder.mLayout.setBackgroundColor(Color.RED);
                }else {
                    viewHolder.mLayout.setBackgroundColor(Color.GREEN);
                }
                break;
            case"PM2.5":
                if(Integer.parseInt(bean.value)<
                        Integer.parseInt(Sputil.getString("pm"))){
                    viewHolder.mLayout.setBackgroundColor(Color.RED);
                }else {
                    viewHolder.mLayout.setBackgroundColor(Color.GREEN);
                }
                break;
            case"道路状态":
                if(Integer.parseInt(bean.value)<
                        Integer.parseInt(Sputil.getString("道路"))){
                    viewHolder.mLayout.setBackgroundColor(Color.RED);
                }else {
                    viewHolder.mLayout.setBackgroundColor(Color.GREEN);
                }
                break;
        }

        return convertView;
    }
    class ViewHolder{
        TextView title;
        TextView tv_value;
        RelativeLayout mLayout;
    }
}
