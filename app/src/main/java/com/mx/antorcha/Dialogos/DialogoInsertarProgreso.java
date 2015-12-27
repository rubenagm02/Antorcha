package com.mx.antorcha.Dialogos;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.mx.antorcha.Adaptadores.AdaptadorListaMetas;
import com.mx.antorcha.BaseDatos.ConexionBaseDatosInsertar;
import com.mx.antorcha.BaseDatos.ConexionBaseDatosObtener;
import com.mx.antorcha.Modelos.Meta;
import com.mx.antorcha.Modelos.MetaProgreso;
import com.mx.antorcha.R;

/**
 * Created by Ruben on 21/12/2015.
 */
public class DialogoInsertarProgreso extends DialogFragment {

    private int idMeta;
    private Activity activity;
    private ListView listView;
    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogo_insertar_progreso, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        final EditText editTextNuevoProgreso = (EditText) view.findViewById(R.id.dialogo_nuevo_progreso);

        Button button = (Button) view.findViewById(R.id.dialogo_guardar_nuevo_progreso);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MetaProgreso metaProgreso = new MetaProgreso(
                        idMeta,
                        Double.parseDouble(editTextNuevoProgreso.getText().toString()),
                        "2015-11-10");

                ConexionBaseDatosInsertar conexionBaseDatosInsertar = new ConexionBaseDatosInsertar(activity);
                conexionBaseDatosInsertar.insertarMetaProgreso(metaProgreso);

                ConexionBaseDatosObtener conexionBaseDatosObtener = new ConexionBaseDatosObtener(activity);
                AdaptadorListaMetas adaptadorListaMetas = new AdaptadorListaMetas(activity, conexionBaseDatosObtener.obtenerMetas(), fragmentManager );
                listView.setAdapter(adaptadorListaMetas);

                getDialog().dismiss();
            }
        });
        return view;
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

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }
}
