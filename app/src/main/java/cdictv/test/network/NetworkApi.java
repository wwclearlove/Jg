package cdictv.test.network;

import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cdictv.test.App;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkApi  {

    public static final String BASE_USL = "https://easy-mock.com/mock/" +
            "5c8f3515c42b1c0235654282/jiaotong/";
    public static final String uri="https://getman.cn/mock/cheliang";
     private static  NetworkApi networkApi;
    private  OkHttpClient okHttpClient;
    private Handler mHandler=new Handler() ;

    private NetworkApi(){
        okHttpClient =
                new OkHttpClient.Builder().
                        readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .connectTimeout(30,TimeUnit.SECONDS).build();
    }
    public static NetworkApi getNetworkApi(){
        if(networkApi == null){
            synchronized (NetworkApi.class){
                if (networkApi == null){
                    networkApi =new NetworkApi();
                }
            }

        }
        return networkApi;
    }

   public void request(Request request, final MyCall myCall) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCall.failed();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (TextUtils.isEmpty(str)) {
                            Toast.makeText(App.INSTANCE, "数据为空", Toast.LENGTH_LONG).show();
                            myCall.failed();
                        }else {
                            myCall.success(str);
                        }

                    }
                });

            }
        });
    }
}