package com.karan.medisecstor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class HomeViewAudio extends AppCompatActivity {
    private RecyclerView recyclerView_Aud;
    private String num;
    private String email;
    private FirebaseAuth mAuth;
    private PostAdapterAudio adapterAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view_audio);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        num = user.getPhoneNumber();
        email = user.getPhoneNumber();
        num = user.getPhoneNumber();
        mAuth = FirebaseAuth.getInstance();

        recyclerView_Aud = findViewById(R.id.recycler_aud);
        recyclerView_Aud.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<PostAudio> options =
                new FirebaseRecyclerOptions.Builder<PostAudio>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(num).child("Uploaded Audios"), PostAudio.class)
                        .build();
        adapterAudio = new PostAdapterAudio(options);
        recyclerView_Aud.setAdapter(adapterAudio);

    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(HomeViewAudio.this, HomeAudio.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onStart(){
        super.onStart();
        adapterAudio.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapterAudio.stopListening();
    }
}
