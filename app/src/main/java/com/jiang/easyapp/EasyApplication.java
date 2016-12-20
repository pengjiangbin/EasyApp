package com.jiang.easyapp;

import android.app.Application;
import android.widget.Toast;

/**
 * Created by jiang on 2016/12/19.
 */

public class EasyApplication extends Application {
    private static EasyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
    public static EasyApplication getInstance(){
        return instance;
    }
    public static void toastShort(String message){
        Toast.makeText(instance,message,Toast.LENGTH_SHORT).show();
    }
}
