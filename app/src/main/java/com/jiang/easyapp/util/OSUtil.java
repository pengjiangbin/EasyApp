package com.jiang.easyapp.util;

import android.content.Context;
import android.content.pm.PackageManager;
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
    public static int getVersionCode(){
        int versionCode=1;
        try {
            versionCode=EasyApplication.getInstance().getPackageManager().getPackageInfo(EasyApplication.getInstance().getPackageName(),0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            versionCode=1;
        }
        return versionCode;
    }
}
