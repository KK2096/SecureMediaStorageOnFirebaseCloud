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

public class PostAdapterDoc extends FirebaseRecyclerAdapter<PostDoc, PostAdapterDoc.PastViewHolder> {


    public PostAdapterDoc(@NonNull FirebaseRecyclerOptions<PostDoc> options) {
        super(options);
    }

    public void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url){
        DownloadManager downloadManager =(DownloadManager)context
                .getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri =Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadManager.enqueue(request);
    }

    @Override
    protected void onBindViewHolder(@NonNull PostAdapterDoc.PastViewHolder holder, int position, @NonNull PostDoc post) {
        holder.mName.setText(post.getName());
        holder.mDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFile(holder.mName.getContext(), post.getName(), ".docx", DIRECTORY_DOWNLOADS, post.getUrl());
            }
        });
    }

    @NonNull
    @Override
    public PostAdapterDoc.PastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_doc, parent, false);
        return new PostAdapterDoc.PastViewHolder(view);
    }

    class PastViewHolder extends RecyclerView.ViewHolder
    {
        TextView mName;
        Button mDownload;
        public PastViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.name);
            mDownload = itemView.findViewById(R.id.down);
        }
    }
}