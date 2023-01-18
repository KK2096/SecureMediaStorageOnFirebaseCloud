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

public class HomeViewPpt extends AppCompatActivity {
    private String num;
    private String email;
    private FirebaseAuth mAuth;
    private PostAdapterPpt adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view_ppt);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        num = user.getPhoneNumber();
        email = user.getPhoneNumber();
        num = user.getPhoneNumber();
        mAuth = FirebaseAuth.getInstance();
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<PostPpt> options =
                new FirebaseRecyclerOptions.Builder<PostPpt>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(num).child("Uploaded Ppt"), PostPpt.class)
                        .build();
        adapter = new PostAdapterPpt(options);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(HomeViewPpt.this, HomeViewFile.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}
