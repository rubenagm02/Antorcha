package com.mx.antorcha.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.mx.antorcha.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        EsperaSplashScreen esperaSplashScreen = new EsperaSplashScreen(this);
        esperaSplashScreen.execute();
    }

    class EsperaSplashScreen extends AsyncTask<Void, Void, Void> {

        private Activity activity;

        public EsperaSplashScreen (Activity activity) {
            this.activity = activity;
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v){
            Intent intent = new Intent(activity, Inicio.class);
            activity.startActivity(intent);
            activity.finish();
        }
    }

}