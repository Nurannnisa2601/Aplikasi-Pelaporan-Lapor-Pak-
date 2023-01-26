package com.example.laporpakk;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomActivity extends AppCompatActivity {

    ImageView imageView1, imageView2, imageView3;
    TextView namauser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);

        setLayout();
        //
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //
        bottomNavigationView.setSelectedItemId(R.id.home);

        //
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext()
                        ,Dashboard.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext()
                                ,About.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });



    }

    private void setLayout() {

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);


        imageView1.setOnClickListener(v -> {
            Intent intent = new Intent(BottomActivity.this, LaporanActivity.class);
            startActivity(intent);

        });

        imageView2.setOnClickListener(v -> {
            Intent intent = new Intent(BottomActivity.this, LaporanActivity.class);
            startActivity(intent);
        });

        imageView3.setOnClickListener(v -> {
            Intent intent = new Intent(BottomActivity.this, LaporanActivity.class);
            startActivity(intent);
        });
    }
}