package cdictv.test.adatpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cdictv.test.R;
import cdictv.test.bean.GongjiaoBean;

public class BusQueryAdapter extends BaseExpandableListAdapter {
    private List<GongjiaoBean.DataBean> mlist;
    Context context;
    public BusQueryAdapter(Context context, List<GongjiaoBean.DataBean> list){
        this.context  = context;
        mlist=list;
    }
    ViewHolderFather mViewHolderFather;
    ViewHolderSon mViewHolderSon;
    //父item数量
    @Override
    public int getGroupCount() {
        return mlist.size();
    }
    //子item数量
    @Override
    public int getChildrenCount(int groupPosition) {
        return mlist.get(groupPosition).bus.size();
    }
    //得到父item
    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }
    //得到一个父item的一个子item
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }
    //得到父item id
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    //得到子item id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
//自动生成
    @Override
    public boolean hasStableIds() {
        return false;
    }
    //加载父布局
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GongjiaoBean.DataBean dataBean=mlist.get(groupPosition);
        if(convertView == null){
            convertView = View.inflate(context, R.layout.item_busquery_group,null);
            mViewHolderFather=new ViewHolderFather();
            mViewHolderFather.station=convertView.findViewById(R.id.station);
            convertView.setTag(mViewHolderFather);
        }else {
          mViewHolderFather= (ViewHolderFather) convertView.getTag();
        }
        mViewHolderFather.station.setText(dataBean.zhantai+"");
        ImageView img = convertView.findViewById(R.id.img);
        if(isExpanded){
            img.setImageResource(R.mipmap.triangle_bottom);
        }else {
            img.setImageResource(R.mipmap.triangle_right);
        }
        return convertView;
    }
    //加载子布局
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        GongjiaoBean.DataBean.BusBean busBean=mlist.get(groupPosition).bus.get(childPosition);
        if(convertView == null){
            convertView = View.inflate(context, R.layout.item_busquery_child,null);
           mViewHolderSon=new ViewHolderSon();
            mViewHolderSon.busId=convertView.findViewById(R.id.bus_id);
            mViewHolderSon.distance=convertView.findViewById(R.id.distance);
            convertView.setTag(mViewHolderSon);
        }else {
            mViewHolderSon= (ViewHolderSon) convertView.getTag();
        }
        mViewHolderSon.busId.setText(busBean.name+"");
        mViewHolderSon.distance.setText(busBean.distance+"");
        return convertView;
    }
    //
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class ViewHolderFather{
        TextView station;
    }
    class ViewHolderSon{
        TextView busId;
        TextView distance;
    }
}
