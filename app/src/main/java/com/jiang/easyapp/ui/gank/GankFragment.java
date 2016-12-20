package com.jiang.easyapp.ui.gank;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.easyapp.R;
import com.jiang.easyapp.ui.gank.adapter.GankItemAdapter;


public class GankFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mPager;
    private GankItemAdapter mItemAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_gank, container, false);
        mTabLayout = (TabLayout) inflateView.findViewById(R.id.tab_gank);
        mPager = (ViewPager) inflateView.findViewById(R.id.pager_gank);
        mItemAdapter=new GankItemAdapter(getChildFragmentManager());
        mPager.setOffscreenPageLimit(mItemAdapter.getCount());
        mPager.setAdapter(mItemAdapter);
        mTabLayout.setupWithViewPager(mPager);
        return inflateView;
    }



}
