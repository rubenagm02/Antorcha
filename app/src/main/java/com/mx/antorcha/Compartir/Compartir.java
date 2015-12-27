package com.mx.antorcha.Compartir;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Ruben on 23/12/2015.
 */
public class Compartir {

    public Compartir (Activity activity) {
        this.activity = activity;
        sendIntent = new Intent(Intent.ACTION_SEND);
    }

    private final String URL = "antorcha.com.mx";
    private Intent sendIntent;
    private Activity activity;

    public void agregarUrl () {
        sendIntent.putExtra(Intent.EXTRA_TEXT, URL);
    }

    public void agregarTexto (String texto) {
        sendIntent.putExtra(Intent.EXTRA_TEXT, texto);
    }

    public void agregarView (View view) {
        Bitmap bitmap = viewABitmap(view);

        //se guarda la imagen
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/antorcha");
        myDir.mkdirs();
        String fname = "compartirView.jpg";
        File file = new File (myDir, fname);

        if (file.exists ()) {
            file.delete ();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Uri imageUri = Uri.parse(Environment.getExternalStorageDirectory().toString()
                + "/antorcha/compartirView.jpg");

        sendIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
    }

    public void agregarImagen (Bitmap bitmap) {

        //se guarda la imagen
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/antorcha");
        myDir.mkdirs();
        String fname = "compartir.jpg";
        File file = new File (myDir, fname);

        if (file.exists ()) {
            file.delete ();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Uri imageUri = Uri.parse(Environment.getExternalStorageDirectory().toString()
                + "/antorcha/compartir.jpg");

        sendIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
    }

    public boolean compartir(){
        sendIntent.setType("image/*");
        activity.startActivity(Intent.createChooser(sendIntent, "Email:"));

        return true;
    }

    public static Bitmap viewABitmap(View v) {

        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache();
        Bitmap bm = v.getDrawingCache();
        return bm;
    }
}
