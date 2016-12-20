package com.jiang.easyapp.util;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TabHost;

import java.util.ArrayList;

/**
 * Created by jiang on 2016/8/27.
 */
public class FragmentTabManager {
    private Context mContext;
    private FragmentManager mFragmentManager;//fragment管理器
    private int mContainerId;
    private ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
    private TabInfo mLastTab;//当前手机显示的fragment
    private TabHost.OnTabChangeListener mOnTabChangeListener;

    public FragmentTabManager(Context context, FragmentManager fragmentManager, int containerId) {
        mContext = context;
        mFragmentManager = fragmentManager;
        mContainerId = containerId;
    }

    /**
     * 添加选项卡
     *
     * @param tag   fragment的tag
     * @param clazz fragment的class
     * @param args  fragment参数
     * @return
     */
    public FragmentTabManager addTab(String tag, Class<?> clazz, Bundle args) {
        TabInfo tabInfo = new TabInfo(tag, clazz, args);
        mTabs.add(tabInfo);
        return this;
    }

    /**
     * 获取当前的fragment
     *
     * @return
     */
    public Fragment getCurrentFragment() {
        return mLastTab == null ? null : mLastTab.fragment;
    }

    /**
     * 获取当前的fragment的tag
     *
     * @return
     */
    public String getCurrentTab() {
        return mLastTab == null ? null : mLastTab.tag;
    }

    /**
     * 设置tab变化监听
     *
     * @param listener
     */
    public void setOnTabChangedListener(TabHost.OnTabChangeListener listener) {
        mOnTabChangeListener = listener;
    }

    /**
     * 解除当前fragment的viewTree
     */
    public void detach() {
        if (mLastTab != null) {
            if (mLastTab.fragment != null) {
                mFragmentManager.beginTransaction().detach(mLastTab.fragment).commit();
            }
            mLastTab = null;
        }
    }

    /**
     * 保存fragment的状态
     *
     * @param tabId
     */
    public void restoreTab(String tabId) {
        TabInfo newTab = null;
        for (int i = 0; i < mTabs.size(); i++) {
            TabInfo tab = mTabs.get(i);
            tab.fragment = mFragmentManager.findFragmentByTag(tab.tag);
            if (tabId.equals(tab.tag)) {
                newTab = tab;
            }
        }
        mLastTab = newTab;
    }

    /**
     * 根据tabId切换fragment
     *
     * @param tabId 需要显示的fragment的tabId
     */
    public void changeTab(String tabId) {
        FragmentTransaction ft = doTabChange(tabId, null);
        if (ft != null)
            ft.commitAllowingStateLoss();
        if (mOnTabChangeListener != null)
            mOnTabChangeListener.onTabChanged(tabId);
    }


    private FragmentTransaction doTabChange(String tabId, FragmentTransaction ft) {
        TabInfo newTab = null;
        for (int i = 0; i < mTabs.size(); i++) {
            TabInfo tabInfo = mTabs.get(i);
            if (tabInfo.tag.equalsIgnoreCase(tabId)) {
                newTab = tabInfo;
            }
        }
        if (newTab == null)
            throw new IllegalStateException("tab unknown" + tabId);
        if (mLastTab != newTab) {
            if (ft == null)
                ft = mFragmentManager.beginTransaction();
            if (mLastTab != null) {
                if (mLastTab.fragment != null) {
                    ft.hide(mLastTab.fragment);
                }
            }
            if (newTab.fragment == null) {
                newTab.fragment = Fragment.instantiate(mContext, newTab.clazz.getName(), newTab.args);
                ft.add(mContainerId, newTab.fragment, newTab.tag);
            } else {
                ft.show(newTab.fragment);
            }
            mLastTab = newTab;
        }

        return ft;
    }

    /**
     * fragment的销毁
     *
     * @param tabId 需要销毁fragment的id
     */
    public void destory(String tabId) {
        TabInfo newTab = null;
        for (int i = 0; i < mTabs.size(); i++) {
            TabInfo tab = mTabs.get(i);
            if (tab.tag.equals(tabId)) {
                newTab = tab;
            }
        }
        if (newTab == null) {
            throw new IllegalStateException("tab unknown" + tabId);
        }
        if (newTab.fragment != null) {
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.remove(newTab.fragment).commitAllowingStateLoss();
            newTab.fragment = null;
        }
        if (newTab == mLastTab) {
            mLastTab = null;
        }
    }

    /**
     * fragment选项卡
     */
    class TabInfo {
        private String tag;
        private Class<?> clazz;
        private Bundle args;
        private Fragment fragment;

        public TabInfo(String tag, Class<?> clazz, Bundle args) {
            this.tag = tag;
            this.clazz = clazz;
            this.args = args;
        }
    }
}
