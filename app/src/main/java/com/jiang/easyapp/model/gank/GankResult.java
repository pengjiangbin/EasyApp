package com.jiang.easyapp.model.gank;

import com.jiang.easyapp.model.BaseResult;

/**
 * Created by jiang on 2016/12/20.
 */

public class GankResult<T> implements BaseResult<T> {
    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public T getResults() {
        return results;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(T results) {
        this.results = results;
    }

    @Override
    public boolean isOK() {
        return error;
    }

    @Override
    public T getData() {
        return results;
    }
}
