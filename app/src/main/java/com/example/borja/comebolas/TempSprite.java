package com.example.borja.comebolas;

/**
 * Created by borja on 05/02/2016.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.util.List;
public class TempSprite {
    public static int x;
    public static int y;
    public static Bitmap bmp;
    public static boolean dibujado=false;
    long tiempoSprite=0;
    long tiempoMaxSprite=10;
    static List listaBitmap;
    static int posicion;

    VistaJuego gameView;
     public TempSprite(VistaJuego gameView, List listaBitmap){
         this.gameView=gameView;
         this.listaBitmap=listaBitmap;
     }
   /* public TempSprite(VistaJuego gameView, Bitmap bmp){
        this.gameView=gameView;
        this.bmp=bmp;
    }*/
    public void onDraw(Canvas c){
       // Log.i("entraONDraw","xxxx"+x+",y-->"+y);
        float aleatorio=(float) Math.random()*100;
    //    Log.i("ALEATORIO","num-->"+aleatorio);
        if(aleatorio>90){//cambia el bitmap
            update();//actualiza la posicion del objeto
            posicion=(int)(Math.random()*listaBitmap.size());
            bmp=(Bitmap)listaBitmap.get(posicion);
        }
        c.drawBitmap(bmp, x, y, null);
    }
    private void update() {
        x=(int)(Math.random() * (gameView.getWidth()-32));
        y=(int)(Math.random() * (gameView.getHeight()-32));

    }
    public Rect getBounds(){
        return new Rect(this.x,this.y,this.x+bmp.getWidth(),this.y+bmp.getHeight());
    }

    public boolean checkCollision(Rect bola, Rect enemigo){
        return Rect.intersects(bola,enemigo);
    }
}