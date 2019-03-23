package cdictv.test.adatpter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cdictv.test.bean.LudengBeen;
import cdictv.test.R;

public class LudengAdatper extends BaseAdapter {
    private List<LudengBeen.DataBean> mDataBean;
    private Context mContext;
    public LudengAdatper(List<LudengBeen.DataBean> bean, Context context){
    mDataBean=bean;
    mContext=context;
        for (LudengBeen.DataBean user:mDataBean
                ) {
            Log.d("++++", "success: "+user.toString());
        }
    }
    @Override
    public int getCount() {
        return mDataBean.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        LudengBeen.DataBean bean=mDataBean.get(position);
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.lukou=convertView.findViewById(R.id.lukou);
            viewHolder.red=convertView.findViewById(R.id.red_light);
            viewHolder.yellow=convertView.findViewById(R.id.yellow_light);
            viewHolder.greaww=convertView.findViewById(R.id.green_light);
            convertView.setTag(viewHolder);
        }else {

            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.lukou.setText(bean.id+"");
        viewHolder.red.setText(bean.red+"");
        viewHolder.yellow.setText(bean.yellow+"");
        viewHolder.greaww.setText(bean.green+"");
        return convertView;
    }
    class ViewHolder{
        TextView lukou;
        TextView red;
        TextView yellow;
        TextView greaww;
    }
}
