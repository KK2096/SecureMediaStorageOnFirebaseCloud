package com.karan.medisecstor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {
    GridLayout mygrid;
    Animation frombottom;
    LinearLayout lin_img,lin_vdv,lin_aud,lin_fil,lin_sec_msg;
    LinearLayout lin_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mygrid = findViewById(R.id.mygrid);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.formbottom);
        mygrid.startAnimation(frombottom);
        lin_img = (LinearLayout) findViewById(R.id.li_img);
        lin_vdv = (LinearLayout) findViewById(R.id.li_vid);
        lin_aud = (LinearLayout) findViewById(R.id.li_mus);
        lin_fil = (LinearLayout) findViewById(R.id.li_file);
        lin_sec_msg = (LinearLayout) findViewById(R.id.li_msg);
        lin_profile = (LinearLayout) findViewById(R.id.li_prof);

        lin_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(getApplicationContext(), "Img", Toast.LENGTH_SHORT).show();*/
                Intent intent = new Intent(Dashboard.this, HomeImage.class);
                startActivity(intent);
                finish();
            }
        });

        lin_vdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Vid", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Dashboard.this, HomeVideo.class);
                startActivity(intent);
                finish();
            }
        });

        lin_aud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Aud", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Dashboard.this, HomeAudio.class);
                startActivity(intent);
                finish();
            }
        });

        lin_fil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "File", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Dashboard.this, HomeFiles.class);
                startActivity(intent);
                finish();
            }
        });

        lin_sec_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.ayush.steganography");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                } else {
                    Toast.makeText(Dashboard.this, "There is no package available in android", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage("Do You Really Want To Exit")
                // .setIcon(R.drawable.alert)
                .setNegativeButton("Cancel", (dialogInterface, i) -> {

                })
                .setPositiveButton("OK", (dialog, id) -> finish());
        android.app.AlertDialog alert = builder.create();
        alert.show();
    }
}