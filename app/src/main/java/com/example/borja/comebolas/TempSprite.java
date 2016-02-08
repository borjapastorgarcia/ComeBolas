package com.example.borja.comebolas;

/**
 * Created by borja on 05/02/2016.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.List;
public class TempSprite {
    private float x;
    private float y;
    private Bitmap bmp;
    private int life = 15;
    private List<Bitmap> listaBitmap;

    VistaJuego gameView;
     public TempSprite(VistaJuego gameView, List listaBitmap){
         this.gameView=gameView;
         this.listaBitmap=listaBitmap;
     }
    public void onDraw(Canvas canvas) {
        update();
        if(Math.random()*100>10){
            bmp=listaBitmap.get((int)(listaBitmap.size()*Math.random()));
            canvas.drawBitmap(bmp, x, y, null);
        }
    }
    private void update() {
        x=Float.parseFloat(String.valueOf(Math.random() * (gameView.getWidth()-32)));
        y=Float.parseFloat(String.valueOf(Math.random() * (gameView.getHeight()-32)));
    }
}