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
import com.mx.antorcha.Modelos.Disciplina;
import com.mx.antorcha.Modelos.Medalla;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import static com.mx.antorcha.Conexion.InfoConexion.URL_DISCIPLINA;
import static com.mx.antorcha.Conexion.InfoConexion.URL_MEDALLA;


/**
 *
 */
public class ConexionDisciplinas extends AsyncTask<Void, Void, Void> {

    private Activity activity;

    public ConexionDisciplinas(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Void... params) {

        final ConexionBaseDatosInsertar conexionBaseDatosInsertar = new ConexionBaseDatosInsertar(activity);

        StringRequest postRequest = new StringRequest(Request.Method.GET, URL_DISCIPLINA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Peticion", response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int x = 0; x < jsonArray.length(); x++) {

                                //Se obtienen los datos y se mandan a la medalla
                                Disciplina disciplina = new Disciplina(
                                        Integer.parseInt(jsonArray.getJSONObject(x).getString("id")),
                                        jsonArray.getJSONObject(x).getString("nombre"),
                                        jsonArray.getJSONObject(x).getString("descripcion"),
                                        ""
                                );

                                conexionBaseDatosInsertar.insertarDisciplina(disciplina);
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


        return null;
    }
}
