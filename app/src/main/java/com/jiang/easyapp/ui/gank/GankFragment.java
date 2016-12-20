package com.jiang.easyapp.ui.gank;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.easyapp.R;


public class GankFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mPager;
    private String mType;
    private String mTitle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_gank, container, false);
        mTabLayout = (TabLayout) inflateView.findViewById(R.id.tab_gank);
        mPager = (ViewPager) inflateView.findViewById(R.id.pager_gank);
        mPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        mType = "Android";
                        break;
                    case 1:
                        mType = "iOS";
                        break;
                    case 2:
                        mType = "前端";
                        break;
                }
                return NotesFragment.newInstance(mType);

            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        mTitle = "Android";
                        break;
                    case 1:
                        mTitle = "IOS";
                        break;
                    case 2:
                        mTitle = "WEB";
                        break;
                    default:
                        mTitle = "默认";
                        break;

                }
                return mTitle;
            }
        });
        mTabLayout.setupWithViewPager(mPager);
        return inflateView;
    }


}
