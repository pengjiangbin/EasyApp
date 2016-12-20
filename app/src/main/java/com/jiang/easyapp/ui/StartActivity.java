package com.jiang.easyapp.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.jiang.easyapp.R;
import com.jiang.easyapp.router.Router;

public class StartActivity extends AppCompatActivity {
    private static final long TIME_DELAY = 3000;
    ObjectAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_start);
        RelativeLayout activity_start = (RelativeLayout) findViewById(R.id.activity_start);
        animator = ObjectAnimator.ofFloat(activity_start, "alpha", 0, 1);
        animator.setDuration(TIME_DELAY);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Router.showMain(StartActivity.this);
            }

            @Override
            public void onAnimationCancel(Animator animation) {


            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    @Override
    public void onBackPressed() {
        if (animator != null) {
            animator.cancel();
            animator = null;
        }
        super.onBackPressed();
    }
}
