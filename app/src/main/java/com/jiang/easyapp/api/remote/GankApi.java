package com.jiang.easyapp.api.remote;

import com.jiang.easyapp.model.gank.BeautyResult;
import com.jiang.easyapp.model.gank.GankResult;
import com.jiang.easyapp.model.gank.NotesResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by jiang on 2016/12/19.
 */

public interface GankApi {
    /**
     * 获取文章
     */
    @GET("data/{category}/{num}/{page}")
    Observable<GankResult<List<NotesResult>>> getNotes(@Path("category") String category, @Path("num") int num, @Path("page") int page);
    /**
     * 获取福利
     */
    @GET("data/福利/{num}/{page}")
    Observable<GankResult<List<BeautyResult>>> getBeauty(@Path("num") int num, @Path("page") int page);
}
