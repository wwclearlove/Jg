package cdictv.test.adatpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;

import cdictv.test.R;

public class BusQueryAdapter extends BaseExpandableListAdapter {
    Context context;
    public BusQueryAdapter(Context context){
        this.context  = context;
    }
    //父item数量
    @Override
    public int getGroupCount() {
        return 2;
    }
    //子item数量
    @Override
    public int getChildrenCount(int groupPosition) {
        return 2;
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
        if(convertView == null){
            convertView = View.inflate(context, R.layout.item_busquery_group,null);
        }
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
        return View.inflate(context, R.layout.item_busquery_child,null);
    }
    //
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
