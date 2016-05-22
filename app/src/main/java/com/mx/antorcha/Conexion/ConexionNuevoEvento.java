package com.mx.antorcha.Conexion;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mx.antorcha.SharedPreferences.DisciplinasDeportesSharedPreferences;
import com.mx.antorcha.SharedPreferences.MiembroSharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.mx.antorcha.Conexion.InfoConexion.URL_SUBIR_EVENTO;

/**
 * Created by Rubén
 */
public class ConexionNuevoEvento {

    private Activity activity;
    private String[] datos;

    public ConexionNuevoEvento(Activity activity, String[] datos) {
        this.activity = activity;
        this.datos = datos;
    }

    public void enviar(){
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL_SUBIR_EVENTO,
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
                params.put("nombre", datos[0]);
                params.put("direccion", datos[0]);
                params.put("estado", datos[0]);
                params.put("municipio", datos[0]);
                params.put("estado", "Jalisco");
                params.put("descripcion", datos[0]);
                params.put("fechaInicio", datos[0]);
                params.put("latitud", datos[0]);
                params.put("longitud", datos[0]);
                params.put("telefono", datos[0]);
                params.put("codigoPostal", datos[0]);

                return params;
            }
        };

        Volley.newRequestQueue(activity).add(postRequest);
    }
}
