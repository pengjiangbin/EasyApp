package com.jiang.easyapp.api.client;

import com.jiang.easyapp.util.OSUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by jiang on 2016/12/19.
 */

public class ClientHelper {
    public static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    public static Interceptor getCacheInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if(!OSUtil.hasInternet()){
                    //无网络情况下，强制使用缓存,此请求不会真正的被发送出去
                    request=request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                }
                Response response=chain.proceed(request);
                if(OSUtil.hasInternet()){
                    String control=request.cacheControl().toString();
                    if(control!=null){//如果服务端设置了缓存，则使用服务端缓存
                        return response.newBuilder().header("Cache-Control",control).build();
                    }else{//如果服务端没有设置缓存，则由客户端设置缓存策略
                        return response.newBuilder().header("Cache-Control","public, max-age=36000").build();
                    }
                }else{
                    return response.newBuilder().header("Cache-Control","public, only-if-cached, max-stale=3600000").build();
                }

            }
        };
        return interceptor;
    }

}
