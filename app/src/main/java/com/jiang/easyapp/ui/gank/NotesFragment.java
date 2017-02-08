package com.jiang.easyapp.ui.gank;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.easyapp.R;
import com.jiang.easyapp.api.remote.ApiFactory;
import com.jiang.easyapp.base.BaseSubscriber;
import com.jiang.easyapp.base.ui.BaseFragment;
import com.jiang.easyapp.model.gank.GankResult;
import com.jiang.easyapp.model.gank.NotesResult;
import com.jiang.easyapp.ui.gank.adapter.NoteAdapter;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class NotesFragment extends BaseFragment {

    private static final String PARAM_TYPE = "type";
    private RecyclerView mRecyclerView;
    private String mType;

    public static NotesFragment newInstance(String type) {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getString(PARAM_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_notes, container, false);
        mRecyclerView = (RecyclerView) inflateView.findViewById(R.id.list_notes);
        return inflateView;
    }

    @Override
    protected void fetchData() {
        ApiFactory.getGankApi().getNotes(mType, 10, 1)
                .subscribeOn(Schedulers.io())
                .map(new Func1<GankResult<List<NotesResult>>, List<NotesResult>>() {

                    @Override
                    public List<NotesResult> call(GankResult<List<NotesResult>> listGankResult) {
                        return listGankResult.getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<List<NotesResult>>() {
                    @Override
                    public void onNext(List<NotesResult> notesResults) {
                        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        mRecyclerView.setLayoutManager(manager);
                        mRecyclerView.setAdapter(new NoteAdapter(notesResults));
                    }
                });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }
}
