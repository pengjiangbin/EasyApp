package com.jiang.easyapp.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.easyapp.R;
import com.jiang.easyapp.base.BaseSubscriber;
import com.jiang.easyapp.model.BaseResult;

/**
 * Created by jiang on 2016/12/21.
 */

public abstract class BaseListFragment<T> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected RecyclerView mRecyclerView;

    protected SwipeRefreshLayout mRefreshLayout;

//    protected BaseListAdapter<T> mAdapter;
    /**
     * 刷新
     */
    public static final int STATE_REFRESH = 0;
    /**
     * 加载更多
     */
    public static final int STATE_LOADMORE = 1;

    protected BaseSubscriber<BaseResult<T>> mSubscriber=new BaseSubscriber<BaseResult<T>>(){
        @Override
        public void onNext(BaseResult<T> result) {
          if(result.isOK()){

          }else{

          }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initLayoutID(), container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_view);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        return view;
    }

    protected abstract int initLayoutID();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void initView() {
//        mRefreshLayout.setOnRefreshListener(this);
//        if (mAdapter != null) {
//            mRecyclerView.setAdapter(mAdapter);
//        } else {
//            mAdapter = getAdapter();
//            mRecyclerView.setAdapter(mAdapter);
//        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    protected void fetchData() {

    }

//    protected abstract BaseListAdapter<T> getAdapter();
}
