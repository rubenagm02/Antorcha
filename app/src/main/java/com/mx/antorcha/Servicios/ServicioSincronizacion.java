package com.mx.antorcha.Servicios;

import android.app.IntentService;
import android.content.Intent;

import com.mx.antorcha.BaseDatos.ConexionBaseDatosObtener;
import com.mx.antorcha.Conexion.ConexionMetas;
import com.mx.antorcha.Modelos.Meta;
import com.mx.antorcha.Modelos.Pendiente;
import com.mx.antorcha.SharedPreferences.MiembroSharedPreferences;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by root on 25/03/16.
 */
public class ServicioSincronizacion extends IntentService {

    public ServicioSincronizacion() {
        super("ServicioSinconizacion");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            synchronized ("ServicioSincronizacion") {
                ConexionBaseDatosObtener conexionBaseDatosObtener = new ConexionBaseDatosObtener(this);
                ArrayList<Pendiente> pendientes = conexionBaseDatosObtener.obtenerPendientes();

                for (Pendiente pendiente : pendientes) {

                    switch (pendiente.getTipo()) {
                        case Pendiente.META : {

                            //Se obtienen los datos de la meta
                            Meta meta = new Meta();
                            StringTokenizer stringTokenizer = new StringTokenizer(pendiente.getDatos(), Pendiente.SEP);
                            meta.setId(Integer.parseInt(stringTokenizer.nextToken()));
                            meta.setInicio(Double.parseDouble(stringTokenizer.nextToken()));
                            meta.setFin(Double.parseDouble(stringTokenizer.nextToken()));
                            meta.setNombre(stringTokenizer.nextToken());
                            meta.setFechaFin(stringTokenizer.nextToken());
                            meta.setFechaInicio(stringTokenizer.nextToken());
                            meta.setTipoMedida(stringTokenizer.nextToken());

                            ConexionMetas conexionMetas = new ConexionMetas(this, meta, pendiente.getId());
                            conexionMetas.setId(new MiembroSharedPreferences(this).getId());
                            conexionMetas.insertarMetas();
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
