package com.polistudios.saborescompartidos.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.polistudios.saborescompartidos.R;
import com.polistudios.saborescompartidos.models.VideoItem;

import java.util.List;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.viewHolder> {
    private List<VideoItem> mVideosList;
    private Context context;

    public VideoAdapter(List<VideoItem> videoItems, Context ctx)
    {
        mVideosList = videoItems;
        context = ctx;
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

        if(data.videoTitle != null)
            holder.tvTitle.setText(data.videoTitle);

        if(data.videoDescription != null)
            holder.tvDesc.setText(data.videoDescription);

        if(data.likes != null)
            holder.tvLikes.setText(data.likes);

        if(data.shares != null)
            holder.tvShares.setText(data.shares);
    }

    @Override
    public int getItemCount() {
        return mVideosList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public VideoView mVideoView;
        public ProgressBar mProgressBar;
        public ImageView mProfileImage;
        public TextView tvLikes, tvComments, tvShares, tvTitle, tvDesc;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            mVideoView = itemView.findViewById(R.id.reels_videoView);
            mProgressBar = itemView.findViewById(R.id.reels_progressBar);
            mProfileImage = itemView.findViewById(R.id.reels_imgProfilePic);
            tvLikes = itemView.findViewById(R.id.reels_tvStar);
            tvComments = itemView.findViewById(R.id.reels_tvComment);
            tvShares = itemView.findViewById(R.id.reels_tvShare);
            tvTitle = itemView.findViewById(R.id.reels_tvTitle);
            tvDesc = itemView.findViewById(R.id.reels_tvDesc);

            mProgressBar.setVisibility(View.VISIBLE);
        }

        void setData(int position)
        {
            new Thread(() -> {
                if (mVideosList.get(position).videoURL != null) {
                    mVideoView.setVideoURI(Uri.parse(mVideosList.get(position).videoURL));
                    Log.d("REELS (VIDEO URL)", mVideosList.get(position).videoURL);
                }

                mVideoView.setOnPreparedListener(mediaPlayer -> {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    mediaPlayer.setLooping(true);
                    mVideoView.requestFocus();

                    float w = (float) mediaPlayer.getVideoWidth();
                    float h = (float) mediaPlayer.getVideoHeight();
                    float a = w / h;
                    float s = (float) mVideoView.getWidth() / mVideoView.getHeight();
                    float scaleX = 1.0f;
                    float scaleY = 1.0f;

                    if (a > s)
                        scaleY = s / a;
                    else
                        scaleX = a / s;

                    mVideoView.setScaleX(scaleX);
                    mVideoView.setScaleY(scaleY);

                    mediaPlayer.start();
                });
            }).start();
        }
    }
}
