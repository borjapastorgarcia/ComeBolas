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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    TempSprite tempSprite;
    private List<Bitmap> temps = new ArrayList<Bitmap>();
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
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.bulbasaur));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.caterpie));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.charmander));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.fearow));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.golduck));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.growlithe));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.kadabraf));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.meowth));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.nidoking));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.nidoqueen));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.oddish));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.paras));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.pidgey));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.pikachuf));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.poliwhirl));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.rattata));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.sandslash));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.squirtle));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.venomoth));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.vulpix));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.weedle));
        temps.add(BitmapFactory.decodeResource(getResources(), R.drawable.wigglytuff));
        tempSprite=new TempSprite(this,temps);
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
        tempSprite.onDraw(canvas);
        bola.onDraw(canvas);
    }
    @Override
    public boolean onTouchEvent(MotionEvent e) {
      //  Log.e("ENtra a onTouchEvent","-----ENTRA"+e.getX()+","+e.getY());
        bola.onTouch(e.getX(), e.getY());
        return false;
    }
    public  void vibrate(Context context, int miliseconds){
        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 300 milliseconds
        v.vibrate(miliseconds);
    }

    public GameLoopThread getGameLoopThread() {
        return gameLoopThread;
    }
}
