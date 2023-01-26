package com.example.laporpakk;

import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIMER = 5000;

    // variable
    ImageView backgroundImage;

    //Animasi
    Animation sideAnim, bottomAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Hooks
        backgroundImage = findViewById(R.id.background_image);

        //Animasi
        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        sideAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        //set Animasi on elements
        backgroundImage.setAnimation(sideAnim);
        backgroundImage.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_TIMER);


    }
}