package com.mx.antorcha.Conexion;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mx.antorcha.Modelos.Medalla;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import static com.mx.antorcha.Conexion.InfoConexion.URL_LOGIN;
import static com.mx.antorcha.Conexion.InfoConexion.URL_MEDALLA;

/**
 *
 */
public class ConexionLogin extends AsyncTask<Void, Void, Void>{

    private Activity activity;
    private String correo;
    private String password;
    private String idGoogle;
    private String idFacebook;

    public ConexionLogin(Activity activity, String correo, String password) {
        this.activity = activity;
        this.correo = correo;
        this.password = password;
    }

    public ConexionLogin (Activity activity, String idGoogle) {
        this.activity = activity;
        this.idGoogle = idGoogle;
    }

    public ConexionLogin (String idFacebook, Activity activity) {
        this.activity = activity;
        this.idFacebook = idFacebook;
    }


    @Override
    protected Void doInBackground(Void... params) {

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Login", response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                // the POST parameters:

                if (correo != null && password != null) {
                    params.put("correo", correo);
                    params.put("password", password);
                } else if (idFacebook != null) {
                    params.put("idFacebook", idFacebook);
                } else if (idGoogle != null) {
                    params.put("idGoogle", idGoogle);
                } else {
                    Toast.makeText(activity,
                            "Estamos teniendo un problema para iniciar sesión, " +
                                    "por favor verifica tus datos y vuelve a intentarlo",
                            Toast.LENGTH_LONG).show();
                }
                return params;
            }
        };
        Volley.newRequestQueue(activity).add(postRequest);

        return null;
    }

    @Override
    public void onPostExecute (Void v) {
        //lo que se hace una ves que se fue logueado.
    }
}
