package com.jiang.easyapp.ui.gank.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jiang.easyapp.R;
import com.jiang.easyapp.model.gank.BeautyResult;
import com.jiang.easyapp.util.ImageUtil;

import java.util.List;

/**
 * Created by jiang on 2016/12/20.
 */

public class BeautyAdapter extends RecyclerView.Adapter<BeautyAdapter.ViewHolder> {
    private List<BeautyResult> mResults;

    public BeautyAdapter(List<BeautyResult> results) {
        mResults = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beauty, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BeautyResult result = mResults.get(position);
        String img_url = result.getUrl();
        ImageUtil.load(holder.itemView.getContext(),img_url,holder.img_beauty);
    }

    @Override
    public int getItemCount() {
        return mResults != null ? mResults.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_beauty;

        public ViewHolder(View itemView) {
            super(itemView);
            img_beauty = (ImageView) itemView.findViewById(R.id.img_beauty);
        }
    }
}
