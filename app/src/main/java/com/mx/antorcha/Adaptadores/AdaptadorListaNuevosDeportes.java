package com.mx.antorcha.Adaptadores;

import android.app.Activity;
import android.graphics.Matrix;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.BaseDatos.ConexionBaseDatosObtener;
import com.mx.antorcha.Modelos.Deporte;
import com.mx.antorcha.Modelos.Disciplina;
import com.mx.antorcha.R;

import java.util.ArrayList;

/**
 *
 */
public class AdaptadorListaNuevosDeportes extends ArrayAdapter<Disciplina>{

    private Activity activity;

    public AdaptadorListaNuevosDeportes(Activity activity, ArrayList<Disciplina> disciplinas) {
        super(activity, R.layout.item_seleccionar_disciplina, disciplinas);
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Disciplina disciplina = getItem(position);
        LayoutInflater vi = LayoutInflater.from(getContext());
        convertView = vi.inflate(R.layout.item_seleccionar_disciplina, null);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_seleccionar_disciplina_imagen);
        AdaptadorSVG.mostrarImagen(imageView, activity, R.raw.icono_flecha_izquierda);

        /****** VARIABLES DE PRUEBA *******

        ArrayList<Deporte> deportes = new ArrayList<>();
        deportes.add(new Deporte());
        deportes.add(new Deporte());
        /**********************************/

        ConexionBaseDatosObtener conexionBaseDatosObtener = new ConexionBaseDatosObtener(activity);
        ArrayList<Deporte> deportes = conexionBaseDatosObtener.obtenerDeportes(disciplina.getId());

        LinearLayout linearLayoutSeleccionarDisciplina = (LinearLayout) convertView.findViewById(R.id.seleccionar_disciplina_layout);

        for (Deporte deporte : deportes) {
            View view = vi.inflate(R.layout.item_seleccionar_deporte, null);
            linearLayoutSeleccionarDisciplina.addView(view);

            LinearLayout linearLayout = (LinearLayout) view;
            CheckBox checkBox = (CheckBox) linearLayout.getChildAt(0);
            TextView textView = (TextView) linearLayout.getChildAt(1);
            textView.setText(deporte.getNombre());
        }

        return convertView;
    }


}
