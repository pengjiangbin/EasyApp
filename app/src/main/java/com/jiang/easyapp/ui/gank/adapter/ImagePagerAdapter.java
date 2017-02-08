package com.jiang.easyapp.ui.gank.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.jiang.easyapp.R;
import com.jiang.easyapp.model.gank.BeautyResult;

import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by jiang on 2016/12/21.
 */

public class ImagePagerAdapter extends PagerAdapter {
    private List<BeautyResult> mImages;
    private SparseArray<View> cacheViews;
    private Context mContext;

    public ImagePagerAdapter(List<BeautyResult> images,Context context) {
        mImages = images;
        this.mContext=context;
        cacheViews=new SparseArray<>(mImages.size());
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String img_url=mImages.get(position).getUrl();
        View view=cacheViews.get(position);
        if(view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_image_detail,container,false);
            view.setTag(position);
            ImageView imageView= (ImageView) view.findViewById(R.id.img_detail);
            final PhotoViewAttacher attacher=new PhotoViewAttacher(imageView);
            Glide.with(mContext).load(img_url).into(new GlideDrawableImageViewTarget(imageView){
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                    super.onResourceReady(resource, animation);
                    attacher.update();
                }
            });
            attacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {

                }
            });
            cacheViews.put(position,view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view= (View) object;
        container.removeView(view);
    }
}
