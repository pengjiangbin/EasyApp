package com.jiang.easyapp.api.exception;

import android.content.Context;

import com.jiang.easyapp.EasyApplication;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by jiang on 2016/12/20.
 */

public class ErrorHelper {
    public static void handleCommonError(Context context, Throwable e){
        if(e instanceof HttpException||e instanceof IOException){
            EasyApplication.toastShort("无网络连接");
        }else if(e instanceof ApiException){
            handleError(context,e);
        }

    }

    private static void handleError(Context context, Throwable e) {
        ApiException exception = (ApiException) e;
        switch (exception.getErrorCode()) {
            case ErrorType.ERROR_CLIENT:
                EasyApplication.toastShort("非法客户端");
                break;
            case ErrorType.ERROR_USER_AUTHORIZE:
                EasyApplication.toastShort("用户授权失败");
                break;
            case ErrorType.ERROR_REQUEST_PARAM:
                EasyApplication.toastShort("请求参数错误");
                break;
            case ErrorType.ERROR_INTERNET:
                EasyApplication.toastShort("无网络连接");
                break;
            case ErrorType.ERROR_PARAM_CHECK:
                EasyApplication.toastShort("非法请求参数");
                break;
            default:
                EasyApplication.toastShort("系统异常，请稍后再试");
                break;
        }
    }
}
