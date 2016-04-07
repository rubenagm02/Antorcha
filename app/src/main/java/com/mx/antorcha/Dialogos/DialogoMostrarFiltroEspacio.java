package com.mx.antorcha.Dialogos;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mx.antorcha.R;

/**
 *
 */
public class DialogoMostrarFiltroEspacio extends DialogFragment {

    public DialogoMostrarFiltroEspacio () {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filtro_busqueda_espacio, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        TextView textViewCancenlar = (TextView) view.findViewById(R.id.dialogo_filtro_espacio_cancelar);
        TextView textViewAceptar = (TextView) view.findViewById(R.id.dialogo_filtro_espacio_aceptar);

        textViewCancenlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        textViewAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        //Se programa la acción del seek bar

        SeekBar seekBarRango = (SeekBar) view.findViewById(R.id.filtro_busqueda_espacio_seekbar);
        seekBarRango.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return view;
    }
}
