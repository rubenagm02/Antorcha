package com.mx.antorcha.Conexion;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mx.antorcha.Modelos.EspacioDeportivo;
import com.mx.antorcha.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 *
 */
public class ConexionInformacionEspacio {

    private Activity activity;
    private String idEspacio;
    private View view;

    public ConexionInformacionEspacio(Activity activity, String idEspacio) {
        this.activity = activity;
        this.idEspacio = idEspacio;
    }

    public void obtenerEspacio() {

        StringRequest postRequest = new StringRequest(Request.Method.GET, InfoConexion.URL_BUSCAR_UN_ESPACIO + idEspacio,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("ConexionInformacionEsp", response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int x = 0; x < jsonArray.length(); x++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(x);

                                EspacioDeportivo espacioDeportivo = new EspacioDeportivo(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("nombre"),
                                        jsonObject.getString("descripcion"),
                                        jsonObject.getString("domicilio"),
                                        jsonObject.getString("colonia"),
                                        jsonObject.getString("codigoPostal"),
                                        jsonObject.getString("municipio"),
                                        jsonObject.getString("ciudad"),
                                        jsonObject.getString("estado"),
                                        jsonObject.getString("telefono"),
                                        jsonObject.getDouble("latitud"),
                                        jsonObject.getDouble("longitud"),
                                        jsonObject.getString("valoracion"),
                                        jsonObject.getString("horario"));

                                if (view != null) {
                                    ((TextView) view.findViewById(R.id.sliding_buscar_espacio_descripcion)).setText(espacioDeportivo.getDescripcion());

                                }

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
                //No hay parametros
                return params;
            }
        };
        Volley.newRequestQueue(activity).add(postRequest);

    }

    public void setView(View view) {
        this.view = view;
    }

    public void cargarInformacionEspacio () {

    }
}
