package com.mx.antorcha.Adaptadores;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mx.antorcha.Modelos.Medalla;
import com.mx.antorcha.R;

import java.util.ArrayList;

/**
 * Created by Ruben on 18/12/2015.
 */
public class AdaptadorListaMedallas extends RecyclerView.Adapter<AdaptadorListaMedallas.ViewHolder> {

    ArrayList<Medalla> medallas;

    public AdaptadorListaMedallas (Activity activity, ArrayList<Medalla> medallas) {
        this.medallas = medallas;
    }

    @Override
    public AdaptadorListaMedallas.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_medalla, parent, false);

        AdaptadorListaMedallas.ViewHolder viewHolder = new AdaptadorListaMedallas.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdaptadorListaMedallas.ViewHolder holder, int position) {
        holder.setMedalla(medallas.get(position));
    }

    @Override
    public int getItemCount() {
        return medallas.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        private Medalla medalla;

        public ViewHolder(View itemView) {
            super(itemView);
            //Del itemView se jalan los elementos del xml
        }

        public void setMedalla (Medalla medalla) {
            this.medalla = medalla;
        }
    }
}
