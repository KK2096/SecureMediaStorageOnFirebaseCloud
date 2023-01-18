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

public class HomeViewVid extends AppCompatActivity {

    private RecyclerView recyclerView_Vid;
    private String num;
    private String email;
    private FirebaseAuth mAuth;
    private PostAdapterVideo Videoadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view_vid);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        num = user.getPhoneNumber();
        email = user.getPhoneNumber();
        num = user.getPhoneNumber();
        mAuth = FirebaseAuth.getInstance();
        recyclerView_Vid = findViewById(R.id.recycler_vid);
        recyclerView_Vid.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<PostVideo> options =
                new FirebaseRecyclerOptions.Builder<PostVideo>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(num).child("Uploaded Videos"), PostVideo.class)
                        .build();
        Videoadapter = new PostAdapterVideo(options);
        recyclerView_Vid.setAdapter(Videoadapter);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(HomeViewVid.this, HomeVideo.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart(){
        super.onStart();
        Videoadapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        Videoadapter.stopListening();
    }
}
