package com.jiang.easyapp.ui.beauty;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.easyapp.R;


public class BeautyDetailFragment extends Fragment {

    public static BeautyDetailFragment newInstance(String param1, String param2) {
        BeautyDetailFragment fragment = new BeautyDetailFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflateView=inflater.inflate(R.layout.fragment_beauty_detail, container, false);
        return inflateView;
    }


}
