package com.mx.antorcha.GCM;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmListenerService;
import com.mx.antorcha.Notificaciones.Notificacion;

/**
 *
 */
public class ServicioGCM extends GcmListenerService {

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String mensaje = data.getString("message");

        Notificacion.normal(this, Notificacion.obtenerObjeto(mensaje));

    }
}
