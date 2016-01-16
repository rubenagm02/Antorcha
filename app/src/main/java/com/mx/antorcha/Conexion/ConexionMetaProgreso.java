package com.mx.antorcha.Conexion;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mx.antorcha.BaseDatos.ConexionBaseDatosActualizar;
import com.mx.antorcha.BaseDatos.ConexionBaseDatosObtener;
import com.mx.antorcha.Modelos.MetaProgreso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.mx.antorcha.Conexion.InfoConexion.URL_META_PROGRESO;

/**
 *
 */
public class ConexionMetaProgreso extends AsyncTask<Void, Void, Void> {

    private Activity activity;
    private int idMeta;

    private ConexionBaseDatosActualizar conexionBaseDatosActualizar;

    public ConexionMetaProgreso (Activity activity, int idMeta) {
        this.activity = activity;
        this.idMeta = idMeta;
        conexionBaseDatosActualizar = new ConexionBaseDatosActualizar(activity);
    }

    @Override
    protected Void doInBackground(Void... params) {
        ConexionBaseDatosObtener conexionBaseDatosObtener = new ConexionBaseDatosObtener(activity);
        ArrayList<MetaProgreso> metaProgresos = conexionBaseDatosObtener.obtenerMetaProgreso(idMeta);

        for (int x = 0; x < metaProgresos.size(); x++) {
            final MetaProgreso metaProgreso = metaProgresos.get(x);

            if (metaProgreso.getIdServidor() == 0) {

                StringRequest postRequest = new StringRequest(Request.Method.POST, URL_META_PROGRESO,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i("Peticion", response);

                                try {
                                    int idMetaProgteso = new JSONObject(response).getInt("id");

                                    conexionBaseDatosActualizar.actualizarMetaProgreso(metaProgreso.getId(), idMetaProgteso);
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
                        params.put("idMeta", idMeta + "");
                        params.put("progreso", metaProgreso.getProgreso() + "");
                        params.put("fecha", metaProgreso.getFecha());

                        return params;
                    }
                };

                Volley.newRequestQueue(activity).add(postRequest);
            }
        }
        
        return null;
    }

    @Override
    protected void onPostExecute(Void v){

    }
}
