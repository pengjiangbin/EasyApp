package com.jiang.easyapp.api.exception;

/**
 * Created by jiang on 2016/12/20.
 */

public class ApiException extends RuntimeException {
    private int mErrorCode;
    private String mErrorMsg;

    public ApiException(String msg, int errorCode) {
        super(msg);
        this.mErrorCode = errorCode;

    }

    public ApiException(String message, int errorCode, String errorMsg) {
        super(message);
        mErrorCode = errorCode;
        mErrorMsg = errorMsg;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public String getErrorMsg() {
        return mErrorMsg;
    }
}
