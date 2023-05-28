package com.example.flappyice;

import android.graphics.Canvas;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.content.Intent;
import android.view.WindowManager;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity{
    ImageView flappy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        flappy = findViewById(R.id.playbutton);
        flappy.setOnClickListener(this::startGame);
    }


    public void startGame(View view){
        Intent intent = new Intent(this, StartGame.class);
        startActivity(intent);
        finish();
    }





}