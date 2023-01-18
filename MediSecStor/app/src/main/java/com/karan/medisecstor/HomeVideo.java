package com.karan.medisecstor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;

public class HomeVideo extends AppCompatActivity {

    private static final int PICK_VIDEO_REQUEST = 234;
    private StorageReference mFolder;
    private String num;
    private String email;
    private FirebaseAuth mAuth;
    private String fileName;
    private Uri fileUri;

    LinearLayout upvid, downvid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_video);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        num = user.getPhoneNumber();
        email = user.getPhoneNumber();
        num = user.getPhoneNumber();
        mAuth = FirebaseAuth.getInstance();
        upvid = findViewById(R.id.upload_vid);
        downvid = findViewById(R.id.download_vid);

        upvid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(HomeVideo.this,"UPLOAD VIDEO ",Toast.LENGTH_SHORT).show();
                Snackbar.make(v, "UPLOAD VIDEO", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
//                Intent intent = new Intent(HomeVideo.this, HomeSelVideo.class);
//                startActivity(intent);
//                finish();
                UploadVideo();
            }
        });
        mFolder = FirebaseStorage.getInstance().getReference().child(num).child("Videos");
        downvid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(HomeVideo.this,"DOWNLOAD VIDEO ",Toast.LENGTH_SHORT).show();
                Snackbar.make(v, "DOWNLOAD VIDEO", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(HomeVideo.this, HomeViewVid.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void UploadVideo(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        startActivityForResult(intent,PICK_VIDEO_REQUEST);
    }

    ///// Signout Function.....
    public void signOut() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do You Really Want To Sign Out")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        Toast.makeText(HomeVideo.this, "Signed Out", Toast.LENGTH_SHORT).show();


                        mAuth.signOut();
                        MainActivity a = new MainActivity();
                        // a.setTrue();
                        a.savePreferences(false, HomeVideo.this);
                        sendV s = new sendV();
                        Intent intent = new Intent(HomeVideo.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(HomeVideo.this, Dashboard.class);
        startActivity(intent);
        finish();
    }

    ///// Menu Item Creation
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.signout) {
            signOut();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_VIDEO_REQUEST){
            if(resultCode == RESULT_OK){
                final Uri VideoData = data.getData();
                fileUri = VideoData;
                final File file = new File(fileUri.getPath());
                fileName = file.getName();
                //StorageReference VideoName =     mFolder.child("Photos").child(uri.getLastPathSegment());
                final StorageReference VideoName = mFolder.child(VideoData.getLastPathSegment());
                VideoName.putFile(VideoData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        final DatabaseReference VideoStore  = FirebaseDatabase.getInstance().getReference().child(email).child("Uploaded Videos");
                        final String name = taskSnapshot.getMetadata().getName();
                        VideoName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("name",name);
                                hashMap.put("url",String.valueOf(uri));
                                String key = VideoStore.push().getKey();
                                VideoStore.child(fileName).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(HomeVideo.this,"VIDEO UPLOADED \n SUCCESSFULLY",Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }
    }

}

