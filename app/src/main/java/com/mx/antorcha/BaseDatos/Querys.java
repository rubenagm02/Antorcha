package com.mx.antorcha.BaseDatos;

/**
 *
 */
public class Querys {

    /* TAGS PARA LA BASE DE DATOS */
    static String TAG_INSERTAR = "Insertando dato";

    /* QUERYS DE CREACION DE TABLAS */
    static String CREAR_TABLA_METAS = "CREATE TABLE Meta (Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Nombre TEXT, Inicio DECIMAL, Fin DECIMAL, FechaInicio DATE, FechaFin DATE, TipoMedida TEXT, IdServidor INTEGER)";
    static String CREAR_TABLA_META_PROGRESO = "CREATE TABLE MetaProgreso (IdMeta INTEGER, Progreso DECIMAL, Fecha DATE)";
    static String CREAR_TABLA_MEDALLAS = "CREATE TABLE Medalla (Id INTEGER PRIMARY KEY, Nombre TEXT, Tipo INTEGER, Descripcion TEXT, ComoSeLogra TEXT, Imagen TEXT);";

    /* QUERYS PARA OBTENER DATOS */

    //Metas
    static String OBTENER_METAS = "SELECT * FROM Meta";
    static String OBTENER_METAS_PROGRESO = "SELECT * FROM MetaProgreso WHERE IdMeta = ";
    static String OBTENER_MEDALLAS = "SELECT * FROM Medalla";


}
