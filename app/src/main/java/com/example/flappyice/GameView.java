package com.example.flappyice;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;


public class GameView extends View {

    Handler handler;
    Runnable runnable;
    final int UPDATE_MILLIS=30;
    boolean dead = false;
    double travelled = 0;
    int tubeCount = 0;
    Bitmap background;
    Bitmap topTube, bottomTube;
    Display display;
    Point point;
    int dWidth, dHeight; //device width and height
    Rect rect;
    Bitmap[] icee;
    int frames = 0, iceframe = 0;
    int velocity = 0, gravity = 3;

    int icex, icey;
    boolean gameStart = false;
    int gap = 300;
    int minTubeOffset, maxTubeOffset;
    int numberOfTubes  = 1;
    int distanceBetweenTubes;
    int tubeX;
    int topTubeY;
    Random random;
    int tubeVelocity = 8;

    public void generateTube(){
        topTube = BitmapFactory.decodeResource(getResources(), R.drawable.top_tube);
        bottomTube = BitmapFactory.decodeResource(getResources(), R.drawable.bot_tube);
        tubeX = dWidth + tubeCount++ * distanceBetweenTubes;
        topTubeY = minTubeOffset + random.nextInt(maxTubeOffset - minTubeOffset + 1);
    }
    public GameView(Context context) {
        super(context);
        handler = new Handler();
        runnable = this::invalidate;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        display = ((Activity)context).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        dWidth = point.x;
        dHeight = point.y;
        rect = new Rect(0, 0, dWidth, dHeight);
        icee = new Bitmap[4];
        icee[0] = BitmapFactory.decodeResource(getResources(), R.drawable.ice1);
        icee[1] = BitmapFactory.decodeResource(getResources(), R.drawable.ice2);
        icee[2] = BitmapFactory.decodeResource(getResources(), R.drawable.ice3);
        icee[3] = BitmapFactory.decodeResource(getResources(), R.drawable.ice4);
        icex = dWidth/2 - icee[iceframe].getWidth()/2;
        icey = dHeight/2 - icee[iceframe].getHeight()/2;
        distanceBetweenTubes = dWidth;
        minTubeOffset = gap/2;
        random = new Random();
        maxTubeOffset = dHeight - minTubeOffset - gap;
        generateTube();



    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(!dead){
            super.onDraw(canvas);

            canvas.drawBitmap(background, null, rect, null);

            canvas.drawBitmap(icee[iceframe], icex, icey, null);

            if(gameStart){
                if(frames == 0){
                    iceframe = 0;
                } else if(frames <= 8){
                    iceframe = 1;
                } else if(frames <= 16){
                    iceframe = 2;
                } else if(frames <= 24){
                    iceframe = 3;
                } else if(frames <= 32){
                    iceframe = 2;
                } else if(frames <= 40){
                    iceframe = 1;
                } else {
                    frames = 0;
                    iceframe = 0;
                }
                frames++;

                if(icey < dHeight - icee[iceframe].getHeight() + 285 || velocity < 0){
                    velocity += gravity;
                    icey += velocity;
                }



                for(int i = 0; i < numberOfTubes; i++){
                    travelled += tubeVelocity/1000.0;
                    tubeX -= tubeVelocity;
                    canvas.drawBitmap(topTube, tubeX, topTubeY - topTube.getHeight(), null);
                    canvas.drawBitmap(bottomTube, tubeX, topTubeY + gap, null);
                    System.out.println(travelled);
                }

                if(travelled >= 0.75){
                    tubeCount++;
                    travelled = 0;
                    generateTube();
                }
            }

            handler.postDelayed(runnable, UPDATE_MILLIS);
        }
        else {

        }


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            velocity += -30;
            gameStart = true;
        }



        return super.onTouchEvent(event);
    }
}
