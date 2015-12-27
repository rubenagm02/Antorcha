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
import com.mx.antorcha.Modelos.Medalla;
import com.mx.antorcha.R;

import java.util.ArrayList;

/**
 * Created by Ruben on 20/12/2015.
 */
public class AdaptadorEspacioCard extends RecyclerView.Adapter<AdaptadorEspacioCard.ViewHolder> {
    ArrayList<EspacioDeportivo> espacioDeportivos;
    static Activity activity;

    public AdaptadorEspacioCard (Activity activity, ArrayList<EspacioDeportivo> espacioDeportivos) {
        this.espacioDeportivos = espacioDeportivos;
        this.activity = activity;
    }

    @Override
    public AdaptadorEspacioCard.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_actividades_card_espacio, parent, false);

        AdaptadorEspacioCard.ViewHolder viewHolder = new AdaptadorEspacioCard.ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdaptadorEspacioCard.ViewHolder holder, int position) {
        holder.setEspacioDeportivo(espacioDeportivos.get(position));
    }

    @Override
    public int getItemCount() {
        return espacioDeportivos.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        private EspacioDeportivo espacioDeportivo;

        public ViewHolder(View itemView) {
            super(itemView);
            //Del itemView se jalan los elementos del xml
            ImageView imageViewCompartir = (ImageView) itemView.findViewById(R.id.item_card_espacio_compartir);
            AdaptadorSVG.mostrarImagen(imageViewCompartir, activity, R.raw.icono_mas_opciones);

            TextView textViewInvitar = (TextView) itemView.findViewById(R.id.item_espacio_invitar);
            textViewInvitar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, Compartir.class);
                    activity.startActivity(intent);
                }
            });
        }

        public void setEspacioDeportivo (EspacioDeportivo espacioDeportivo) {
            this.espacioDeportivo = espacioDeportivo;
        }
    }
}
