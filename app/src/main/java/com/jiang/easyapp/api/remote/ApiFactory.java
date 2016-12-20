package com.jiang.easyapp.api.remote;

import com.jiang.easyapp.AppConst;
import com.jiang.easyapp.api.client.ClientFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jiang on 2016/12/19.
 */

public class ApiFactory {
    private static GankApi gankApi;

    public static GankApi getGankApi() {
        if (gankApi == null) {
            gankApi = createApi(AppConst.GANK_URL, GankApi.class);
        }
        return gankApi;
    }

    private static <T> T createApi(String baseUrl, Class<T> clazz) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(ClientFactory.getInstance().getOkHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build().create(clazz);
    }
}
