package com.mx.antorcha.Notificaciones;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.mx.antorcha.Activities.BuscarActividad;
import com.mx.antorcha.Modelos.Notificacion;
import com.mx.antorcha.R;

/**
 *
 */
public class ObtenerNotificacion {

    static public PendingIntent obtenerPending (com.mx.antorcha.Modelos.Notificacion notificacion, Context context) {
        switch (notificacion.getAccion()) {
            case "espacio" : {
                Intent intent = new Intent(context, BuscarActividad.class);
                intent.putExtra("id", notificacion.getId());
                intent.putExtra("tipo", "espacio");

                return PendingIntent.getActivity(context, 0, intent, 0);
            }
            case "evento" : {
                Intent intent = new Intent(context, BuscarActividad.class);
                intent.putExtra("id", notificacion.getId());
                intent.putExtra("tipo", "evento");

                return PendingIntent.getActivity(context, 0, intent, 0);
            }
            case "medalla" : {
                Intent intent = new Intent(context, BuscarActividad.class);
                intent.putExtra("id", notificacion.getId());

                return PendingIntent.getActivity(context, 0, intent, 0);
            }
            default : {
                return null;
            }
        }
    }

    static public int obtenerIcono(Notificacion notificacion){

        switch (notificacion.getIcono()) {
            case "medalla" : {
                return R.drawable.antorcha_s;
            }
            default : {
                return R.drawable.icono_agregar;
            }

        }
    }
}
