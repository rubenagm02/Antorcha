package com.mx.antorcha.Adaptadores;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mx.antorcha.Fragment.FragmentBuscarEspacio;
import com.mx.antorcha.Fragment.FragmentBuscarEventos;

/**
 * Created by Ruben on 15/12/2015.
 */
public class AdaptadorBuscarActividadTabs extends FragmentStatePagerAdapter {

    private Activity activity;
    private FragmentManager fragmentManager;

    public AdaptadorBuscarActividadTabs(FragmentManager fm, Activity activity) {
        super(fm);
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0 :
                FragmentBuscarEspacio fragmentBuscarEspacio = new FragmentBuscarEspacio();
                fragmentBuscarEspacio.setActivity(activity);
                fragmentBuscarEspacio.setFragmentManager(fragmentManager);
                return fragmentBuscarEspacio;
            case 1 :
                FragmentBuscarEventos fragmentBuscarEventos = new FragmentBuscarEventos();
                fragmentBuscarEventos.setActivity(activity);
                fragmentBuscarEventos.setFragmentManager(fragmentManager);
                return fragmentBuscarEventos;
            default:

                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Espacios deportivos";
            case 1:
                return "Eventos";
            default:
                return "Tab";
        }
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }
}
