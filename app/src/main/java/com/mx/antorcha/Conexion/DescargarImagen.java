package com.mx.antorcha.Conexion;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 */
public class DescargarImagen {

    static public String URL = "http://html.rincondelvago.com/";

    //Poner la imagen visible
    static public void cargarImagen (Activity activity, String nombre, final ImageView imageView) {

        Picasso.with(activity).load(URL + nombre).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                // cache is now warmed up
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
    }

    //Se guarda una imagen de una url
    static public void guardarImagen (Activity activity, final String nombre) {


        Picasso.with(activity).load(URL + nombre).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                // Se guarda la imagen
                String root = Environment.getExternalStorageDirectory().toString();
                File directorio = new File(root + "/antorcha");
                directorio.mkdirs();
                File file = new File (directorio, nombre);

                if (file.exists()) {
                    file.delete();
                }
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
    }

    //Se guarda una imagen de una url
    static public void guardarImagen (Activity activity, final String url, final String nombre) {

        Picasso.with(activity).load(url).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                // Se guarda la imagen
                String root = Environment.getExternalStorageDirectory().toString();
                File directorio = new File(root + "/antorcha");
                directorio.mkdirs();
                File file = new File (directorio, nombre);

                if (file.exists()) {
                    file.delete();
                }
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
    }

    static public void imagenGuardada (Activity activity, final String nombre, final ImageView imageView) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/antorcha/" + nombre);
        Bitmap bitmap = null;

        try {
            bitmap = BitmapFactory.decodeStream(new FileInputStream(myDir));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //se muestra
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            //Sino se descarga
            Picasso.with(activity).load(URL + nombre).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    // Se guarda la imagen
                    String root = Environment.getExternalStorageDirectory().toString();
                    File directorio = new File(root + "/antorcha");
                    directorio.mkdirs();
                    File file = new File (directorio, nombre);

                    if (file.exists()) {
                        file.delete();
                    }
                    try {
                        imageView.setImageBitmap(bitmap);
                        FileOutputStream out = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                }
            });
        }
    }
}
