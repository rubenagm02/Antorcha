package com.mx.antorcha.Dialogos;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

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

        return view;
    }
}
