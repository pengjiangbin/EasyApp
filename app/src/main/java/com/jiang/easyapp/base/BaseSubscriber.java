package com.jiang.easyapp.base;

import android.content.Context;

import com.jiang.easyapp.api.exception.ApiException;
import com.jiang.easyapp.api.exception.ErrorHelper;
import com.jiang.easyapp.api.exception.ErrorType;
import com.jiang.easyapp.util.OSUtil;

import rx.Subscriber;

/**
 * Created by jiang on 2016/12/20.
 */

public class BaseSubscriber<T> extends Subscriber<T> {
    private Context mContext;

    public BaseSubscriber() {
    }

    public BaseSubscriber(Context context) {
        mContext = context;
    }

    @Override
    public void onStart() {
        if(!OSUtil.hasInternet()){
            this.onError(new ApiException("network interrupt", ErrorType.ERROR_INTERNET));
        }
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ErrorHelper.handleCommonError(mContext,e);
    }

    @Override
    public void onNext(T t) {

    }
}
