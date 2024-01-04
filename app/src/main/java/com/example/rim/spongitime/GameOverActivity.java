package com.example.rim.spongitime;

import static com.example.rim.spongitime.SplashActivity.mediaPlayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohaiminur.bubblefish.R;

public class  GameOverActivity extends AppCompatActivity {

    private Button startGameAgain;
    private TextView DisplayScore;
    private String score;
    MediaPlayer micancion;
    ImageView play;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        // Detiene la reproducción de la canción
        if (SplashActivity.mediaPlayer != null) {
            SplashActivity.mediaPlayer.stop();
            SplashActivity.mediaPlayer.release();
            SplashActivity.mediaPlayer = null;
        }
        score=getIntent().getExtras().get("score").toString();
        startGameAgain=(Button) findViewById(R.id.play_again_btn);
        DisplayScore=(TextView) findViewById(R.id.score_text);

        startGameAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent=new Intent(GameOverActivity.this,MainActivity.class);
                startActivity(mainIntent);
                Musica();
            }
        });
        DisplayScore.setText("Score: "+score);
    }
    //MUSICA
    public void Musica() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(GameOverActivity.this, R.raw.spongisong2);
            mediaPlayer.start();
        } else {
            Toast.makeText(getApplicationContext(), "Try again ", Toast.LENGTH_LONG).show();
        }
        if (mediaPlayer != null) {
            Toast.makeText(getApplicationContext(), "Song Started", Toast.LENGTH_LONG).show();
        }
    }//fin el metodo Musica

    // Parar la Musica del juego
    public void Stop() {
        if (micancion != null) {
            micancion.release();
            micancion = null;
        }else{
            try {
                Toast.makeText(GameOverActivity.this, "Stop", Toast.LENGTH_LONG).show();
            }catch(Exception exception){
                exception.printStackTrace();
            }
        }
    }
}
