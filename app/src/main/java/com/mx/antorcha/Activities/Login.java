package com.mx.antorcha.Activities;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.PersonBuffer;
import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.Conexion.ConexionLogin;
import com.mx.antorcha.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.FacebookSdk;
import com.mx.antorcha.SharedPreferences.MiembroSharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class Login extends AppCompatActivity {

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_login);

        //se carga la barra de android por el xml
        Toolbar toolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);

        //Se agrega el callback
        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_facebook_login);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i("Login Facebook", loginResult.toString());
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                // Application code
                                Log.v("LoginActivity", response.toString());
                                try {
                                    JSONObject jsonObject = new JSONObject(response.toString()).getJSONObject("graphObject");
                                    String idFacebook = jsonObject.getString("id");
                                    String nombre = jsonObject.getString("name");
                                    String email = jsonObject.getString("email");
                                    String genero = jsonObject.getString("gender");
                                    String fechaNacimiento = jsonObject.getString("birthday");

                                    ConexionLogin conexionLogin = new ConexionLogin(idFacebook, Login.this);
                                    conexionLogin.execute();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.i("Login Facebook", "Cancelado");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("Login Facebook", error.toString());
            }
        });

        //Se carga el boton de inicio de sesión
        ImageView imageViewInicioSesion = (ImageView) findViewById(R.id.login_boton_inicio_sesion);
        ImageView imageViewAtras = (ImageView) findViewById(R.id.login_barra_atras);

        //Se carga la imagen
        AdaptadorSVG.mostrarImagen(imageViewInicioSesion, this, R.raw.boton_registrate);
        AdaptadorSVG.mostrarImagen(imageViewAtras, this, R.raw.icono_regresar);

        //el on click de regresar
        imageViewAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }



    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}