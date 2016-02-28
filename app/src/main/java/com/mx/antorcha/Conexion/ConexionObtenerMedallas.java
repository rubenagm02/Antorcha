package com.mx.antorcha.Conexion;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mx.antorcha.SharedPreferences.MedallasSharedPreferences;
import com.mx.antorcha.SharedPreferences.MiembroSharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.mx.antorcha.Conexion.InfoConexion.URL_OBTENER_MEDALLAS;

/**
 *
 */
public class ConexionObtenerMedallas {

    private Activity activity;

    public ConexionObtenerMedallas(Activity activity) {
        this.activity = activity;
    }

    public void obtenerMedallas () {
        int idMiembro = new MiembroSharedPreferences(activity).getId();

        StringRequest postRequest = new StringRequest(Request.Method.GET, URL_OBTENER_MEDALLAS + idMiembro,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("ObtenerMedallas", response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int x = 0; x < jsonArray.length(); x++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(x);

                                MedallasSharedPreferences medallasSharedPreferences = new MedallasSharedPreferences(activity);
                                medallasSharedPreferences.agregarMedalla(jsonObject.getInt("idMedalla"));
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
