package com.mx.antorcha.Adaptadores;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mx.antorcha.Activities.Compartir;
import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.Modelos.EspacioDeportivo;
import com.mx.antorcha.Modelos.Evento;
import com.mx.antorcha.R;

import java.util.ArrayList;

/**
 * Created by Ruben on 20/12/2015.
 */
public class AdaptadorEventoCard extends RecyclerView.Adapter<AdaptadorEventoCard.ViewHolder> {
    ArrayList<Evento> eventos;
    static Activity activity;

    public AdaptadorEventoCard (Activity activity, ArrayList<Evento> eventos) {
        this.eventos = eventos;
        this.activity = activity;
    }

    @Override
    public AdaptadorEventoCard.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_actividades_eventos_card, parent, false);

        AdaptadorEventoCard.ViewHolder viewHolder = new AdaptadorEventoCard.ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdaptadorEventoCard.ViewHolder holder, int position) {
        holder.setEvento(eventos.get(position));
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        private Evento evento;

        public ViewHolder(View itemView) {
            super(itemView);
            //Del itemView se jalan los elementos del xml
            ImageView imageViewCompartir = (ImageView) itemView.findViewById(R.id.item_card_evento_compartir);
            AdaptadorSVG.mostrarImagen(imageViewCompartir, activity, R.raw.icono_mas_opciones);

            TextView textViewInvitar = (TextView) itemView.findViewById(R.id.item_evento_invitar);
            textViewInvitar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, Compartir.class);
                    activity.startActivity(intent);
                }
            });
        }

        public void setEvento (Evento evento) {
            this.evento = evento;
        }
    }
}
