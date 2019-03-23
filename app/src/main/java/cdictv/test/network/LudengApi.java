package cdictv.test.network;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class LudengApi {
    private static  final String path1="lamplist";

    public static  void show(String parameters, String headers, MyCall myCall) {
        RequestBody body = new FormBody.Builder()
                .add("queryParameters",parameters)
                .add("headers", headers)
                .build();
        Request request = new Request.Builder()
                .url(NetworkApi.BASE_USL +path1)
                .post(body)
                .build();
    NetworkApi.getNetworkApi().request(request,myCall);
    }
    public static  void cheliang( MyCall myCall) {
        RequestBody body = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url(NetworkApi.uri)
                .post(body)
                .build();
        NetworkApi.getNetworkApi().request(request,myCall);
    }
}
