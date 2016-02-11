package com.example.borja.comebolas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by borja on 29/11/2015.
 */
public class verPuntuacion extends DialogFragment{
    String nuevoNumero;
   static verPuntuacion fragment;
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        final LayoutInflater inflater = getActivity().getLayoutInflater();
       final View view = inflater.inflate(R.layout.finjuego, null);
        builder.setView(view)
                .setPositiveButton("Volver a jugar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        verPuntuacion.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    public static verPuntuacion newInstance(String titulo) {
        Log.i("newInstance", "Llega a newINstance");
         fragment = new verPuntuacion();
        Bundle args = new Bundle();
        args.putString("title", titulo);
        fragment.setArguments(args);
        return fragment;
    }
}
