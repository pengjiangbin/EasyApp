package com.jiang.easyapp.model;

/**
 * Created by jiang on 2016/12/20.
 */

public interface BaseResult<T> {
    boolean isOK();
    T getData();
}
