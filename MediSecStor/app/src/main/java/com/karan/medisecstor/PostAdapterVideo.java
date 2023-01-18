package com.karan.medisecstor;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class PostAdapterVideo extends FirebaseRecyclerAdapter<PostVideo, PostAdapterVideo.VidPastViewHolder> {

    public PostAdapterVideo(@NonNull FirebaseRecyclerOptions<PostVideo> options) {
        super(options);
    }

    public void downloadVideoFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url){
        DownloadManager downloadManager = (DownloadManager)context
                .getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri =Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadManager.enqueue(request);

    }
    @Override
    protected void onBindViewHolder(@NonNull PostAdapterVideo.VidPastViewHolder holder, int position, @NonNull PostVideo postVideo) {
        holder.mVidName.setText(postVideo.getName());
        holder.mDownVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadVideoFile(holder.mVidName.getContext(), postVideo.getName(), ".mp4", DIRECTORY_DOWNLOADS, postVideo.getUrl());
            }
        });
    }

    @NonNull
    @Override
    public PostAdapterVideo.VidPastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_vid, parent, false);
        return new VidPastViewHolder(view);
    }

    class VidPastViewHolder extends RecyclerView.ViewHolder{
        TextView mVidName;
        Button mDownVid;
        public VidPastViewHolder(@NonNull View itemView) {
            super(itemView);
            mVidName = itemView.findViewById(R.id.vid_name);
            mDownVid = itemView.findViewById(R.id.vid_down);
        }
    }
}
