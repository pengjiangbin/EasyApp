package com.jiang.easyapp.ui.beauty;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.easyapp.R;
import com.jiang.easyapp.api.remote.ApiFactory;
import com.jiang.easyapp.base.BaseSubscriber;
import com.jiang.easyapp.base.ui.BaseFragment;
import com.jiang.easyapp.model.gank.BeautyResult;
import com.jiang.easyapp.model.gank.GankResult;
import com.jiang.easyapp.ui.gank.adapter.BeautyAdapter;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class BeautyFragment extends BaseFragment {
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_beauty, container, false);
        mRecyclerView = (RecyclerView) inflateView.findViewById(R.id.list_beauty);
        return inflateView;
    }

    @Override
    protected void fetchData() {
        ApiFactory.getGankApi().getBeauty(20, 1)
                .map(new Func1<GankResult<List<BeautyResult>>, List<BeautyResult>>() {
                    @Override
                    public List<BeautyResult> call(GankResult<List<BeautyResult>> listGankResult) {
                        return listGankResult.getData();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<List<BeautyResult>>() {
                    @Override
                    public void onNext(List<BeautyResult> beautyResults) {
                        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                        mRecyclerView.setLayoutManager(manager);
                        mRecyclerView.setAdapter(new BeautyAdapter(beautyResults));
                    }
                });
    }


}
