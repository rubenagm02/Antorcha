package com.mx.antorcha.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.R;

import java.util.Calendar;

public class NuevoEvento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_evento);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //se carga la barra de android por el xml
        Toolbar toolbar = (Toolbar) findViewById(R.id.nuevo_evento_toolbar);
        setSupportActionBar(toolbar);

        ImageView imageViewRegresar = (ImageView) findViewById(R.id.nuevo_evento_regresar);
        AdaptadorSVG.mostrarImagen(imageViewRegresar, this, R.raw.icono_regresar);

        imageViewRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Para la fecha del evento
        final TextView textViewFecha = (TextView) findViewById(R.id.nuevo_evento_fecha);
        textViewFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int anio = c.get(Calendar.YEAR);
                int mes = c.get(Calendar.MONTH);
                int dia = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(NuevoEvento.this,
                        R.style.datePickerDialogStyle,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int anio,
                                                  int mes, int dia) {
                                textViewFecha.setText(anio + "-"
                                        + (mes + 1) + "-" + dia);

                            }
                        }, anio, mes, dia);
                datePickerDialog.show();
            }
        });

        TextView textViewSiguiente = (TextView) findViewById(R.id.nuevo_evento_siguiente);
        textViewSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NuevoEvento.this, SeleccionaUbicacion.class);
                startActivity(intent);
            }
        });
    }
}
