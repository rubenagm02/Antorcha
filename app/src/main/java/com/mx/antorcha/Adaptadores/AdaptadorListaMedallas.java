package com.mx.antorcha.Adaptadores;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mx.antorcha.Modelos.Medalla;
import com.mx.antorcha.R;

import java.util.ArrayList;

/**
 * Created by Ruben on 18/12/2015.
 */
public class AdaptadorListaMedallas extends RecyclerView.Adapter<AdaptadorListaMedallas.ViewHolder> {

    private ArrayList<Medalla> medallas;
    static TextView textViewNombre;
    static TextView textViewTipo;
    static TextView textViewDescripcion;
    static ImageView imageViewMedallaPrincipal;

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

    public void setTextViewNombre(TextView textViewNombre) {
        this.textViewNombre = textViewNombre;
    }

    public void setTextViewTipo(TextView textViewTipo) {
        this.textViewTipo = textViewTipo;
    }

    public void setTextViewDescripcion(TextView textViewDescripcion) {
        this.textViewDescripcion = textViewDescripcion;
    }

    public void setImageViewMedallaPrincipal(ImageView imageViewMedallaPrincipal) {
        this.imageViewMedallaPrincipal = imageViewMedallaPrincipal;
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        private Medalla medalla;
        private ImageView imageViewMedalla;

        public ViewHolder(View itemView) {
            super(itemView);
            //Del itemView se jalan los elementos del xml
            imageViewMedalla = (ImageView) itemView.findViewById(R.id.item_medallas_medalla);

            //on clic de la medalla
            imageViewMedalla.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (textViewNombre != null) {
                        textViewNombre.setText(medalla.getNombre());
                        textViewDescripcion.setText(medalla.getComoSeLogra());
                    }
                }
            });
        }

        public void setMedalla (Medalla medalla) {
            this.medalla = medalla;
        }
    }
}
