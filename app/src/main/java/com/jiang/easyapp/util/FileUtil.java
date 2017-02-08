package com.jiang.easyapp.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by jiang on 2016/12/22.
 */

public class FileUtil {
    public static  void closeResource(Closeable...closeables){
        if(closeables==null){
            return;
        }
        for (Closeable closeable : closeables) {
            if(closeable!=null){
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
