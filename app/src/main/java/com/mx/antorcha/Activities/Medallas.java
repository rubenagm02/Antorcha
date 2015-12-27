package com.mx.antorcha.Activities;

import android.content.pm.ActivityInfo;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.Adaptadores.AdaptadorListaMedallas;
import com.mx.antorcha.Compartir.*;
import com.mx.antorcha.Compartir.Compartir;
import com.mx.antorcha.Conexion.DescargarImagen;
import com.mx.antorcha.MenuDrawer.AdapterDrawer;
import com.mx.antorcha.Modelos.Medalla;
import com.mx.antorcha.R;

import java.util.ArrayList;

public class Medallas extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medallas);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //se carga la barra de android por el xml
        Toolbar toolbar = (Toolbar) findViewById(R.id.medallas_toolbar);
        setSupportActionBar(toolbar);

        //se carga el drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        listView = (ListView) findViewById(R.id.lista_drawer);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("");
        listView.setAdapter(new AdapterDrawer(this, R.layout.drawer, arrayList, "Medallas"));

        //Se muestra el boton del drawer
        ImageView imageViewDrawer = (ImageView) findViewById(R.id.medallas_barra_drawer);
        AdaptadorSVG.mostrarImagen(imageViewDrawer, this, R.raw.icono_menu_drawer);

        imageViewDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });


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
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        //Se crea el recycler view para las medallas
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.medallas_lista_medallas);
        recyclerView.setLayoutManager(layoutManager);
        AdaptadorListaMedallas adaptadorListaMedallas = new AdaptadorListaMedallas(this, medallas);

        recyclerView.setAdapter(adaptadorListaMedallas);

        //Se colocan las imagenes de las medallas
        ImageView imageViewFlechaIzquierda = (ImageView) findViewById(R.id.medallas_flecha_izquierda);
        ImageView imageViewFlechaDerecha = (ImageView) findViewById(R.id.medallas_flecha_derecha);
        ImageView imageViewMedallaPrincipal = (ImageView) findViewById(R.id.medallas_medalla_principal);

        AdaptadorSVG.mostrarImagen(imageViewFlechaDerecha, this, R.raw.icono_flecha_derecha);
        AdaptadorSVG.mostrarImagen(imageViewFlechaIzquierda, this, R.raw.icono_flecha_izquierda);

        //se coloca el boton de compartir
        ImageView imageViewCompartir = (ImageView) findViewById(R.id.medallas_compartir);
        AdaptadorSVG.mostrarImagen(imageViewCompartir, this, R.raw.icono_compartir);

        imageViewCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.mx.antorcha.Compartir.Compartir compartir = new Compartir(Medallas.this);
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.medallas_layout_compartir);
                compartir.agregarView(linearLayout);
                compartir.agregarTexto("antorcha.com.mx");
                compartir.compartir();
            }
        });
    }
}
