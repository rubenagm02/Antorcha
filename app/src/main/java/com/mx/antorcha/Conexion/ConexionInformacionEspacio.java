package com.mx.antorcha.Conexion;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static com.mx.antorcha.Conexion.InfoConexion.URL_BUSCAR_EVENTO;
import static com.mx.antorcha.Conexion.InfoConexion.URL_INFORMACION_ESPACIO;

/**
 * Created by Ruben on 27/12/2015.
 */
public class ConexionInformacionEspacio extends AsyncTask<Void, Void, Void> {

    private Activity activity;
    private String idEspacio;

    public ConexionInformacionEspacio(Activity activity, String idEspacio) {
        this.activity = activity;
        this.idEspacio = idEspacio;
    }

    @Override
    protected Void doInBackground(Void... params) {

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL_INFORMACION_ESPACIO + idEspacio,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("ConexionInformacionEsp", response);
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
                //No hay parametros
                return params;
            }
        };
        Volley.newRequestQueue(activity).add(postRequest);

        return null;
    }
}
