package com.jiang.easyapp.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by jiang on 2016/12/20.
 */

public class ImageUtil {
    public static void load(Context context, String url, ImageView view) {
        Glide.with(context).load(url).into(view);
    }
}
