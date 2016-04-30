package com.mx.antorcha.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mx.antorcha.Activities.DetalleEvento;
import com.mx.antorcha.Modelos.Evento;
import com.mx.antorcha.R;

import java.util.ArrayList;

/**
 * Created by root on 27/04/16.
 */
public class AdaptadorListaEventos extends ArrayAdapter<Evento> {

    private Context context;

    public AdaptadorListaEventos(Context context, int resource, ArrayList<Evento> eventos) {
        super(context, resource, eventos);

        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Evento evento = getItem(position);

        if (evento.getTipo() == 0) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.item_lista_eventos, null);

            //Se cargan los datos en el item
            ((TextView) convertView.findViewById(R.id.item_lista_eventos_nombre_evento)).setText(evento.getNombre());
            ((TextView) convertView.findViewById(R.id.item_lista_evento_fecha)).setText(evento.getFechaInicio());

            //Se coloca el click
            convertView.findViewById(R.id.item_lista_eventos_click).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetalleEvento.class);
                    context.startActivity(intent);
                }
            });
        } else {
            //Si es un item del mes
            LayoutInflater vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.lista_mes_eventos, null);

        }

        return convertView;
    }
}
