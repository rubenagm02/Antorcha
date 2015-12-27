package com.mx.antorcha.Modelos;

/**
 * Created by Ruben on 21/12/2015.
 */
public class MetaProgreso {

    private int idMeta;
    private double progreso;
    private String fecha;

    public MetaProgreso(int idMeta, double progreso, String fecha) {
        this.idMeta = idMeta;
        this.progreso = progreso;
        this.fecha = fecha;
    }

    public MetaProgreso () {

    }

    public int getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(int idMeta) {
        this.idMeta = idMeta;
    }

    public double getProgreso() {
        return progreso;
    }

    public void setProgreso(double progreso) {
        this.progreso = progreso;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
