package com.mx.antorcha.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.Adaptadores.AdaptadorListaNuevosDeportes;
import com.mx.antorcha.Modelos.Disciplina;
import com.mx.antorcha.R;

import java.util.ArrayList;

public class NuevoDeporteFavorito extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_deporte_favorito);

        //se carga la barra de android por el xml
        Toolbar toolbar = (Toolbar) findViewById(R.id.nuevo_deporte_toolbar);
        setSupportActionBar(toolbar);

        //click en regresar
        ImageView imageViewRegresar = (ImageView) findViewById(R.id.nuevo_deporte_barra_regresar);
        AdaptadorSVG.mostrarImagen(imageViewRegresar, this, R.raw.icono_regresar);

        imageViewRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /****** VARIABLES DE PRUEBAS ******/
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        disciplinas.add(new Disciplina());
        disciplinas.add(new Disciplina());
        disciplinas.add(new Disciplina());

        /***********/

        //Se Carga el listView
        ListView listViewDisciplinas = (ListView) findViewById(R.id.nuevo_deporte_lista_disciplina);
        AdaptadorListaNuevosDeportes adaptadorListaNuevosDeportes = new AdaptadorListaNuevosDeportes(this, disciplinas);
        listViewDisciplinas.setAdapter(adaptadorListaNuevosDeportes);
    }
}
