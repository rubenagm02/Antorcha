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
import android.widget.ListView;

import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.Adaptadores.AdaptadorEspecialistaCard;
import com.mx.antorcha.MenuDrawer.AdapterDrawer;
import com.mx.antorcha.Modelos.Especialista;
import com.mx.antorcha.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Especialistas extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especialistas);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //se carga la barra de android por el xml
        Toolbar toolbar = (Toolbar) findViewById(R.id.especialistas_toolbar);
        setSupportActionBar(toolbar);

        //Se agrega el drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        listView = (ListView) findViewById(R.id.lista_drawer);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("");
        listView.setAdapter(new AdapterDrawer(this, R.layout.drawer, arrayList, "Especialistas"));

        //Se carga el drawer
        ImageView imageViewDrawer = (ImageView) findViewById(R.id.especialistas_barra_drawer);
        AdaptadorSVG.mostrarImagen(imageViewDrawer, this, R.raw.icono_menu_drawer);

        imageViewDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });


        /******* VARIABLES DE PRUEBA ******/
        ArrayList<Especialista> especialistas = new ArrayList<>();
        especialistas.add(new Especialista("Rubén Alejandro Guardado Maldonado",
                "Ing. en computación",
                "Computadoras", "Una personas que le gusta el desarrollo de aplicaciones móviles",
                "3331420208",
                "alguncorreo@gmail.com",
                "Hombre"));

        //Se carga la lista de especialistas
        //se carga la parte del recycler view
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(Especialistas.this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_especialistas);
        recyclerView.setLayoutManager(layoutManager);

        AdaptadorEspecialistaCard adaptadorEspecialistaCard = new AdaptadorEspecialistaCard(Especialistas.this, especialistas);
        recyclerView.setAdapter(adaptadorEspecialistaCard);
    }
}
