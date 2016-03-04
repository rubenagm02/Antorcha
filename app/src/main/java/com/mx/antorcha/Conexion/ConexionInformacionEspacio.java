package com.mx.antorcha.Conexion;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mx.antorcha.Modelos.EspacioDeportivo;
import com.mx.antorcha.Modelos.Resenia;
import com.mx.antorcha.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 *
 */
public class ConexionInformacionEspacio {

    private Activity activity;
    private String idEspacio;
    private View view;
    private ArrayList<Resenia> resenias;

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

                                    //Se obtienen las reseñas
                                    JSONArray jsonArrayResenias = jsonObject.getJSONArray("resenias");
                                    resenias = new ArrayList<>();

                                    for (int y = 0; y < jsonArrayResenias.length(); y++) {
                                        JSONObject jsonObjectResenia = jsonArrayResenias.getJSONObject(y);

                                        ((TextView) view.findViewById(R.id.sliding_buscar_espacio_resenia)).setText(jsonObjectResenia.getString("resenia"));
                                        ((TextView) view.findViewById(R.id.sliding_buscar_espacio_titulo_resenia)).setText(jsonObjectResenia.getString("titulo"));
                                        resenias.add(new Resenia(jsonObjectResenia.getString("titulo"), jsonObjectResenia.getString("resenia"), jsonObjectResenia.getString("nombre")));
                                    }

                                    //Se obtienen los nombres de los deportes
                                    JSONArray jsonArrayDeportes = jsonObject.getJSONArray("deportes");
                                    ArrayList<String> deportes = new ArrayList<>();

                                    for (int y = 0; y < jsonArrayDeportes.length(); y++) {
                                        deportes.add(jsonArrayDeportes.getJSONObject(y).getString("deporte"));
                                    }

                                    ImageView imageViewImagenGaleria = (ImageView) view.findViewById(R.id.sliding_buscar_espacio_imagen_galeria);
                                    DescargarImagen.cargarImagen(activity, InfoConexion.URL_DESCARGAR_IMAGEN_ESPACIO + espacioDeportivo.getId() + "_2_.png", imageViewImagenGaleria);

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
