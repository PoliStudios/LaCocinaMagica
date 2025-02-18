package com.polistudios.lacocinamagica.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.polistudios.lacocinamagica.R;
import com.polistudios.lacocinamagica.models.VideoItem;

import java.util.List;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.viewHolder> {
    private List<VideoItem> mVideosList;

    public VideoAdapter(List<VideoItem> videoItems)
    {
        mVideosList = videoItems;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.setData(position);
        VideoItem data = mVideosList.get(position);
    }

    @Override
    public int getItemCount() {
        return mVideosList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public VideoView mVideoView;
        public ProgressBar mProgressBar;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void setData(int position)
        {
            if(mVideosList.get(position).videoURL != null)
            {
                mVideoView.setVideoURI(Uri.parse(mVideosList.get(position).videoURL));
            }

            mVideoView.setOnPreparedListener(mediaPlayer -> {
                mProgressBar.setVisibility(View.INVISIBLE);
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
            });
        }
    }
}
