package com.daghlas.myrehab;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView greetings;
    LinearLayout enroll_kid, new_rehab, manage_kids, manage_rehabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //status bar color
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.green));

        //greeting Tag
        greetings = findViewById(R.id.greetingTag);
        //Greeting tag - set according to time of day
        Calendar c = Calendar.getInstance();
        int time = c.get(Calendar.HOUR_OF_DAY);

        if (time < 12) {
            greetings.setText(R.string.good_morning);
        } else if (time < 16) {
            greetings.setText(R.string.good_afternoon);
        } else if (time < 21) {
            greetings.setText(R.string.good_evening);
        } else {
            greetings.setText(R.string.good_night);
        }

        enroll_kid = findViewById(R.id.layout1);
        new_rehab = findViewById(R.id.layout2);
        manage_kids = findViewById(R.id.layout3);
        manage_rehabs = findViewById(R.id.layout4);

        enroll_kid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Enrollment.class);
                startActivity(intent);
                finish();
            }
        });
        new_rehab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Rehabs.class);
                startActivity(intent);
                finish();
            }
        });
        manage_kids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KManagement.class);
                startActivity(intent);
                finish();
            }
        });
        manage_rehabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RManagement.class);
                startActivity(intent);
                finish();
            }
        });

        //action bar title & back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Wesley's REHAB");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    //action bar back button implementation
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //ask user to confirm if they want to exit app when back button is pressed
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //builder.setTitle(R.string.app_name);
        builder.setMessage("Are you sure you want to exit the app?")
                .setCancelable(false)
                .setPositiveButton("No", (dialog, id) -> {
                })
                .setNegativeButton("Yes", (dialog, id) -> {
                    System.exit(0);
                    finish();
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    //inflating options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //what happens when menu item is selected

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.refresh) {
            Toast.makeText(this, "Refreshing..", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Log out of account?")
                    .setCancelable(false)
                    .setPositiveButton("No", (dialog, id) -> {
                    })
                    .setNegativeButton("Yes", (dialog, id) -> {
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        startActivity(intent);
                        finish();
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        return super.onOptionsItemSelected(item);
    }
}