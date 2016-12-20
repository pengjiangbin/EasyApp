package com.jiang.easyapp.api.exception;

/**
 * Created by jiang on 2016/12/20.
 */

public interface ErrorType {
    /**
     * 客户端错误
     */
    int ERROR_CLIENT = 0;
    /**
     * 用户授权错误
     */
    int ERROR_USER_AUTHORIZE = 1;
    /**
     * 请求参数错误
     */
    int ERROR_REQUEST_PARAM = 2;
    /**
     * 网络错误
     */
    int ERROR_INTERNET = 3;
    /**
     * 参数校验错误
     */
    int ERROR_PARAM_CHECK = 4;
}
