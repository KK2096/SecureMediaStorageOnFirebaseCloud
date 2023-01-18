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

public class PostAdapterAudio extends FirebaseRecyclerAdapter<PostAudio, PostAdapterAudio.AudPastViewHolder> {

    public PostAdapterAudio(@NonNull FirebaseRecyclerOptions<PostAudio> options) {
        super(options);
    }

    public void downloadAudioFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url){
        DownloadManager downloadManager = (DownloadManager)context
                .getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri =Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadManager.enqueue(request);

    }
    @Override
    protected void onBindViewHolder(@NonNull PostAdapterAudio.AudPastViewHolder holder, int position, @NonNull PostAudio postAudio) {
        holder.mAudName.setText(postAudio.getName());
        holder.mAudBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadAudioFile(holder.mAudName.getContext(), postAudio.getName(), ".mp3", DIRECTORY_DOWNLOADS, postAudio.getUrl());
            }
        });
    }

    @NonNull
    @Override
    public PostAdapterAudio.AudPastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_audio, parent, false);
        return new PostAdapterAudio.AudPastViewHolder(view);
    }

    class AudPastViewHolder extends RecyclerView.ViewHolder {
        TextView mAudName;
        Button mAudBut;

        public AudPastViewHolder(@NonNull View itemView) {
            super(itemView);
            mAudName = itemView.findViewById(R.id.aud_name);
            mAudBut = itemView.findViewById(R.id.aud_btn);
        }
    }
}

