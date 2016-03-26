package com.mx.antorcha.BaseDatos;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 *
 */
public class ConexionBaseDatosEliminar extends SQLiteOpenHelper{

    public ConexionBaseDatosEliminar(Context activity) {
        super(activity, "Antorcha", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void eliminarMeta (int idMeta) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("Meta", "Id=" + idMeta, null);

        Log.i("Eliminar DB", "Se ha eliminado una meta");

    }

    public void eliminarPendiente (int idPendiente) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("Pendiente", "Id=" + idPendiente, null);

        Log.i("Eliminar DB", "Se ha eliminado un pendiente");
    }
}
