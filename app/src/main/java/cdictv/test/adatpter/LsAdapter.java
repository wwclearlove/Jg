package cdictv.test.adatpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cdictv.test.bean.CzliBeen;
import cdictv.test.R;
import cdictv.test.network.Upadate;

public class LsAdapter extends RecyclerView.Adapter<LsAdapter.ViewHolder> {
    private List<CzliBeen> mList;
    private Upadate mUpadate;
    private Context mContext;
    public LsAdapter(List<CzliBeen>list,Context context){
        mContext=context;
        mList=list;
        for (CzliBeen been:mList
             ) {
            Log.d("been", "onCreate: "+been.toString());

        }
    }

    @NonNull
    @Override
    public LsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.ls_item,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LsAdapter.ViewHolder viewHolder, int i) {
        CzliBeen been=mList.get(i);
        viewHolder.xu.setText(i+1+"");
        viewHolder.ch.setText(been.getBh()+"");
        viewHolder.czje.setText(been.getJe()+"");
        viewHolder.name.setText(been.getName());
        viewHolder.date.setText(been.getDate());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView xu;
        TextView ch;
        TextView czje;
        TextView name;
        TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            xu=itemView.findViewById(R.id.it_xuhao);
            ch=itemView.findViewById(R.id.item_ch);
            czje=itemView.findViewById(R.id.it_czje);
            name=itemView.findViewById(R.id.it_name);
            date=itemView.findViewById(R.id.it_czsj);

        }
    }
}
