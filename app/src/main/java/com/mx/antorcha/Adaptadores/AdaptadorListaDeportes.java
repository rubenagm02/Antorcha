package com.mx.antorcha.Adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.Modelos.Deporte;
import com.mx.antorcha.R;

import java.util.ArrayList;

/**
 *
 */
public class AdaptadorListaDeportes extends ArrayAdapter<Deporte> {

    private Activity activity;

    public AdaptadorListaDeportes(Activity activity, int resource, ArrayList<Deporte> deportes) {
        super(activity, resource, deportes);
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater vi;
        vi = LayoutInflater.from(getContext());
        convertView = vi.inflate(R.layout.item_perfil_deporte, null);

        Deporte deporte = getItem(position);

        ImageView imageViewMasOpciones = (ImageView) convertView.findViewById(R.id.deportes_lista_mas_opciones);
        AdaptadorSVG.mostrarImagen(imageViewMasOpciones, activity, R.raw.icono_compartir);

        TextView textViewPrincipal = (TextView) convertView.findViewById(R.id.item_deporte_texto_principal);
        textViewPrincipal.setText(deporte.getNombre());

        TextView textViewSecundario = (TextView) convertView.findViewById(R.id.item_deporte_texto_secundario);
        textViewSecundario.setText(deporte.getDisciplina() + "");

        return convertView;
    }
}
