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

public class HomeImage extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 234;
    private StorageReference mFolder;
    private String num;
    private String email;
    private FirebaseAuth mAuth;
    private String fileName;
    private Uri fileUri;
    LinearLayout upimg, donwimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_image);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        num = user.getPhoneNumber();
        email = user.getPhoneNumber();
        num = user.getPhoneNumber();
        mAuth = FirebaseAuth.getInstance();
        upimg = findViewById(R.id.upload_img);
        donwimg = findViewById(R.id.download_img);
        upimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "UPLOAD IMAGE", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

//                Intent intent = new Intent(HomeImage.this, HomeSelImage.class);
//                startActivity(intent);
//                finish();
                UploadImage();
            }
        });
        mFolder = FirebaseStorage.getInstance().getReference().child(num).child("Images");
        donwimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(HomeImage.this,"Download Image", Toast.LENGTH_SHORT).show();
                Snackbar.make(v, "DOWNLOAD IMAGE", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(HomeImage.this, HomeViewImg.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void UploadImage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
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

                        Toast.makeText(HomeImage.this, "Signed Out", Toast.LENGTH_SHORT).show();


                        mAuth.signOut();
                        MainActivity a = new MainActivity();
                        // a.setTrue();
                        a.savePreferences(false, HomeImage.this);
                        sendV s = new sendV();
                        Intent intent = new Intent(HomeImage.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

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
    public void onBackPressed()
    {
        Intent intent = new Intent(HomeImage.this, Dashboard.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST){
            if(resultCode == RESULT_OK){
                final Uri ImageData = data.getData();
                fileUri = ImageData;
                final File file = new File(fileUri.getPath());
                fileName = file.getName();
                //StorageReference VideoName =     mFolder.child("Photos").child(uri.getLastPathSegment());
                final StorageReference ImageName = mFolder.child(ImageData.getLastPathSegment());
                ImageName.putFile(ImageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        final DatabaseReference ImageStore  = FirebaseDatabase.getInstance().getReference().child(email).child("Uploaded Images");
                        final String name = taskSnapshot.getMetadata().getName();
                        ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("name",name);
                                hashMap.put("url",String.valueOf(uri));
                                String key = ImageStore.push().getKey();
                                ImageStore.child(fileName).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(HomeImage.this,"Image UPLOADED \n SUCCESSFULLY",Toast.LENGTH_LONG).show();
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

