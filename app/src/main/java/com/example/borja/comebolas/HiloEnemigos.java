package com.example.borja.comebolas;

import android.graphics.Canvas;

/**
 * Created by borja on 05/02/2016.
 */
public class HiloEnemigos extends Thread {
    static final long FPS=60;
    boolean running;
    public void terminate() {
        running = false;
    }

    @Override
    public void run() {
        long ticksPS=1000/FPS;
        long startTime=0;
        long sleepTime;
        while (running){
            try{

            }finally {

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
