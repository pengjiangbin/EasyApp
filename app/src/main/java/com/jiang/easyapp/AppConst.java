package com.jiang.easyapp;

import java.io.File;

/**
 * Created by jiang on 2016/12/19.
 */

public class AppConst {
    /**
     * API
     */
    public static final String GANK_URL="http://gank.io/api/";

    /**
     * DATA
     */
    public static final String DATA_PATH=EasyApplication.getInstance().getCacheDir().getAbsolutePath()+ File.separator+"data";
    public static final String DATA_NET=DATA_PATH+File.separator+"net";

    /**
     * SP
     */
    public static final String KEY_CACHE="auto_cache";
}
