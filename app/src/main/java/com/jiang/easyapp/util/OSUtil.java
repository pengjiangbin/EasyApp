package com.jiang.easyapp.util;

import android.content.Context;
import android.net.ConnectivityManager;

import com.jiang.easyapp.EasyApplication;

/**
 * Created by jiang on 2016/12/19.
 */

public class OSUtil {
    public static boolean hasInternet() {
        ConnectivityManager manager = (ConnectivityManager) EasyApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean flag = manager.getActiveNetworkInfo() != null;
        return flag;
    }
}
