package com.example.onroadremotevehicleassistantapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class AdminSplashScreeen extends AppCompatActivity {
    private static int SPLASH_SCREEN=5000;

    Animation topanim,bottomanim;
    ImageView image;
    TextView logo,slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_splash_screeen);
        topanim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image = findViewById(R.id.appimage1);
        logo = findViewById(R.id.appname1);
        slogan=findViewById(R.id.tagline1);

        image.setAnimation(topanim);
        logo.setAnimation(bottomanim);
        slogan.setAnimation(bottomanim);

        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                Intent intent = new Intent(AdminSplashScreeen.this,AdminLogin.class);
                Pair[] pairs = new Pair[2];
                pairs[0]= new Pair<View,String>(image,"logo_image1");
                pairs[1] = new Pair<View,String>(logo,"logo_text1");

                ActivityOptions option = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    option = ActivityOptions.makeSceneTransitionAnimation(AdminSplashScreeen.this,pairs);
                }
                startActivity(intent,option.toBundle());
            }
        },SPLASH_SCREEN);
    }
}