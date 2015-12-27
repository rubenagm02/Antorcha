package com.mx.antorcha.Activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.Adaptadores.AdaptadorSpinner;
import com.mx.antorcha.BaseDatos.ConexionBaseDatosInsertar;
import com.mx.antorcha.Modelos.Meta;
import com.mx.antorcha.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class NuevaMeta extends AppCompatActivity {
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_meta);
        activity = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Se inicializan todas las variables qpara datos
        final EditText editTextNombre = (EditText) findViewById(R.id.nueva_meta_nombre_meta);
        final EditText editTextInicio = (EditText) findViewById(R.id.nueva_meta_inicio);
        final EditText editTextFin = (EditText) findViewById(R.id.nueva_meta_fin);

        //Se inicializa lo necesario para mostrar el calendario
        final TextView textViewCalendario = (TextView) findViewById(R.id.nueva_meta_fecha);
        textViewCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int anio = c.get(Calendar.YEAR);
                int mes = c.get(Calendar.MONTH);
                int dia = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(activity,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int anio,
                                              int mes, int dia) {
                            textViewCalendario.setText(anio + "-"
                                    + (mes + 1) + "-" + dia);

                        }
                    }, anio, mes, dia);

                datePickerDialog.show();
            }
        });

        //Se inicializa la activity
        activity = this;

        //se carga la barra de android por el xml
        Toolbar toolbar = (Toolbar) findViewById(R.id.nueva_meta_toolbar);
        setSupportActionBar(toolbar);

        //click en regresar
        ImageView imageViewRegresar = (ImageView) findViewById(R.id.nueva_meta_barra_regresar);
        AdaptadorSVG.mostrarImagen(imageViewRegresar, this, R.raw.icono_regresar);

        imageViewRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NuevaMeta.this, Metas.class);
                startActivity(intent);
                finish();
            }
        });

        ImageView imageViewGuardarMeta = (ImageView) findViewById(R.id.nueva_meta_image_guardar);
        AdaptadorSVG.mostrarImagen(imageViewGuardarMeta, this,R.raw.boton_guardar_meta);

        //Se cargan los datos al spinner
        final Spinner spinnerTipo = (Spinner) findViewById(R.id.nueva_meta_tipo_medida);

        //Los tipos de medidas
        ArrayList<String> medidas = new ArrayList<>();
        medidas.add("Selecciona una medida");
        medidas.add("Kilogramo");
        medidas.add("Metros");
        medidas.add("Centimetros");
        medidas.add("Kilometros");
        spinnerTipo.setAdapter(new AdaptadorSpinner(NuevaMeta.this, medidas));

        //Se guarda la meta
        imageViewGuardarMeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Meta meta = new Meta();
                meta.setNombre(editTextNombre.getText().toString());
                meta.setInicio(Double.parseDouble(editTextInicio.getText().toString()));
                meta.setFin(Double.parseDouble(editTextFin.getText().toString()));

                //se guarda la fecha actual
                final Calendar c = Calendar.getInstance();
                int anio = c.get(Calendar.YEAR);
                int mes = c.get(Calendar.MONTH);
                int dia = c.get(Calendar.DAY_OF_MONTH);

                meta.setFechaInicio(anio + "-" + mes + "-" + dia);
                meta.setFechaFin(textViewCalendario.getText().toString());
                meta.setTipoMedida(spinnerTipo.getSelectedItem().toString());

                ConexionBaseDatosInsertar conexionBaseDatosInsertar = new ConexionBaseDatosInsertar(activity);
                conexionBaseDatosInsertar.insertarMeta(meta);

                Intent intent = new Intent(NuevaMeta.this, Metas.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
