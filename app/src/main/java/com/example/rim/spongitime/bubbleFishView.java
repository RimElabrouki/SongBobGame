package com.example.rim.spongitime;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.mohaiminur.bubblefish.R;

public class bubbleFishView extends View {

    private Bitmap fish[]=new Bitmap[2];
    static int screenWidth, screenHeight;

    private int fishX=10;
    Bitmap bom, medusarosa,zumo;

    private int fishY;
    private int fishSpeed;
    MediaPlayer micancion;

    private int canvasWidth,canvasHeight;

    private int yellowX,yellowY,yellowSpeed=13;
    private Paint yellowPaint = new Paint();

    private int greenX,greenY,greenSpeed=15;
    private Paint greenPaint = new Paint();

    private int redX,redY,redSpeed=16;
    private Paint redPaint = new Paint();

    private int score,lifecountFish;

    private boolean touch=false;

    private Bitmap backgroundImage;
    private Paint scorePaint=new Paint();
    private Bitmap life[]=new Bitmap[2];

    public bubbleFishView(Context context) {
        super(context);

        fish[0]= BitmapFactory.decodeResource(getResources(), R.drawable.spon);
        fish[1]= BitmapFactory.decodeResource(getResources(), R.drawable.spon);

        backgroundImage=BitmapFactory.decodeResource(getResources(), R.drawable.verde);
        bom = BitmapFactory.decodeResource(context.getResources(), R.drawable.bom);
        medusarosa = BitmapFactory.decodeResource(context.getResources(), R.drawable.medusarosa);
        zumo = BitmapFactory.decodeResource(context.getResources(), R.drawable.rose);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);
        //cambiaremos el tamaño de los bubles con el setTextSize
        yellowPaint.setTextSize(1);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);
        greenPaint.setTextSize(1);


        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);
        redPaint.setTextSize(1);

// tamaño del texto
        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(13);//  70 nichilm age
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);
        life[0]=BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1]=BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);
//550
        fishY=660;
        score=0;
        lifecountFish=3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasWidth=canvas.getWidth();
        canvasHeight=canvas.getHeight();

        canvas.drawBitmap(backgroundImage,0,0,null);

        int minFishY=fish[0].getHeight();
        //3
        int maxFishY=canvasHeight-fish[0].getHeight()*1;
        fishY=fishY+fishSpeed;

        if(fishY<minFishY)
        {
            fishY=minFishY;
        }
        if(fishY> maxFishY)
        {
            fishY=maxFishY;
        }
        //2
        fishSpeed=fishSpeed+8;

        if(touch){
            canvas.drawBitmap(fish[1],fishX,fishY,null);
            touch=false;
        }
        else{
            canvas.drawBitmap(fish[0],fishX,fishY,null);
        }

        yellowX=yellowX-yellowSpeed;

        //update score
        if(hitBallChecker(yellowX,yellowY)){
            score=score+10;
            //100
            yellowX=-100;
        }

        if(yellowX<0){
            yellowX=canvasWidth+18;
            yellowY=(int) Math.floor(Math.random()*(maxFishY-minFishY))+minFishY;
        }
        canvas.drawBitmap(zumo,yellowX,greenY,null);
        //radious can be 25 20
        // canvas.drawCircle(yellowX,yellowY,6,yellowPaint);==> eso si queremos dibujar un circulo
        greenX=greenX-greenSpeed;
        //update score
        if(hitBallChecker(greenX,greenY)){
            score=score+20;
            greenX=-100;
        }
//21
        if(greenX<0){
            greenX=canvasWidth+18;
            greenY=(int) Math.floor(Math.random()*(maxFishY-minFishY))+minFishY;
        }
        //radious can be 25
       // canvas.drawCircle(greenX,greenY,7,greenPaint);
         canvas.drawBitmap(medusarosa,greenX,greenY,null);
        redX=redX-redSpeed;

        //update score
        if(hitBallChecker(redX,redY)){
            redX=-100;
            lifecountFish--;
            if(lifecountFish==0){
                Toast.makeText(getContext(),"Game Over",Toast.LENGTH_SHORT).show();
                Intent gameOverIntent =new Intent(getContext(),GameOverActivity.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                gameOverIntent.putExtra("score",score);
                getContext().startActivity(gameOverIntent);
            }
        }

        if(redX<0){
            //21
            redX=canvasWidth+18;
            redY=(int) Math.floor(Math.random()*(maxFishY-minFishY))+minFishY;
        }
        //radious can be 25 redPaint
        canvas.drawBitmap(bom,redX,redY,null);

      //  canvas.drawCircle(redX,redY,5,redPaint);

// cambiar el score la posicion de X y Y
        canvas.drawText("Score: "+score,20,50,scorePaint);
// LAS VIDAS
        for(int i=0;i<3;i++){
//280 was 580
            //CAMBIAR LA POSICION DE LAS VIDAS DEL PESCADO
            int x=(int) (90+ life[0].getWidth()*1 * i);
            int y=30;

            if(i<lifecountFish){
                canvas.drawBitmap(life[0],x,y,null);
            }
            else{
                canvas.drawBitmap(life[1],x,y,null);
            }
        }
    }

    public boolean hitBallChecker(int x,int y){
        if(fishX<x && x < (fishX+fish[0].getWidth()) && fishY <y && y< (fishY+fish[0].getHeight())){
            return true;
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            touch=true;
            //-25
            fishSpeed=-30;
        }
        return true;
    }

}
