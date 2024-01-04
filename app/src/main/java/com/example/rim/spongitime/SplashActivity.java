package com.example.rim.spongitime;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mohaiminur.bubblefish.R;

public class SplashActivity extends AppCompatActivity {
    public static  MediaPlayer mediaPlayer;
    ImageView play;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread thread=new Thread()
        {
            @Override
            public void run() {
                try
                {
                    //duracion que tarda para abrir la pantalla del juego
                    sleep(4000);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                    Intent mainIntent=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
    //MUSICA
    public void Musica(View view) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(SplashActivity.this, R.raw.spongisong);
            mediaPlayer.start();

        } else {
            Toast.makeText(getApplicationContext(), "Try again ", Toast.LENGTH_LONG).show();
        }
        if (mediaPlayer != null) {
            Toast.makeText(getApplicationContext(), "Song Started", Toast.LENGTH_LONG).show();
        }
    }//fin el metodo Musica
}
