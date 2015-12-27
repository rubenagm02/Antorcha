package com.mx.antorcha.BaseDatos;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 */
public class ConexionBaseDatosActualizar extends SQLiteOpenHelper{
    public ConexionBaseDatosActualizar(Activity activity) {
        super(activity, "Antorcha", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Actualizar id Metas de internet
    public void actualizarMetaIdServidor (int idMeta, int idServidor) {
        SQLiteDatabase db = getWritableDatabase();

        //Establecemos los campos-valores a actualizar
        ContentValues valores = new ContentValues();
        valores.put("IdServidor", idServidor + "");

        //Actualizamos el registro en la base de datos
        db.update("Meta", valores, "Id=" + idMeta, null);
    }
}
