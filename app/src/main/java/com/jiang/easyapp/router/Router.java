package com.jiang.easyapp.router;

import android.app.Activity;
import android.content.Intent;

import com.jiang.easyapp.ui.MainActivity;

/**
 * Created by jiang on 2016/12/19.
 */

public class Router {
    public static void showMain(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }
}
