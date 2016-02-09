package com.mx.antorcha.Conexion;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mx.antorcha.BaseDatos.ConexionBaseDatosEliminar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ConexionEliminarMeta {

    static public void eliminarMeta (final int idMeta, int idServidor, final Activity activity) {
        StringRequest postRequest = new StringRequest(Request.Method.DELETE, InfoConexion.URL_ELIMINAR_META + idServidor,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Peticion", response);

                        try {
                            if ((new JSONObject(response).getString("eliminado")).equals("true")) {
                                ConexionBaseDatosEliminar conexionBaseDatosEliminar
                                        = new ConexionBaseDatosEliminar(activity);
                                conexionBaseDatosEliminar.eliminarMeta(idMeta);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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
                return params;
            }
        };
        Volley.newRequestQueue(activity).add(postRequest);
    }
}
