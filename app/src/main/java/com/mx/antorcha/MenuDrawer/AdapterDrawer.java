package com.mx.antorcha.MenuDrawer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mx.antorcha.Activities.Actividades;
import com.mx.antorcha.Activities.BuscarActividad;
import com.mx.antorcha.Activities.Medallas;
import com.mx.antorcha.Activities.Metas;
import com.mx.antorcha.Activities.Perfil;
import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.Fragment.FragmentPerfilPerfil;
import com.mx.antorcha.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 *
 */
public class AdapterDrawer extends ArrayAdapter<String> {
    private int resource;
    ImageView imageViewBuscarActividad;
    ImageView imageViewActividades;
    ImageView imageViewPerfil;
    ImageView imageViewMedallas;
    ImageView imageViewMetas;
    ImageView imageViewMas;

    LinearLayout linearLayoutBuscarActividad;
    LinearLayout linearLayoutActividades;
    LinearLayout linearLayoutPerfil;
    LinearLayout linearLayoutMedallas;
    LinearLayout linearLayoutMetas;

    Activity activity;
    String actividad;

    public AdapterDrawer(Activity activity, int resource, ArrayList<String> palabra, String actividad) {
        super(activity, resource, palabra);
        this.activity = activity;
        this.resource = resource;
        this.actividad = actividad;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater vi;
        vi = LayoutInflater.from(getContext());
        convertView = vi.inflate(resource, null);

        //Se inicializan las imagenes para poder mostrar el icono en svg
        imageViewBuscarActividad = (ImageView)
                convertView.findViewById(R.id.drawer_icono_buscar_actividad);
        imageViewActividades = (ImageView) convertView.findViewById(R.id.drawer_icono_actividades);
        imageViewPerfil = (ImageView) convertView.findViewById(R.id.drawer_icono_perfil);
        imageViewMedallas = (ImageView) convertView.findViewById(R.id.drawer_icono_medallas);
        imageViewMetas = (ImageView) convertView.findViewById(R.id.drawer_icono_metas);
        imageViewMas = (ImageView) convertView.findViewById(R.id.drawer_mas);

        //se muestran los iconos
        AdaptadorSVG.mostrarImagen(imageViewBuscarActividad, activity, R.raw.icono_buscar_actividad);
        AdaptadorSVG.mostrarImagen(imageViewActividades, activity, R.raw.icono_actividades);
        AdaptadorSVG.mostrarImagen(imageViewPerfil, activity, R.raw.icono_perfil);
        AdaptadorSVG.mostrarImagen(imageViewMedallas, activity, R.raw.icono_medallas);
        AdaptadorSVG.mostrarImagen(imageViewMetas, activity, R.raw.icono_metas);

        //Se cargan los textview para pintarlos
        TextView textViewBuscarActividad = (TextView) convertView.findViewById(R.id.drawer_texto_buscar_actividad);
        TextView textViewActividades = (TextView) convertView.findViewById(R.id.drawer_texto_actividades);
        TextView textViewPerfil = (TextView) convertView.findViewById(R.id.drawer_texto_perfil);
        TextView textViewMedallas = (TextView) convertView.findViewById(R.id.drawer_texto_medallas);
        TextView textViewMetas = (TextView) convertView.findViewById(R.id.drawer_texto_metas);

        //para pintar el icono seleccionado y el textview
        switch (actividad) {
            case "Perfil" :
                imageViewPerfil.setAlpha(1f);
                textViewPerfil.setTextColor(Color.parseColor("#FF9522"));
                AdaptadorSVG.mostrarImagen(imageViewPerfil, activity, R.raw.icono_perfil_seleccionado);
                break;
            case "Metas" :
                imageViewMetas.setAlpha(1f);
                textViewMetas.setTextColor(Color.parseColor("#FF9522"));
                AdaptadorSVG.mostrarImagen(imageViewMetas, activity, R.raw.icono_metas_seleccionado);
                break;
            case "Medallas" :
                imageViewMedallas.setAlpha(1f);
                textViewMedallas.setTextColor(Color.parseColor("#FF9522"));
                AdaptadorSVG.mostrarImagen(imageViewMedallas, activity, R.raw.icono_medallas_seleccionado);
                break;
            case "Actividades" :
                imageViewActividades.setAlpha(1f);
                textViewActividades.setTextColor(Color.parseColor("#FF9522"));
                AdaptadorSVG.mostrarImagen(imageViewActividades, activity, R.raw.icono_actividades_seleccionado);
                break;
            case "BuscarActividad" :
                imageViewBuscarActividad.setAlpha(1f);
                textViewBuscarActividad.setTextColor(Color.parseColor("#FF9522"));
                AdaptadorSVG.mostrarImagen(imageViewBuscarActividad, activity, R.raw.icono_buscar_actividad_seleccionado);
                break;
        }


        //se inicializan los linear layout
        linearLayoutBuscarActividad = (LinearLayout) convertView.findViewById(R.id.drawer_layout_buscar_actividad);
        linearLayoutActividades = (LinearLayout) convertView.findViewById(R.id.drawer_layout_actividades);
        linearLayoutPerfil = (LinearLayout) convertView.findViewById(R.id.drawer_layout_perfil);
        linearLayoutMedallas = (LinearLayout) convertView.findViewById(R.id.drawer_layout_medallas);
        linearLayoutMetas = (LinearLayout) convertView.findViewById(R.id.drawer_layout_metas);

        //Se agregan los clics del menú
        linearLayoutPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Perfil.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });

        linearLayoutMedallas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Medallas.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });

        linearLayoutBuscarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, BuscarActividad.class);
                activity.startActivity(intent);
                activity.finish();

            }
        });

        linearLayoutMetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Metas.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });

        linearLayoutActividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Actividades.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });

        //se obtiene la imagen guardada en el celular
        Bitmap bitmapImagenPerfil = FragmentPerfilPerfil.obtenerImagen();

        //se carga la imagen de perfil
        ImageView imageViewImagenPerfil = (ImageView)  convertView.findViewById(R.id.drawer_imagen_perfil);



        if (bitmapImagenPerfil != null) {
            imageViewImagenPerfil.setImageBitmap(bitmapImagenPerfil);
        }

        return convertView;
    }
}
