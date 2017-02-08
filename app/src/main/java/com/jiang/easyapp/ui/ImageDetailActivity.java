package com.jiang.easyapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jiang.easyapp.R;
import com.jiang.easyapp.model.gank.BeautyResult;
import com.jiang.easyapp.ui.gank.adapter.ImagePagerAdapter;

import java.util.List;

public class ImageDetailActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private List<BeautyResult> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        Intent intent = getIntent();
        mList = intent.getParcelableArrayListExtra("images");
        mViewPager.setAdapter(new ImagePagerAdapter(mList,this));
    }
}
