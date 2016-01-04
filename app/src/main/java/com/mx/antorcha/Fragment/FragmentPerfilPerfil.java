package com.mx.antorcha.Fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mx.antorcha.Activities.Perfil;
import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.Adaptadores.AdaptadorListaMedallas;
import com.mx.antorcha.Dialogos.DialogoImagenPerfil;
import com.mx.antorcha.Modelos.Medalla;
import com.mx.antorcha.OtrasFunciones.CalculoFechas;
import com.mx.antorcha.R;
import com.mx.antorcha.SharedPreferences.MiembroSharedPreferences;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by Ruben on 09/12/2015.
 */
public class FragmentPerfilPerfil extends Fragment {

    private Activity activity;
    private FragmentManager fragmentManager;
    private Perfil perfil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_perfil_perfil, container, false);

        /******** VARIABLES TEMPORALES PARA PRUEBAS *******/
        ArrayList<Medalla> medallas = new ArrayList<>();
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());

        /*************************************************/

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);

        //Se crea el recycler view para las medallas
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.perfil_lista_medallas);
        recyclerView.setLayoutManager(layoutManager);
        AdaptadorListaMedallas adaptadorListaMedallas = new AdaptadorListaMedallas(activity, medallas);

        recyclerView.setAdapter(adaptadorListaMedallas);

        //Se colocan las imagenes de las medallas
        ImageView imageViewFlechaIzquierda = (ImageView) rootView.findViewById(R.id.perfil_flecha_izquierda);
        ImageView imageViewFlechaDerecha = (ImageView) rootView.findViewById(R.id.perfil_flecha_derecha);

        AdaptadorSVG.mostrarImagen(imageViewFlechaDerecha, activity, R.raw.icono_flecha_derecha);
        AdaptadorSVG.mostrarImagen(imageViewFlechaIzquierda, activity, R.raw.icono_flecha_izquierda);

        //Se carga la imagen para el degradado de la foto, el fondo oscuro
        ImageView imageViewDifuminado = (ImageView) rootView.findViewById(R.id.perfil_imagen_degradado);
        AdaptadorSVG.mostrarImagen(imageViewDifuminado, activity, R.raw.degradado_inferior);

        //el click en la foto de perfil
        ImageView imageViewPerfil = (ImageView) rootView.findViewById(R.id.perfil_imagen_miembro);
        imageViewPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoImagenPerfil dialogoImagenPerfil = new DialogoImagenPerfil();
                dialogoImagenPerfil.setActivity(activity);
                dialogoImagenPerfil.show(fragmentManager, "dialogo_imagen_perfil");
            }
        });

        perfil.setImageViewPerfil(imageViewPerfil);

        Bitmap bitmapImagenPerfil = obtenerImagen();

        if (bitmapImagenPerfil != null) {
            imageViewPerfil.setImageBitmap(bitmapImagenPerfil);
        }

        //se carga la información del usuario en el perfil
        MiembroSharedPreferences miembroSharedPreferences = new MiembroSharedPreferences(activity);

        //se inicializan los datos
        TextView textViewNombre = (TextView) rootView.findViewById(R.id.perfil_nombre_miembro);
        textViewNombre.setText(miembroSharedPreferences.getNombre());

        //se coloca la edad
        TextView textViewEdad = (TextView) rootView.findViewById(R.id.perfil_edad_miembro);
        textViewEdad.setText(CalculoFechas.calcularEdad(miembroSharedPreferences.getFechaNacimiento()) + " años");

        return rootView;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    static public Bitmap obtenerImagen () {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/antorcha/perfil_antorcha.jpg");
        Bitmap bitmap = null;

        try {
            bitmap = BitmapFactory.decodeStream(new FileInputStream(myDir));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}
