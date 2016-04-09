package com.mx.antorcha.Bienvenida;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mx.antorcha.R;

public class SeleccionaDeportes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecciona_deportes);

        findViewById(R.id.selecciona_deportes_siguiente).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeleccionaDeportes.this, BienvenidaNotificaciones.class);
                startActivity(intent);
            }
        });
    }
}
