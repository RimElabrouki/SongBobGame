package com.example.rim.spongitime;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private bubbleFishView gameView;
    private Handler handler=new Handler();
    //30 es la velocidad del bubbles
    private final static long Interval=90;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView=new bubbleFishView(this);
        setContentView(gameView);

        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run()
                    {
                        gameView.invalidate();
                    }
                });
            }
        },0,Interval);
    }
}
