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
        db.execSQL(Querys.CREAR_TABLA_METAS);
        db.execSQL(Querys.CREAR_TABLA_META_PROGRESO);
        db.execSQL(Querys.CREAR_TABLA_MEDALLAS);
        db.execSQL(Querys.CREAR_TABLA_DEPORTES);
        db.execSQL(Querys.CREAR_TABLA_DISCIPLINAS);
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

    //Actualizar id MetasProgreso
    public void actualizarMetaProgreso (int IdMetaProgreso, int idServidor) {
        SQLiteDatabase db = getWritableDatabase();

        //Establecemos los campos-valores a actualizar
        ContentValues valores = new ContentValues();
        valores.put("IdServidor", idServidor + "");

        //Actualizamos el registro en la base de datos
        db.update("MetaProgreso", valores, "Id=" + IdMetaProgreso, null);
    }
}
