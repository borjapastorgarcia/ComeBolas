package com.example.borja.comebolas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by borja on 24/01/2016.
 */
public class VistaJuego extends SurfaceView {
    private MediaPlayer musica,mpComida;
    SoundPool soundPool;
    int idMusica,idSonido;
    private Bitmap bmp;
    public int contadorDraws=0;
    private GameLoopThread gameLoopThread;
    private SurfaceHolder holder;
    private Bola bola;
    private static Context mContext;
    private List<Bitmap> temps = new ArrayList<Bitmap>();
    public float score;
    private Bitmap pared;
    public VistaJuego (Context context) {
        super(context);
        mContext=context;
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

        soundPool = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
        idMusica= soundPool.load(mContext,R.raw.musica,1);
        musica=MediaPlayer.create(mContext,R.raw.musica);
        musica.start();
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.rsz_pokeball);
        bola=new Bola(this,bmp);

        //metemos imagenes en array
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

        pared=BitmapFactory.decodeResource(getResources(), R.drawable.brick);
        //Log.e("Se crea la bola","--ENTRA" +width+","+heigth);
    }
    @Override
    protected void onDraw(Canvas canvas) {

        //dibujamos score
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.forest1), 0, 0, null);
        for(int i=0;i<canvas.getHeight();i+=32){//dibujar paredes laterales
            Paint paint = new Paint();
            canvas.drawBitmap(pared, 0, i, paint);
            canvas.drawBitmap(pared, canvas.getWidth()-64,i , paint);
        }
        for(int i=32;i<canvas.getWidth()-32;i+=32){//dibujar paredes sup e inf
            Paint paint = new Paint();
            canvas.drawBitmap(pared, i, 0, paint);
            canvas.drawBitmap(pared, i,canvas.getHeight()-64 , paint);
        }
        bola.onDraw(canvas);
        TempSprite tempSprite=new TempSprite(this,temps);

        if(contadorDraws==0) {
            tempSprite.bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bulbasaur);//el primero para que no de null
            tempSprite.x=(int)(Math.random() * (canvas.getWidth()-32));
            tempSprite.y=(int)(Math.random() * (canvas.getHeight()-32));
            TempSprite.posicion=0;
        }
            tempSprite.onDraw(canvas);
            contadorDraws++;
        if(tempSprite.checkCollision(bola.getBounds(),tempSprite.getBounds())){

            //sonar y vibrar
            idSonido= soundPool.load(mContext,R.raw.arrow,1);
            mpComida=MediaPlayer.create(mContext,R.raw.arrow);
            mpComida.start();
            vibrate(mContext,100);
            score+=500;
            bola.setVspeed(bola.getVspeed()+0.25f);
            tempSprite.onDraw(canvas,true);
        }

        Paint textpaint = new Paint();
        textpaint.setColor(Color.WHITE);
        textpaint.setStyle(Paint.Style.FILL);
        textpaint.setTextSize(32);
        canvas.drawText("SCORE: "+String.valueOf(score), 50, 50, textpaint);

        //velocidad
        Paint velocidad = new Paint();
        velocidad.setColor(Color.WHITE);
        velocidad.setStyle(Paint.Style.FILL);
        velocidad.setTextSize(32);
        canvas.drawText("VELOCIDAD: "+bola.getVspeed(), 400, 50, velocidad);
    }
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        bola.onTouch(e.getX(), e.getY());
        return false;
    }
    public  void vibrate(Context context, int miliseconds){
        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(miliseconds);
    }

    public static Context getmContext() {
        return mContext;
    }
    public GameLoopThread getGameLoopThread() {
        return gameLoopThread;
    }

    public MediaPlayer getMusica() {
        return musica;
    }

    public Bitmap getPared() {
        return pared;
    }
}
