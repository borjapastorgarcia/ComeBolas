package com.example.borja.comebolas;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Switch;

public class Preferencias extends AppCompatActivity{

    public boolean vibracionTrue;
    public boolean sonidoTrue;
    Switch swVibracion, swSonido;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        swVibracion=(Switch)findViewById(R.id.switch1);
        swSonido=(Switch)findViewById(R.id.switch2);
        prefs=this.getApplicationContext().getSharedPreferences("Mis preferencias", Context.MODE_PRIVATE);
        if(prefs.getBoolean("Vibracion",true))
            swVibracion.setSelected(true);
        if(prefs.getBoolean("Sonido",true))
            swSonido.setSelected(true);
        final SharedPreferences.Editor editor=prefs.edit();
        swVibracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swVibracion.isSelected())
                    editor.putBoolean("Vibracion",true);
                else
                    editor.putBoolean("Vibracion",false);
            }
        });
        swSonido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swSonido.isSelected())
                    editor.putBoolean("Sonido",true);
                else
                    editor.putBoolean("Sonido",false);
            }
        });



    }

}
