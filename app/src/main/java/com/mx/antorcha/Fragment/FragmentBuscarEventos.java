package com.mx.antorcha.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.Conexion.ConexionBuscarEvento;
import com.mx.antorcha.Dialogos.DialogoMostrarFiltroEspacio;
import com.mx.antorcha.Modelos.EspacioDeportivo;
import com.mx.antorcha.Modelos.Evento;
import com.mx.antorcha.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;

/**
 *
 */
public class FragmentBuscarEventos extends Fragment  implements GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener{

    private Activity activity;
    GoogleMap mMap;
    MapView mapView;
    private ArrayList<Evento> eventos;
    LinearLayout linearLayoutCentral;
    private FragmentManager fragmentManager;
    SlidingUpPanelLayout slidingUpPanelLayout;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        eventos = new ArrayList<>();
        final View rootView = inflater.inflate(R.layout.fragment_buscar_evento, container, false);

        //
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        float dp = 100f;
        float fpixels = metrics.density * dp;
        final int pixels = (int) (fpixels + 0.5f);

        slidingUpPanelLayout = (SlidingUpPanelLayout) rootView.findViewById(R.id.buscar_evento_sliding_layout);

        //Se cargan las imagenes de los icono de contacto
        ImageView imageViewCompartir = (ImageView) rootView.findViewById(R.id.sliding_buscar_actividades_evento_compartir);
        ImageView imageViewContacto = (ImageView) rootView.findViewById(R.id.sliding_buscar_actividades_evento_contacto);
        ImageView imageViewMarkerCentral = (ImageView) rootView.findViewById(R.id.buscar_evento_marker_central);

        AdaptadorSVG.mostrarImagen(imageViewCompartir, activity, R.raw.icono_compartir);
        AdaptadorSVG.mostrarImagen(imageViewContacto, activity, R.raw.icono_llamada);
        AdaptadorSVG.mostrarImagen(imageViewMarkerCentral, activity, R.raw.icono_marker_naranja);

        //Inicializar el mapa
        mapView = (MapView) rootView.findViewById(R.id.map_fragment_evento);
        mapView.onCreate(savedInstanceState);
        MapsInitializer.initialize(activity);
        mMap = mapView.getMap();

        if (mMap != null) {

            CameraUpdate center=
                    CameraUpdateFactory.newLatLng(new LatLng(20.699359689441785,
                            -103.29570472240448));
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(14f);

            mMap.moveCamera(center);
            mMap.animateCamera(zoom);
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15f));
            mMap.setMyLocationEnabled(true);

            //Onclick del mapa
            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    slidingUpPanelLayout.setPanelHeight(0);
                    linearLayoutCentral.setVisibility(View.VISIBLE);
                }
            });

            //Onclick del Marker
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Evento evento = obtenerEvento(marker.getTitle());

                    if (evento != null) {
                        mostrarEvento(evento, rootView);
                    }

                    slidingUpPanelLayout.setPanelHeight(pixels);
                    linearLayoutCentral.setVisibility(View.INVISIBLE);
                    return false;
                }
            });

            //El on click de layout
            linearLayoutCentral = (LinearLayout) rootView.findViewById(R.id.buscar_evento_layout_click_filtro);
            linearLayoutCentral.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogoMostrarFiltroEspacio dialogoMostrarFiltroEspacio = new DialogoMostrarFiltroEspacio();
                    dialogoMostrarFiltroEspacio.show(fragmentManager, "filtro_espacio");
                }
            });

            //Se buscan los eventos
            ConexionBuscarEvento conexionBuscarEvento = new ConexionBuscarEvento(activity, mMap);
            conexionBuscarEvento.setEventos(eventos);
            conexionBuscarEvento.execute();
        }

        return rootView;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    //Onclick del Marcador
    @Override
    public boolean onMarkerClick(Marker marker){
        marker.showInfoWindow();
        return true;
    }
    @Override
    public void onMarkerDrag(Marker marker){

    }
    @Override
    public void onMarkerDragEnd(Marker marker){
        //Actualiza la informacion del Marcador
        marker.showInfoWindow();
    }
    @Override
    public void onMarkerDragStart(Marker marker){

    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override
    public void onPause(){
        super.onPause();
        mapView.onPause();
    }

    class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View v;
        LinearLayout linearLayout;
        public MyInfoWindowAdapter(Activity activity, LinearLayout linearLayout) {
            v = activity.getLayoutInflater().inflate(R.layout.etiqueta_marker,
                    null);
            this.linearLayout = linearLayout;

        }

        @Override
        public View getInfoContents(Marker marker) {
            // set some bitmap to the imageview
            linearLayout.setVisibility(View.VISIBLE);
            return v;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }

    }
    public Evento obtenerEvento (String titulo) {

        for (Evento evento : eventos) {

            if (evento.getNombre().equals(titulo)) {

                return evento;
            }
        }

        return null;
    }

    public void mostrarEvento(Evento evento, View view){
        TextView textViewNombre = (TextView) view.findViewById(R.id.sliding_buscar_evento_nombre_principal);
        TextView textViewFecha = (TextView) view.findViewById(R.id.sliding_buscar_evento_fecha_evento);
        TextView textViewCantidadPersonas = (TextView) view.findViewById(R.id.sliding_buscar_evento_cantidad_personas);
        TextView textViewLugar = (TextView) view.findViewById(R.id.sliding_buscar_evento_lugar);
        TextView textViewDescripcion = (TextView) view.findViewById(R.id.sliding_buscar_evento_descripcion);

        //Se muestra la información;
        textViewNombre.setText(evento.getNombre());
        textViewFecha.setText(evento.getFechaInicio());
        textViewCantidadPersonas.setText(10 + "");
        textViewLugar.setText(evento.getDomicilio());
        textViewDescripcion.setText(evento.getDescripcion());

    }
}
