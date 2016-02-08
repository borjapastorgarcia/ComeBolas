package com.example.borja.comebolas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Vibrator;
import android.util.Log;

/**
 * Created by borja on 24/01/2016.
 */
public class Bola {
    static float x;
    static float y;
    private int mColumnWidth=1;
    private int mColumnHeight=1;
    static float canvasHeigth;
    static float canvasWidth;
    private float newX;
    private float newY;
    private float factorX;
    private float factorY;
    private float score;
    private Canvas canvas;
    private boolean gameOver=false;
    boolean posicionInicial=true;
    boolean primerToque=true;
    Bitmap bmp;
    VistaJuego gameView;
    private int width, height;
    public Bola(VistaJuego gameView, Bitmap bmp){
        this.gameView=gameView;
        Log.e("Se crea la bola","-----ENTRA"+x+","+y);
        this.bmp=bmp;
        this.width=bmp.getWidth()/mColumnWidth;
        this.height=bmp.getHeight()/mColumnHeight;
    }
    public void update(){
        x+=factorX;
        y+=factorY;
        primerToque=false;
        score+=0.5;
     //   Log.e("mostrar",""+x+"----"+y);
        comprobarColisionConParedes(x, y);
        comprobarColisionConObjeto(x, y);
    }
    public void comprobarColisionConObjeto(float x, float y){

    }
    public void comprobarColisionConParedes(float x,float y){
        if((x<64||x>=canvasWidth-(64+bmp.getWidth()))||(y<64||(y>=canvasHeigth-(64+bmp.getHeight())))){

            gameOver=true;
        }
    }
    public void onTouch(float coordX, float coordY){
        posicionInicial=false;
        newX=coordX;
        newY=coordY;
        factorX=(newX-x)/5;
        factorY=(newY-y)/5;
      //  primerToque=true;
    }
    public void onDraw(Canvas canvas){
       // Log.e("ONDRAW","ONDRAW de bola");
        this.canvas=canvas;
        if(gameOver==false){
            if(posicionInicial) {
                x= canvas.getWidth()/2-bmp.getWidth()/2;
                y=canvas.getHeight()/2-bmp.getHeight()/2;
                Log.e("Posicion inicial",""+x+"----"+y);
                canvasHeigth=canvas.getHeight();
                canvasWidth=canvas.getWidth();
            }
            update();
            canvas.drawBitmap(bmp,x,y, null);
        }else{//se acabo el juego
            //update();
            Paint textpaint = new Paint();
            textpaint.setColor(Color.WHITE);
            textpaint.setStyle(Paint.Style.FILL);
            textpaint.setTextSize(64);
            canvas.drawText("Se ha golpeado la pared", canvasWidth/2, 64, textpaint);
            canvas.drawBitmap(bmp,x,y, null);
            gameView.vibrate(gameView.getContext(),300);
            gameView.getGameLoopThread().terminate();
        }

    }


    public float getScore() {
        return score;
    }
}
