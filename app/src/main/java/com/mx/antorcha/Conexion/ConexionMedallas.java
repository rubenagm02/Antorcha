package com.mx.antorcha.Conexion;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mx.antorcha.BaseDatos.ConexionBaseDatosInsertar;
import com.mx.antorcha.Modelos.Medalla;
import com.mx.antorcha.Modelos.Meta;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.mx.antorcha.Conexion.InfoConexion.URL_MEDALLA;
import static com.mx.antorcha.Conexion.InfoConexion.URL_META;

/**
 *
 */
public class ConexionMedallas extends AsyncTask<Void, Void, Void> {

    private Activity activity;

    public ConexionMedallas(Activity activity) {

        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Void... params) {

        final ConexionBaseDatosInsertar conexionBaseDatosInsertar = new ConexionBaseDatosInsertar(activity);

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL_MEDALLA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Peticion", response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int x = 0; x < jsonArray.length(); x++) {

                                //Se obtienen los datos y se mandan a la medalla
                                Medalla medalla = new Medalla(
                                        Integer.parseInt(jsonArray.getJSONObject(x).getString("id")),
                                        jsonArray.getJSONObject(x).getString("nombre"),
                                        Integer.parseInt(jsonArray.getJSONObject(x).getString("tipo")),
                                        jsonArray.getJSONObject(x).getString("descripcion"),
                                        jsonArray.getJSONObject(x).getString("comoObtener"),
                                        jsonArray.getJSONObject(x).getString("imagen")
                                );

                                conexionBaseDatosInsertar.insertarMedalla(medalla);
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

        return  null;
    }
}
