package com.karan.medisecstor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class HomeSelFile extends AppCompatActivity {
    private Button mSelDoc,mSelPpt,mSelPdf,mSelExl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_sel_file);
        mSelDoc = findViewById(R.id.seldoc);
        mSelExl = findViewById(R.id.selexl);
        mSelPpt = findViewById(R.id.selppt);
        mSelPdf = findViewById(R.id.selpdf);


        mSelDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Working", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(HomeSelFile.this, HomeSelDoc.class);
                startActivity(intent);
                finish();
            }
        });

        mSelPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Working", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(HomeSelFile.this, HomeSelPdf.class);
                startActivity(intent);
                finish();
            }
        });

        mSelPpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Working", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(HomeSelFile.this, HomeSelPpt.class);
                startActivity(intent);
                finish();
            }
        });

        mSelExl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Working", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(HomeSelFile.this, HomeSelExl.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(HomeSelFile.this, HomeFiles.class);
        startActivity(intent);
        finish();
    }

}

