package com.mx.antorcha.BaseDatos;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mx.antorcha.Modelos.Deporte;
import com.mx.antorcha.Modelos.Disciplina;
import com.mx.antorcha.Modelos.Medalla;
import com.mx.antorcha.Modelos.Meta;
import com.mx.antorcha.Modelos.MetaProgreso;

/**
 * Created by Ruben on 20/12/2015.
 */
public class ConexionBaseDatosInsertar extends SQLiteOpenHelper {

    public ConexionBaseDatosInsertar(Activity activity) {
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
        //NADA HASTA AHORITA
    }

    //Se insertan datos en la meta
    public long insertarMeta(Meta meta){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues   = new ContentValues();

        contentValues.put("Nombre", meta.getNombre());
        contentValues.put("Inicio", meta.getInicio());
        contentValues.put("Fin", meta.getFin());
        contentValues.put("FechaInicio", meta.getFechaInicio());
        contentValues.put("FechaFin", meta.getFechaFin());
        contentValues.put("TipoMedida", meta.getTipoMedida());

        long id = sqLiteDatabase.insert("Meta", null, contentValues);

        Log.i(Querys.TAG_INSERTAR, "Se ha insertado una meta");

        return id;
    }

    //Se insertan datos en la meta
    public void insertarMetaProgreso(MetaProgreso metaProgreso){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues   = new ContentValues();

        contentValues.put("IdMeta", metaProgreso.getIdMeta());
        contentValues.put("Progreso",metaProgreso.getProgreso());
        contentValues.put("Fecha",metaProgreso.getFecha());
        contentValues.put("IdServidor", 0);

        sqLiteDatabase.insert("MetaProgreso", null, contentValues);

        Log.i(Querys.TAG_INSERTAR, "Se ha insertado una meta progreso");
    }

    //Se insertan las medallas
    public void insertarMedalla (Medalla medalla) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues   = new ContentValues();

        contentValues.put("Id", medalla.getId());
        contentValues.put("Nombre",medalla.getNombre());
        contentValues.put("Tipo", medalla.getTipo());
        contentValues.put("Descripcion", medalla.getDescripcion());
        contentValues.put("ComoSeLogra", medalla.getComoSeLogra());
        contentValues.put("Imagen", medalla.getImagen());

        sqLiteDatabase.insert("Medalla", null, contentValues);

        Log.i(Querys.TAG_INSERTAR, "Se ha insertado una medalla");
    }

    //Se inserta una disciplina
    public void insertarDisciplina (Disciplina disciplina) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues   = new ContentValues();

        contentValues.put("Id", disciplina.getId());
        contentValues.put("Nombre",disciplina.getNombre());
        contentValues.put("Descripcion", disciplina.getDescripcion());

        sqLiteDatabase.insert("Disciplina", null, contentValues);

        Log.i(Querys.TAG_INSERTAR, "Se ha insertado una disciplina");
    }

    //Se inserta un deporte
    public void insertarDeporte (Deporte deporte) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues   = new ContentValues();

        contentValues.put("Id", deporte.getId());
        contentValues.put("Nombre",deporte.getNombre());
        contentValues.put("Disciplina", deporte.getDisciplina());
        contentValues.put("Imagen", deporte.getImagen());

        sqLiteDatabase.insert("Deporte", null, contentValues);

        Log.i(Querys.TAG_INSERTAR, "Se ha insertado un Deporte");
    }
}
