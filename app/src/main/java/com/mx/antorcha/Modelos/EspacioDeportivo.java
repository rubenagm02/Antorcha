package com.mx.antorcha.Modelos;

/**
 * Created by Ruben on 19/12/2015.
 */
public class EspacioDeportivo {

    private int id;
    private String nombre;


    public EspacioDeportivo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public EspacioDeportivo () {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
