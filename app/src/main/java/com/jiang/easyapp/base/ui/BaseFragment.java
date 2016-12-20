package com.jiang.easyapp.base.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * Created by jiang on 2016/12/20.
 */

public abstract class BaseFragment extends RxFragment {
    protected boolean mIsViewInit;
    protected boolean mIsUserVisible;
    protected boolean mIsDataFetched;
    protected Context mContext;

    /**
     * 绑定数据
     */
    protected abstract void fetchData();


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mIsViewInit = true;
        initData();
    }

    protected void initData() {
        if (mIsViewInit && mIsUserVisible && !mIsDataFetched) {
            fetchData();
            mIsDataFetched = true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsUserVisible = isVisibleToUser;
        initData();
    }


}
