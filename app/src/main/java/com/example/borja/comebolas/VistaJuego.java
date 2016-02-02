package com.example.borja.comebolas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 * Created by borja on 24/01/2016.
 */
public class VistaJuego extends SurfaceView {
    private Bitmap bmp;
    private GameLoopThread gameLoopThread;
    private SurfaceHolder holder;
    private float width=this.getWidth();
    private float heigth=this.getHeight();
    private Bola bola;
    public VistaJuego (Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
        });

        width=this.getWidth();
        heigth=this.getHeight();
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.rsz_pokeball);
        bola=new Bola(this,bmp);
        Log.e("Se crea la bola","--ENTRA" +width+","+heigth);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.forest1), 0, 0, null);
        for(int i=0;i<canvas.getHeight();i+=32){//dibujar paredes laterales
            Paint paint = new Paint();
            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.brick), 0, i, paint);
            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.brick), canvas.getWidth()-64,i , paint);
        }
        for(int i=32;i<canvas.getWidth()-32;i+=32){//dibujar paredes sup e inf
            Paint paint = new Paint();
            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.brick), i, 0, paint);
            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.brick), i,canvas.getHeight()-64 , paint);
        }
        bola.onDraw(canvas);
    }
    @Override
    public boolean onTouchEvent(MotionEvent e) {
      //  Log.e("ENtra a onTouchEvent","-----ENTRA"+e.getX()+","+e.getY());
        bola.onTouch(e.getX(), e.getY());
        return false;
    }
    public  void vibrate(Context context){
        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 300 milliseconds
        v.vibrate(300);
    }

    public GameLoopThread getGameLoopThread() {
        return gameLoopThread;
    }
}
