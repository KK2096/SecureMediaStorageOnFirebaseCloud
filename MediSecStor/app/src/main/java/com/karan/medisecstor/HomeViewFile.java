package com.karan.medisecstor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class HomeViewFile extends AppCompatActivity {
    public Button ViewDoc,ViewPpt,ViewPdf,ViewExl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view_file);
        ViewDoc = findViewById(R.id.viewdoc);
        ViewExl = findViewById(R.id.viewexl);
        ViewPdf = findViewById(R.id.viewpdf);
        ViewPpt = findViewById(R.id.viewppt);

        ViewDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Working", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(HomeViewFile.this, HomeViewDoc.class);
                startActivity(intent);
                finish();

            }
        });

        ViewPpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Working", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(HomeViewFile.this, HomeViewPpt.class);
                startActivity(intent);
                finish();

            }
        });
        ViewPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Working", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(HomeViewFile.this, HomeViewPdf.class);
                startActivity(intent);
                finish();

            }
        });

        ViewExl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Working", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(HomeViewFile.this, HomeViewExl.class);
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(HomeViewFile.this, HomeFiles.class);
        startActivity(intent);
        finish();
    }
}
