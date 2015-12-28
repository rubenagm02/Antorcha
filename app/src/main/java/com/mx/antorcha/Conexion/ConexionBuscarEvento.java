package com.mx.antorcha.Conexion;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mx.antorcha.SharedPreferences.MiembroSharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import static com.mx.antorcha.Conexion.InfoConexion.URL_BUSCAR_EVENTO;
import static com.mx.antorcha.Conexion.InfoConexion.URL_META;

/**
 * Created by Ruben on 27/12/2015.
 */
public class ConexionBuscarEvento extends AsyncTask<Void, Void, Void> {

    private String latitud;
    private String longitud;
    private String idMiembro;
    private String cercania;
    private String espacios;
    private Activity activity;
    private GoogleMap googleMap;
    private MiembroSharedPreferences miembroSharedPreferences;

    public ConexionBuscarEvento(Activity activity, GoogleMap googleMap ,String latitud, String longitud, String idMiembro, String cercania, String espacios) {
        this.activity = activity;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idMiembro = idMiembro;
        this.cercania = cercania;
        this.espacios = espacios;
        this.googleMap = googleMap;
        miembroSharedPreferences = new MiembroSharedPreferences(activity);
    }

    public ConexionBuscarEvento (Activity activity, GoogleMap googleMap) {
        miembroSharedPreferences = new MiembroSharedPreferences(activity);
        this.latitud = 20.699359689441785 + "";
        this.longitud = -103.29570472240448 + "";
        this.cercania = 10 + "";
        this.espacios = "si";
        this.idMiembro = miembroSharedPreferences.getId() + "";
        this.googleMap = googleMap;
    }

    @Override
    protected Void doInBackground(Void... params) {

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL_BUSCAR_EVENTO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("ConexionBuscarEvento", response);
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
                params.put("latitud", latitud);
                params.put("longitud", longitud);
                params.put("espacios", espacios);
                params.put("cercania", cercania);
                params.put("idMiembro", idMiembro);

                return params;
            }
        };
        Volley.newRequestQueue(activity).add(postRequest);

        return null;
    }

    public void agregarMarker (double latitud, double longitud) {
        googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(latitud, longitud))
        );
    }
}
