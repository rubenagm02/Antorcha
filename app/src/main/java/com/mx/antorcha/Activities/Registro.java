package com.mx.antorcha.Activities;

import android.content.Intent;
import android.content.IntentSender;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.Conexion.ConexionRegistro;
import com.mx.antorcha.R;

public class Registro extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //se carga la barra de android por el xml
        Toolbar toolbar = (Toolbar) findViewById(R.id.registro_toolbar);
        setSupportActionBar(toolbar);

        //se cargan las imagenes de la barra
        ImageView imageViewAtras = (ImageView) findViewById(R.id.registro_barra_regresar);
        ImageView imageViewRegistrarse = (ImageView) findViewById(R.id.registro_boton_regitrarse);

        //se cargan las imagenes de las imagenes en svg
        AdaptadorSVG.mostrarImagen(imageViewAtras, this, R.raw.icono_regresar);
        AdaptadorSVG.mostrarImagen(imageViewRegistrarse, this, R.raw.boton_guardar_meta);

        //Se inicializan todos los elementos
        final EditText editTextNombre = (EditText) findViewById(R.id.registro_nombre);
        final EditText editTextApellido = (EditText) findViewById(R.id.registro_apellidos);
        final EditText editTextCorreo = (EditText) findViewById(R.id.registro_correo);
        final EditText editTextPassword = (EditText) findViewById(R.id.registro_password);



        imageViewRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConexionRegistro conexionRegistro = new ConexionRegistro(
                        editTextNombre.getText().toString() + " " + editTextApellido.getText().toString(),
                        "M",
                        editTextCorreo.getText().toString(),
                        editTextPassword.getText().toString(),
                        "1992-02-02",
                        Registro.this
                );

                conexionRegistro.execute();
            }
        });


    }


}
