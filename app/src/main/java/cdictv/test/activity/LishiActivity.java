package cdictv.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cdictv.test.adatpter.LsAdapter;
import cdictv.test.bean.CzliBeen;
import cdictv.test.R;
import cdictv.test.database.CzliDao;

public class LishiActivity extends AppCompatActivity {
    private List<CzliBeen> mCzliBeens=new ArrayList<>();
    private Spinner mSpinner;
    RecyclerView mRecyclerView;
    //查询按钮
    private Button btn_select;
    LsAdapter listAdapter;
    String args;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lishi);
       List<CzliBeen> list= CzliDao.paixu("");
       getView();
       mCzliBeens.addAll(list);
        for (CzliBeen been:list
                ) {
            Log.d("been22", "onCreate: "+been.toString());

        }
        if(mCzliBeens.size()==0){
            TextView textView=findViewById(R.id.te_gone);
            textView.setVisibility(View.VISIBLE);
        }else {

            LinearLayoutManager manager=new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(manager);
             listAdapter=  new LsAdapter(mCzliBeens,this);

            mRecyclerView.setAdapter(listAdapter);
        }
        initListen();

    }

    private void initListen() {
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        args= (String) parent.getItemAtPosition(position);
//                         Toast.makeText(getApplicationContext(), args,Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                      args= (String) parent.getItemAtPosition(position);
//                        Toast.makeText(getApplicationContext(), args,Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               List<CzliBeen>mCzliBeens=Litepalutil.paixu(args);
               mCzliBeens.clear();
                List<CzliBeen>CzliBeens=CzliDao.paixu(args);
                mCzliBeens.addAll(CzliBeens);
                for (CzliBeen  beens :mCzliBeens
                     ) {
                    Log.d("", "onClick: "+beens.toString());
                }
               listAdapter.notifyDataSetChanged();
            }
        });
    }
    public void getView() {
        mRecyclerView=findViewById(R.id.recy);
      mSpinner = findViewById(R.id.sp_time_order);
        btn_select=findViewById(R.id.btn_select);
    }
}
