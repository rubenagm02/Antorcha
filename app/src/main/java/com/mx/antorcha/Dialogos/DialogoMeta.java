package com.mx.antorcha.Dialogos;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.mx.antorcha.Modelos.Meta;
import com.mx.antorcha.Modelos.MetaProgreso;
import com.mx.antorcha.R;

import org.w3c.dom.Text;

/**
 * Created by Ruben on 21/12/2015.
 */
public class DialogoMeta extends DialogFragment {

    private int idMeta;
    private Activity activity;
    private ListView listView;

    public DialogoMeta () {

    }

    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogo_actualizar_eliminar_meta, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        //On click de los textos
        TextView textViewActualizarProgreso = (TextView) view.findViewById(R.id.dialogo_meta_actualizar_progreso);
        textViewActualizarProgreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoInsertarProgreso dialogoInsertarProgreso = new DialogoInsertarProgreso();
                dialogoInsertarProgreso.setIdMeta(idMeta);
                dialogoInsertarProgreso.setActivity(activity);
                dialogoInsertarProgreso.setListView(listView);
                dialogoInsertarProgreso.setFragmentManager(fragmentManager);
                dialogoInsertarProgreso.show(fragmentManager, "dialogo_insertar_progreso");

                getDialog().dismiss();
            }
        });
        return view;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setIdMeta(int idMeta) {
        this.idMeta = idMeta;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }


    public void setListView(ListView listView) {
        this.listView = listView;
    }
}
