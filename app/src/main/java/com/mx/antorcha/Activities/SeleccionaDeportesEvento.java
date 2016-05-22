package com.mx.antorcha.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.Adaptadores.AdaptadorListaNuevosDeportes;
import com.mx.antorcha.BaseDatos.ConexionBaseDatosObtener;
import com.mx.antorcha.Modelos.Disciplina;
import com.mx.antorcha.R;
import com.mx.antorcha.SharedPreferences.DisciplinasDeportesSharedPreferences;

import java.util.ArrayList;

public class SeleccionaDeportesEvento extends AppCompatActivity {

    private ArrayList<String> stringDisciplinas;
    private ArrayList<String> stringDeportes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecciona_deportes_evento);

        //se carga la barra de android por el xml
        Toolbar toolbar = (Toolbar) findViewById(R.id.selecciona_deportes_evento_toolbar);
        setSupportActionBar(toolbar);

        //click en regresar
        ImageView imageViewRegresar = (ImageView) findViewById(R.id.selecciona_deportes_evento_regresar);
        AdaptadorSVG.mostrarImagen(imageViewRegresar, this, R.raw.icono_regresar);

        imageViewRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ConexionBaseDatosObtener conexionBaseDatosObtener = new ConexionBaseDatosObtener(this);
        //Se cargan las preferencias del arrayList
        final DisciplinasDeportesSharedPreferences disciplinasDeportesSharedPreferences
                = new DisciplinasDeportesSharedPreferences(this);

        //Se crean los dos arrays que guardarán los deportes favoritos seleccionados y las disciplinas
        stringDeportes = disciplinasDeportesSharedPreferences.getDeportes();
        stringDisciplinas = disciplinasDeportesSharedPreferences.getDisciplinas();

        //Se obitnene los datos guardados
        ArrayList<Disciplina> disciplinas = conexionBaseDatosObtener.obtenerDisciplinas();

        ListView listViewDisciplinas = (ListView) findViewById(R.id.selecciona_deportes_evento_lista);
        AdaptadorListaNuevosDeportes adaptadorListaNuevosDeportes = new AdaptadorListaNuevosDeportes(this, disciplinas);
        adaptadorListaNuevosDeportes.setStringDeportes(stringDeportes);
        adaptadorListaNuevosDeportes.setStringDisciplinas(stringDisciplinas);
        listViewDisciplinas.setAdapter(adaptadorListaNuevosDeportes);
    }
}
