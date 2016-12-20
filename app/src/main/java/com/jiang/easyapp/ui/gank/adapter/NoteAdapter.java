package com.jiang.easyapp.ui.gank.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiang.easyapp.R;
import com.jiang.easyapp.model.gank.NotesResult;
import com.jiang.easyapp.util.ImageUtil;

import java.util.List;

/**
 * Created by jiang on 2016/12/20.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private List<NotesResult> mResults;

    public NoteAdapter(List<NotesResult> results) {
        mResults = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NotesResult result = mResults.get(position);
        String img_url = result.getImages() != null && result.getImages().size() > 0 ? result.getImages().get(0) : null;
        holder.tv_content.setText(result.getDesc());
        ImageUtil.load(holder.itemView.getContext(), img_url, holder.icon);
        holder.source.setText(result.getWho());
        String pushTime = result.getPublishedAt();
        if (pushTime != null && pushTime.contains("T")) {
            holder.push_time.setText(pushTime.split("T")[0]);
        }

    }

    @Override
    public int getItemCount() {
        return mResults == null ? 0 : mResults.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_content;
        ImageView icon;
        TextView source;
        TextView push_time;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            source = (TextView) itemView.findViewById(R.id.source);
            push_time = (TextView) itemView.findViewById(R.id.time);
        }
    }
}
