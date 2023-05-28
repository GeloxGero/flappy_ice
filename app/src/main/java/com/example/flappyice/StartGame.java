package com.example.flappyice;

import android.app.Activity;
import android.os.Bundle;

public class StartGame extends Activity {
    GameView gameView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
    }
}
