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

import static com.mx.antorcha.Conexion.InfoConexion.URL_RESENIA;

/**
 *
 */
public class ConexionValoracion extends AsyncTask<Void, Void, Void>{

    private Activity activity;
    private String id;
    private String valoracion;
    private String tipo;

    public ConexionValoracion(Activity activity, String id, String valoracion, String tipo) {
        this.activity = activity;
        this.id = id;
        this.valoracion = valoracion;
        this.tipo = tipo;
    }

    @Override
    protected Void doInBackground(Void... params) {

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL_RESENIA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Si todo sale correcto, después del registro se hace...
                        Log.i("Peticion registro", response);

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
                params.put("valoracion", valoracion);
                params.put("id", id);
                params.put("tipo", tipo);

                return params;
            }
        };

        Volley.newRequestQueue(activity).add(postRequest);

        return null;
    }
}
