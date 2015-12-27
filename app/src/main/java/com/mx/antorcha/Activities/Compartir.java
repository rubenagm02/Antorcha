package com.mx.antorcha.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.R;

public class Compartir extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartir);

        ImageView imageViewInvitar = (ImageView) findViewById(R.id.compartir_boton_compartir);
        AdaptadorSVG.mostrarImagen(imageViewInvitar, this, R.raw.boton_guardar_meta); //Temporal

        imageViewInvitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout relativeLayoutCompartir = (RelativeLayout) findViewById(R.id.compartir_layout_compartir);
                com.mx.antorcha.Compartir.Compartir compartir = new com.mx.antorcha.Compartir.Compartir(Compartir.this);
                compartir.agregarView(relativeLayoutCompartir);
                compartir.agregarTexto("antorcha.com.mx/4ntorch4");
                compartir.compartir();
            }
        });
    }
}
