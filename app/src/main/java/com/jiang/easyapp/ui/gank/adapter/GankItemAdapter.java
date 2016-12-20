package com.jiang.easyapp.ui.gank.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.jiang.easyapp.ui.gank.GankItem;
import com.jiang.easyapp.ui.gank.NotesFragment;

/**
 * Created by jiang on 2016/12/21.
 */

public class GankItemAdapter extends FragmentPagerAdapter {
    private SparseArray<Fragment> mFragments;

    public GankItemAdapter(FragmentManager fm) {
        super(fm);
        mFragments=new SparseArray<>();
        for (GankItem item : GankItem.values()) {
            mFragments.put(item.getId(), NotesFragment.newInstance(item.getType()));
        }
    }


    @Override
    public Fragment getItem(int position) {

        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return GankItem.values().length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = GankItem.getItem(position).getTitle();
        return title == null ? "" : title;
    }
}
