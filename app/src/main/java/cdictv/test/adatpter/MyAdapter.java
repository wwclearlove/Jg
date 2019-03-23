package cdictv.test.adatpter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cdictv.test.bean.ClczBeen;
import cdictv.test.R;
import cdictv.test.network.Upadate;
import cdictv.test.database.ClczDao;
import cdictv.test.database.CzliDao;
import cdictv.test.util.DateUtil;
import cdictv.test.util.Sputil;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ClczBeen> mList;
    private Upadate mUpadate;
    private Context mContext;
    public MyAdapter(List<ClczBeen> List,Context context,Upadate upadate){
        mList=List;
        mContext=context;
        mUpadate=upadate;
    }
    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recy_item,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, int i) {
        String fz= Sputil.getString("warning");

        ClczBeen been=mList.get(i);
        if(been.getYe()<Integer.parseInt(fz)){
            viewHolder.itemView.setBackgroundColor(Color.YELLOW);
        }else {
            viewHolder.itemView.setBackgroundColor(Color.WHITE);
        }
        viewHolder.bhTextView.setText(been.getBh()+"");
        viewHolder.yeTextView.setText(been.getYe()+"");
        viewHolder.ischeck.setChecked(been.isXz());
        viewHolder.ischeck.setTag(i);
        viewHolder.ischeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i= (int) v.getTag();
                mList.get(i).setXz(!mList.get(i).isXz());
            }
        });
        viewHolder.cq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              View view=View.inflate(mContext,R.layout.dialog,null);
              TextView view1=view.findViewById(R.id.dailog_carid);
              view1.setText("车辆编号:"+mList.get(i).getBh());

                AlertDialog dialog =new AlertDialog.Builder(mContext).setView(view).show();
                Button button1=view.findViewById(R.id.quxiao);
                Button button2=view.findViewById(R.id.ok);
                EditText editText=view.findViewById(R.id.ed_text);
                Log.d("++", "onClick: "+editText.toString());
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(editText.getText().toString().equals("")){
                            Toast.makeText(mContext,"充值失败",Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            return;

                        }
                        int cq=Integer.parseInt(editText.getText().toString());

                        if(cq>0&&cq<=999){

                            int ye=Integer.parseInt(String.valueOf(mList.get(i).getYe()));
                            ClczBeen been=new ClczBeen();
                            been.setBh(mList.get(i).getBh());
                            been.setYe(cq+ye);
                            ClczDao.insertnet(been);
                            CzliDao.insert(mList.get(i).getBh(),editText.getText().toString(),cq+ye+"","王", DateUtil.show());
                            Toast.makeText(mContext,"充值成功",Toast.LENGTH_LONG).show();
                            mList.get(i).setYe(cq+ye);
                            if(mUpadate!=null){
                                mUpadate.success();
                            }
                        }else {
                            Toast.makeText(mContext,"充值失败，金额为1-999",Toast.LENGTH_LONG).show();
                        }

                        dialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bhTextView;
        TextView yeTextView;
        CheckBox ischeck;
        Button cq;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bhTextView=itemView.findViewById(R.id.text_bh);
            yeTextView=itemView.findViewById(R.id.text_yue);
            ischeck=itemView.findViewById(R.id.ischeck);
            cq=itemView.findViewById(R.id.cz);
        }
    }
}
