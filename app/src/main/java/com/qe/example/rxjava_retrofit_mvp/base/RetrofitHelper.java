package com.qe.example.rxjava_retrofit_mvp.base;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p/>
 * Retrofit帮助类
 */
public class RetrofitHelper {

    private static OkHttpClient mOkHttpClient;

    static {
        initOkHttpClient();
    }

    public static Api getBoobApi() {
        return createApi(Api.class, " https://api.douban.com/");
    }


    /**
     * 根据传入的baseUrl，和api创建retrofit
     */
    private static <T> T createApi(Class<T> clazz, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }


    /**
     * 初始化OKHttpClient,设置缓存,设置超时时间,设置打印日志,设置Request拦截器
     */
    private static void initOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(interceptor)
                            .addInterceptor(new RequestInterceptor())
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    private static class RequestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (request.method().equals("POST")) {
                if (request.body() instanceof FormBody) {
                    request = addPostFormParams(request);
                } else if (request.body() instanceof MultipartBody) {
                    request = addPostMultiParams(request);
                }
            } else if (request.method().equals("GET")) {
                request = addGetParams(chain);
            }
            return chain.proceed(request);
        }
    }

    //上传时
    private static Request addPostMultiParams(Request request) {
        // 添加公共参数
        MultipartBody.Builder builder = new MultipartBody.Builder().addFormDataPart("deviceId", "123456");
        MultipartBody oldBody = (MultipartBody) request.body();
        for (int i = 0; i < oldBody.size(); i++) {
            builder.addPart(oldBody.part(i));
        }
        oldBody = builder.build();
        request = request.newBuilder().post(oldBody).build();
        return request;
    }

    //正常时
    private static Request addPostFormParams(Request request) {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        FormBody formBody = (FormBody) request.body();
        //把原来的参数添加到新的构造器，（因为没找到直接添加，所以就new新的）
        for (int i = 0; i < formBody.size(); i++) {
            bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
        }
        //添加公共参数
        formBody = bodyBuilder
                .addEncoded("deviceId", "123456").build();
        request = request.newBuilder().post(formBody).build();
        return request;
    }

    private static Request addGetParams(Interceptor.Chain chain) {
        Request request;
        Request oldRequest = chain.request();
        // 添加公共参数
        HttpUrl authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .addQueryParameter("deviceId", "123456").build();
        request = oldRequest.newBuilder()
                .url(authorizedUrlBuilder)
                .build();
        return request;
    }
}
