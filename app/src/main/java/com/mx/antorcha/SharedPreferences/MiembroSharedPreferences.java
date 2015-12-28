package com.mx.antorcha.SharedPreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 */
public class MiembroSharedPreferences {

    private String NOMBRE_SP = "";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String LLAVE_NOMBRE = "llave_nombre";
    private String LLAVE_SEXO = "llave_sexo";
    private String LLAVE_ID = "llave_id";
    private String LLAVE_EDAD = "llave_edad";
    private String LLAVE_ID_FACEBOOK = "llave_facebook";
    private String LLAVE_ID_GOOGLE = "llave_google";
    private String LLAVE_CORREO = "llave_correo";
    private String DEFAULT = "default";
    private String LLAVE_FECHA_NACIMIENTO = "fecha_nacimiento";

    public MiembroSharedPreferences(Activity activity) {
        sharedPreferences = activity.getSharedPreferences(NOMBRE_SP, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setNombre (String nombre) {
        editor.putString(LLAVE_NOMBRE, nombre);
        editor.apply();
    }

    public String getNombre () {
        return sharedPreferences.getString(LLAVE_NOMBRE, DEFAULT);
    }

    public void setSexo (String sexo) {
        editor.putString(LLAVE_SEXO, sexo);
        editor.apply();
    }

    public String getSexo () {
        return sharedPreferences.getString(LLAVE_SEXO, DEFAULT);
    }

    public void setId (int id) {
        editor.putInt(LLAVE_ID, id);
        editor.apply();
    }

    public int getId () {
        return sharedPreferences.getInt(LLAVE_ID, 0);
    }

    public void setIdGoogle (String google) {
        editor.putString(LLAVE_ID_GOOGLE, google);
        editor.apply();
    }

    public String getIdGoogle () {
        return  sharedPreferences.getString(LLAVE_ID_GOOGLE, DEFAULT);
    }

    public void setEdad (String edad) {
        editor.putString(LLAVE_EDAD, edad);
        editor.apply();
    }

    public String getEdad () {
        return sharedPreferences.getString(LLAVE_EDAD, DEFAULT);
    }

    public void setIdFacebook (String facebook) {
        editor.putString(LLAVE_ID_FACEBOOK, facebook);
        editor.apply();
    }

    public String getIdFacebook () {
        return sharedPreferences.getString(LLAVE_ID_FACEBOOK, DEFAULT);
    }

    public void setFechaNacimiento (String fechaNacimiento) {
        editor.putString(LLAVE_FECHA_NACIMIENTO, fechaNacimiento);
        editor.apply();
    }

    public String getFechaNacimiento () {
        return sharedPreferences.getString(LLAVE_FECHA_NACIMIENTO, DEFAULT);
    }

    public void setCorreo (String correo) {
        editor.putString(LLAVE_CORREO, correo);
        editor.apply();
    }

    public String getCorreo () {
        return sharedPreferences.getString(LLAVE_CORREO, DEFAULT);
    }
}
