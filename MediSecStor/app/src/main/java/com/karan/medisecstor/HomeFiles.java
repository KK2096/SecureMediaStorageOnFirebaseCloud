package com.karan.medisecstor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeFiles extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String email;
    private String num;
    LinearLayout upfile, downfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_files);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        num = user.getPhoneNumber();
        email = user.getPhoneNumber();
        num = user.getPhoneNumber();
        mAuth = FirebaseAuth.getInstance();
        upfile = findViewById(R.id.upload_file);
        downfile = findViewById(R.id.download_file);
        upfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Upload File", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(HomeFiles.this, HomeSelFile.class);
                startActivity(intent);
                finish();

            }
        });
        downfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Download File", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(HomeFiles.this, HomeViewFile.class);
                startActivity(intent);
                finish();

            }
        });
    }

    ///// Menu Item Creation
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
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

                        Toast.makeText(HomeFiles.this, "Signed Out", Toast.LENGTH_SHORT).show();


                        mAuth.signOut();
                        MainActivity a = new MainActivity();
                        // a.setTrue();
                        a.savePreferences(false, HomeFiles.this);
                        sendV s = new sendV();
                        Intent intent = new Intent(HomeFiles.this, MainActivity.class);
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
        Intent intent = new Intent(HomeFiles.this, Dashboard.class);
        startActivity(intent);
        finish();
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

}
