package com.example.borja.comebolas;

import android.graphics.Canvas;

/**
 * Created by borja on 13/01/2016.
 */
public class GameLoopThread extends Thread {
    private VistaJuego view;
    static final long FPS=60;
    boolean running;

    public GameLoopThread(VistaJuego view){
        this.view=view;
    }
    public void setRunning(boolean run){
        running=true;
    }
    public void terminate() {
        running = false;
    }

    @Override
    public void run() {
        long ticksPS=1000/FPS;
        long startTime=0;
        long sleepTime;
        while (running){
            Canvas c=null;
            try{
                c=view.getHolder().lockCanvas();
                synchronized (view.getHolder()){
                    view.onDraw(c);
                }
            }finally {
                if(c!=null)
                    view.getHolder().unlockCanvasAndPost(c);
            }
            sleepTime=ticksPS-(System.currentTimeMillis()-startTime);
            try{
                if(sleepTime>0)
                    sleep(sleepTime);
                else
                    sleep(10);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
