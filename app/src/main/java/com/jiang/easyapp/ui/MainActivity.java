package com.jiang.easyapp.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.jiang.easyapp.R;
import com.jiang.easyapp.ui.beauty.BeautyFragment;
import com.jiang.easyapp.ui.gank.GankFragment;
import com.jiang.easyapp.ui.setting.SettingFragment;
import com.jiang.easyapp.util.FragmentTabManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String GANK = "gank", BEAUTY = "beauty", SETTING = "setting";
    private FragmentTabManager mTabManager;
    private TextView mTv_gank, mTv_beauty, mTv_setting, mTv_title;
    private Toolbar mToolbar;
    private long mLastCurrentTime;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv_gank = (TextView) findViewById(R.id.tv_gank);
        mTv_beauty = (TextView) findViewById(R.id.tv_beauty);
        mTv_setting = (TextView) findViewById(R.id.tv_setting);
        mTv_title = (TextView) findViewById(R.id.tv_title);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mTv_gank.setOnClickListener(this);
        mTv_beauty.setOnClickListener(this);
        mTv_setting.setOnClickListener(this);
        mTabManager = new FragmentTabManager(this, getSupportFragmentManager(), R.id.frg_main);
        mTabManager.addTab(GANK, GankFragment.class, null)
                .addTab(BEAUTY, BeautyFragment.class, null)
                .addTab(SETTING, SettingFragment.class, null);

        mTabManager.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                MainActivity.this.onTabChange(tabId);
            }
        });
        if (savedInstanceState == null) {
            mTabManager.changeTab(GANK);
            mTv_title.setText("每日干货");
        } else {
            String tabID = savedInstanceState.getString("tabID");
            mTabManager.changeTab(tabID);
            mTabManager.restoreTab(tabID);
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("tabID", mTabManager.getCurrentFragment().getTag());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_gank:
                mTabManager.changeTab(GANK);
                mTv_title.setText("每日干货");
                break;
            case R.id.tv_beauty:
                mTabManager.changeTab(BEAUTY);
                mTv_title.setText("福利");
                break;
            case R.id.tv_setting:
                mTabManager.changeTab(SETTING);
                mTv_title.setText("设置");
                break;
        }
    }

    private void onTabChange(String tabId) {
        mTv_gank.setSelected(GANK.equals(tabId));
        mTv_beauty.setSelected(BEAUTY.equals(tabId));
        mTv_setting.setSelected(SETTING.equals(tabId));
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mLastCurrentTime < 2000) {
            super.onBackPressed();
        } else {
            mLastCurrentTime=System.currentTimeMillis();
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();

        }
    }
}
