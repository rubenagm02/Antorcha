package com.mx.antorcha.Conexion;

import android.app.Activity;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mx.antorcha.BaseDatos.ConexionBaseDatosActualizar;
import com.mx.antorcha.BaseDatos.ConexionBaseDatosObtener;
import com.mx.antorcha.Modelos.Meta;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.mx.antorcha.Conexion.InfoConexion.URL_META;


/**
 *
 */
public class ConexionMetas extends AsyncTask<Void, Void, Void>{

    private Activity activity;
    private int id;

    public ConexionMetas (Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Void... params) {
        ConexionBaseDatosObtener conexionBaseDatosObtener = new ConexionBaseDatosObtener(activity);
        final ConexionBaseDatosActualizar conexionBaseDatosActualizar = new ConexionBaseDatosActualizar(activity);
        ArrayList<Meta> metas = conexionBaseDatosObtener.obtenerMetas();

        for (int x = 0; x < metas.size(); x++) {
            final Meta meta = metas.get(x);

            if (meta.getIdServidor() == 0) {
                StringRequest postRequest = new StringRequest(Request.Method.POST, URL_META,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    int idServidor = jsonArray.getJSONObject(0).getInt("id");
                                    conexionBaseDatosActualizar.actualizarMetaIdServidor(meta.getId(), idServidor);
                                    ConexionMetaProgreso conexionMetaProgreso = new ConexionMetaProgreso(activity, idServidor);
                                    conexionMetaProgreso.execute();
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
                        params.put("idMiembro", id + "");
                        params.put("nombre", meta.getNombre());
                        params.put("fechaInicio", meta.getFechaInicio());
                        params.put("fechaFin", meta.getFechaFin());
                        params.put("inicio", meta.getInicio() + "");
                        params.put("fin", meta.getFin()+ "");
                        params.put("tipoMedida", meta.getTipoMedida());

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

    public void setId(int id) {
        this.id = id;
    }
}
