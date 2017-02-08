package com.jiang.easyapp.cache;

import android.content.Context;
import android.os.Environment;

import com.jiang.easyapp.util.FileUtil;
import com.jiang.easyapp.util.OSUtil;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jiang on 2016/12/21.
 */

public class DiskLruCacheUtil {
    /**
     * 缓存目录名称
     */
    public static final String CACHE_DIR = "EASY";
    /**
     * APP版本号
     */
    public static int versionCode = OSUtil.getVersionCode();
    /**
     * 同一个key对应的缓存文件个数
     */
    private static int valueCount = 1;
    /**
     * 一个缓存文件最大容量
     */
    private static int maxSize = 10 * 1024 * 1024;

    /**
     * 缓存数据
     */
    public static void saveObject(Context context, Serializable ser, String key) {
        ObjectOutputStream os = null;
        try {
            DiskLruCache.Editor editor = getDiskLruCacheEditor(context, CACHE_DIR, key);
            if (editor != null) {
                os = new ObjectOutputStream(editor.newOutputStream(0));
                os.writeObject(ser);
                os.flush();
                editor.commit();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtil.closeResource(os);
        }
    }

    /**
     * 读取数据
     */
    public static Serializable readObject(Context context, String key) {
        ObjectInputStream is = null;
        try {
            DiskLruCache.Editor editor = getDiskLruCacheEditor(context, CACHE_DIR, key);
            is = new ObjectInputStream(editor.newInputStream(0));
            return (Serializable) is.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            FileUtil.closeResource(is);
        }
        return null;
    }

    /**
     * 获取editor
     */
    public static DiskLruCache.Editor getDiskLruCacheEditor(Context context, String uniqueName, String key) throws IOException {
        DiskLruCache diskLruCache = DiskLruCache.open(getDiskCacheDir(context, uniqueName), versionCode, valueCount, maxSize);
        DiskLruCache.Editor editor = diskLruCache.edit(key);
        return editor;
    }

    /**
     * 获取缓存路径
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 将传入的key转换为相应的MD5值
     */
    public static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(key.getBytes());
            cacheKey = bytesToHexString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    /**
     *
     */
    private static String bytesToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hex);
        }
        return stringBuilder.toString();
    }
}
