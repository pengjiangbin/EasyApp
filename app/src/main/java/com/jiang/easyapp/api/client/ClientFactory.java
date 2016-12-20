package com.jiang.easyapp.api.client;

import com.jiang.easyapp.AppConst;
import com.jiang.easyapp.BuildConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;


/**
 * Created by jiang on 2016/12/19.
 */

public class ClientFactory {
    private volatile OkHttpClient mOkHttpClient;
    private OkHttpClient.Builder mBuilder;
    private static final long CONNECTION_TIMEOUT = 15;
    private static final long READ_TIMEOUT = 15;

    private ClientFactory() {
        mBuilder = new OkHttpClient.Builder();
        if (BuildConfig.LOG_DEBUG) {
            mBuilder.addInterceptor(ClientHelper.getLoggingInterceptor());
        }
        Cache cache = new Cache(new File(AppConst.DATA_NET), 10 * 1024 * 1024);
        mBuilder.retryOnConnectionFailure(true)
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(ClientHelper.getCacheInterceptor())
                .addInterceptor(ClientHelper.getCacheInterceptor());
    }

    private static class SingleHolder {
        private static final ClientFactory INSTANCE = new ClientFactory();
    }

    public static ClientFactory getInstance() {
        return SingleHolder.INSTANCE;
    }


    public OkHttpClient getOkHttpClient() {
        mOkHttpClient = mBuilder.build();
        return mOkHttpClient;
    }


}
