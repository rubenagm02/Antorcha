package com.mx.antorcha.Conexion;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mx.antorcha.Activities.BuscarActividad;
import com.mx.antorcha.Activities.Principal;
import com.mx.antorcha.SharedPreferences.MiembroSharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import static com.mx.antorcha.Conexion.InfoConexion.URL_FACEBOOK_IMAGEN_1;
import static com.mx.antorcha.Conexion.InfoConexion.URL_FACEBOOK_IMAGEN_2;
import static com.mx.antorcha.Conexion.InfoConexion.URL_REGISTRO;

/**
 *
 */
public class ConexionRegistro extends AsyncTask<Void, Void, Void> {

    private String nombre;
    private String sexo;
    private String correo;
    private String password;
    private String fechaNacimiento;
    private Activity activity;
    private String facebook;
    private boolean bandera = false; //Determina si se cambia de actividad o no

    public ConexionRegistro(String nombre, String sexo, String correo, String password, String fechaNacimiento, Activity activity) {

        this.nombre = nombre;
        this.sexo = sexo;
        this.correo = correo;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.activity = activity;
    }

    public ConexionRegistro () {

    }

    @Override
    protected Void doInBackground(Void... params) {
//así es como funciona el post, los campos se mandan con una llave ys e agregan...
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL_REGISTRO,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Si todo sale correcto, después del registro se hace...
                    Log.i("Peticion registro", response);

                    //se obtiene el Id del miembro
                    int id;

                    try {
                        id = new JSONArray(response).getJSONObject(0).getInt("id");
//Y los decodifico como un json... eso sí lo saben...
                        //Se guardan los cambios en el Shared preferences
                        MiembroSharedPreferences miembroSharedPreferences = new MiembroSharedPreferences(activity);
                        miembroSharedPreferences.setId(id);
                        miembroSharedPreferences.setNombre(nombre);
                        miembroSharedPreferences.setCorreo(correo);
                        miembroSharedPreferences.setFechaNacimiento(fechaNacimiento);
                        miembroSharedPreferences.setSexo("M");

                        if (facebook != null && facebook.length() > 0) {
                            miembroSharedPreferences.setIdFacebook(facebook);
                            DescargarImagen.guardarImagen(activity, URL_FACEBOOK_IMAGEN_1 + facebook + URL_FACEBOOK_IMAGEN_2, miembroSharedPreferences.getId() + ".jpg");
                        }
                        //se inicia el activity
                        //Lo que se hace cuando el Login se hace de manera correcta
                        Intent intent = new Intent(activity, BuscarActividad.class);
                        activity.startActivity(intent);
                        activity.finish();

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
                params.put("nombre", nombre);
                params.put("sexo", sexo.toUpperCase());
                params.put("correo", correo);

                if (facebook != null && facebook != "") {
                    params.put("facebook", facebook);
                    params.put("fechaNacimiento", fechaNacimiento);
                } else {
                    params.put("pass", password);
                    params.put("fechaNacimiento", fechaNacimiento);
                }

                return params;
            }
        };
        Volley.newRequestQueue(activity).add(postRequest);

        return null;
    }

    @Override
    protected void onPostExecute (Void v) {

        if (bandera) {
            //Lo que se hace cuando el Login se hace de manera correcta
            Intent intent = new Intent(activity, Principal.class);
            activity.startActivity(intent);
            activity.finish();
        } else {
            Toast.makeText(activity, "Parece que hay un error con la conexión, " +
                    "intentalo de nuevo o ponte en contacto con nosotros. " +
                    "admin@antorcha.com.mx",
                    Toast.LENGTH_LONG).show();
        }
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
}
