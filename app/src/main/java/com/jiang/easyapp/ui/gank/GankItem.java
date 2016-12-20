package com.jiang.easyapp.ui.gank;

/**
 * Created by jiang on 2016/12/21.
 */

public enum GankItem {
    ANDROID_ITEM(0, "Android", "Android", NotesFragment.class),
    IOS_ITEM(1, "IOS", "iOS", NotesFragment.class),
    WEB_ITEM(2, "WEB", "前端", NotesFragment.class);
    private int id;
    private String title;
    private String type;
    private Class<?> clazz;

    GankItem(int id, String title, String type, Class<?> clazz) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.clazz = clazz;
    }

    public static GankItem getItem(int id) {
        for (GankItem item : values()) {
            if (item.getId() == id) {
                return item;
            }
        }
        return ANDROID_ITEM;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
