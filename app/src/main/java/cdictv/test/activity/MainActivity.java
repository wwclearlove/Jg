package cdictv.test.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cdictv.test.R;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    LinearLayout mLinearLayout;
    ImageView mImageView;
    private  int sag=1;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawerLayout);
        mLinearLayout=findViewById(R.id.navigation_view);
        mImageView=findViewById(R.id.left_menu);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sag==1)
                {
                    drawerLayout.openDrawer(Gravity.LEFT);
                    sag=2;
                }
                if (sag==2){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }

            }
        });

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                //设置拉出布局的宽度
                mLinearLayout.setX(slideOffset * drawerView.getWidth());

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                Log.e("==", "onDrawerSlide: " + "完全展开时执行");
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                Log.e("==", "onDrawerSlide: " + "完全关闭时执行");
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                Log.e("==", "onDrawerSlide: " + "改变状态时时执行");
            }
        });
    }


    }



