package com.mx.antorcha.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.R;

public class DetalleEvento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evento);

        //se carga la barra de android por el xml
        Toolbar toolbar = (Toolbar) findViewById(R.id.detalle_evento_toolbar);
        setSupportActionBar(toolbar);

        //Se carga la imagen para el regresar
        ImageView imageViewRegresar = (ImageView) findViewById(R.id.detalle_evento_regresar);
        AdaptadorSVG.mostrarImagen(imageViewRegresar, this, R.raw.icono_regresar);

        imageViewRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
