package cdictv.test.network;



import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;



public class OkhttpApi {
    private static  final String path1="lamplist";
    private static  final String path2="gongjiao";
    private static  final String path3="login";
    public static  void login(String name, String password, MyCall myCall) {
        RequestBody body = new FormBody.Builder()
                .add("username",name)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url(NetworkApi.BASE_USL +path3)
                .post(body)
                .build();
        NetworkApi.getNetworkApi().request(request,myCall);

    }
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
    public static  void gongjiao( MyCall myCall) {
        RequestBody body = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url(NetworkApi.BASE_USL +path2)
                .post(body)
                .build();
        NetworkApi.getNetworkApi().request(request,myCall);
    }
    public static  void cheliang( MyCall myCall) {
        RequestBody body = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url(NetworkApi.uri)
//                .post(body)
                .build();
        NetworkApi.getNetworkApi().request(request,myCall);
    }

    public static  void luKuang( MyCall myCall) {

        RequestBody body = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url(NetworkApi.LUKUANGuri)
                .post(body)
                .build();
        NetworkApi.getNetworkApi().request(request,myCall);

    }
}
