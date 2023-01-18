package com.karan.medisecstor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class HomeSelExl extends AppCompatActivity {

    public Button mSELEXL;
    private static final int PICK_EXL = 234;
    private StorageReference mFolderRef;
    private String number;
    private String email_id;
    private FirebaseAuth mAuth;
    private String filenames;
    private Uri fileuris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_sel_exl);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        number = user.getPhoneNumber();
        email_id = user.getPhoneNumber();
        number = user.getPhoneNumber();
        mAuth = FirebaseAuth.getInstance();
        mSELEXL = findViewById(R.id.selEXL);
        mFolderRef = FirebaseStorage.getInstance().getReference().child(number).child("EXL");
        mSELEXL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Working", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                UploadEXL();
            }
        });
    }

    public void UploadEXL(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,PICK_EXL);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_EXL) {
            if(resultCode == RESULT_OK){
                final Uri ExlData = data.getData();
                fileuris = ExlData;
                File file = new File(fileuris.getPath());
                filenames = file.getName();
                final StorageReference ExlName = mFolderRef.child(ExlData.getLastPathSegment());
                ExlName.putFile(ExlData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        final DatabaseReference FileStore = FirebaseDatabase.getInstance().getReference().child(email_id).child("Uploaded EXL");
                        final String name = taskSnapshot.getMetadata().getName();
                        ExlName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("name",name);
                                hashMap.put("url",String.valueOf(uri));
                                String key = FileStore.push().getKey();
                                FileStore.child(filenames).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(HomeSelExl.this,"EXL UPLOADED \n SUCCESSFULLY",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(HomeSelExl.this, HomeSelFile.class);
        startActivity(intent);
        finish();
    }
}
