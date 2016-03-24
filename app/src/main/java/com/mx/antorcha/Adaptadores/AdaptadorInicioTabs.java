package com.mx.antorcha.Adaptadores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.mx.antorcha.FragmentInicio.FragmentInicio1;
import com.mx.antorcha.FragmentInicio.FragmentInicio2;
import com.mx.antorcha.FragmentInicio.FragmentInicio3;
import com.mx.antorcha.FragmentInicio.FragmentInicio4;

/**
 *
 */
public class AdaptadorInicioTabs  extends FragmentStatePagerAdapter {

    public AdaptadorInicioTabs(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0 :
                return new FragmentInicio1();
            case 1 :
                return new FragmentInicio2();
            case 2 :
                return new FragmentInicio3();
            case 3 :
                return new FragmentInicio4();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
