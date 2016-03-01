package com.mx.antorcha.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.BaseDatos.ConexionBaseDatosInsertar;
import com.mx.antorcha.BaseDatos.ConexionBaseDatosObtener;
import com.mx.antorcha.Conexion.ConexionBuscarEspacio;
import com.mx.antorcha.Conexion.ConexionInformacionEspacio;
import com.mx.antorcha.Dialogos.DialogoInsertarResenia;
import com.mx.antorcha.Dialogos.DialogoMostrarFiltroEspacio;
import com.mx.antorcha.Modelos.EspacioDeportivo;
import com.mx.antorcha.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;

/**
 *
 */
public class FragmentBuscarEspacio extends Fragment implements GoogleMap.OnMarkerDragListener {

    private Activity activity;
    GoogleMap mMap;
    private View view;
    private Bundle bundle;
    private ImageView imageViewAsignarmeEspacio;
    MapView mapView;
    public LinearLayout linearLayoutSliding;
    LinearLayout linearLayoutCentral;
    private FragmentManager fragmentManager;
    private ConexionBuscarEspacio conexionBuscarEspacio;
    public ArrayList<EspacioDeportivo> espacioDeportivos;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_buscar_espacio, container, false);
        view = rootView;
        espacioDeportivos = new ArrayList<>();
        //
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        float dp = 100f;
        float fpixels = metrics.density * dp;
        final int pixels = (int) (fpixels + 0.5f);

        //se inicializa el Linear del sliding
        linearLayoutSliding = (LinearLayout) rootView.findViewById(R.id.dragView);

        //Se cargan las imagenes de los icono de contacto
        ImageView imageViewCompartir = (ImageView) rootView.findViewById(R.id.sliding_buscar_actividades_espacio_compartir);
        ImageView imageViewContacto = (ImageView) rootView.findViewById(R.id.sliding_buscar_actividades_espacio_contacto);
        ImageView imageViewLapiz = (ImageView) rootView.findViewById(R.id.sliding_buscar_actividades_espacio_lapiz);
        ImageView imageViewMarkerCentral = (ImageView) rootView.findViewById(R.id.buscar_espacio_marker_central);

        final SlidingUpPanelLayout slidingUpPanelLayout = (SlidingUpPanelLayout) rootView.findViewById(R.id.buscar_espacio_sliding_layout);
        slidingUpPanelLayout.setPanelHeight(0);
        //Inicializar el mapa
        mapView = (MapView) rootView.findViewById(R.id.map_fragment_espacio);
        mapView.onCreate(savedInstanceState);
        MapsInitializer.initialize(getContext());

        //Se cargan las imagenes en vectores
        AdaptadorSVG.mostrarImagen(imageViewCompartir, activity, R.raw.icono_compartir);
        AdaptadorSVG.mostrarImagen(imageViewContacto, activity, R.raw.icono_llamada);
        AdaptadorSVG.mostrarImagen(imageViewLapiz, activity, R.raw.icono_lapiz);
        AdaptadorSVG.mostrarImagen(imageViewMarkerCentral, activity, R.raw.icono_marker_naranja);
        mMap = mapView.getMap();

        if (mMap != null) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mMap.animateCamera(CameraUpdateFactory.zoomTo(2));
            mMap.setMyLocationEnabled(true);

            //Onclick del Marker
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    slidingUpPanelLayout.setPanelHeight(pixels);
                    linearLayoutCentral.setVisibility(View.INVISIBLE);

                    //Cuando se da click se busca el espacio
                    EspacioDeportivo espacioDeportivo = obtenerEspacio(marker.getTitle());

                    if (espacioDeportivo != null) {
                        mostrarInformacionEspacio(espacioDeportivo, rootView);
                    }

                    return false;
                }
            });

            //Onclick del mapa
            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    slidingUpPanelLayout.setPanelHeight(0);
                    linearLayoutCentral.setVisibility(View.VISIBLE);
                }
            });

            mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(CameraPosition cameraPosition) {
                    Log.i("Google", cameraPosition.target.toString() +  "///" + cameraPosition.zoom);
                }
            });

            mMap.setInfoWindowAdapter(new MyInfoWindowAdapter(activity, linearLayoutSliding));

            //marker de prueba
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(20.699359689441785,-103.29570472240448))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            //El on click de layout
            linearLayoutCentral = (LinearLayout) rootView.findViewById(R.id.buscar_espacio_layout_click_filtro);
            linearLayoutCentral.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogoMostrarFiltroEspacio dialogoMostrarFiltroEspacio = new DialogoMostrarFiltroEspacio();
                    dialogoMostrarFiltroEspacio.show(fragmentManager, "filtro_espacio");
                }
            });
            ConexionBuscarEspacio conexionBuscarEspacio = new ConexionBuscarEspacio(activity, mMap);
            conexionBuscarEspacio.setEspacioDeportivos(espacioDeportivos);
            conexionBuscarEspacio.execute();

            //Se carga la imagen del boton de asignarme espacio
            imageViewAsignarmeEspacio = (ImageView) rootView.findViewById(R.id.buscar_espacio_boton_asignarme_espacio);
            AdaptadorSVG.mostrarImagen(imageViewAsignarmeEspacio, activity, R.raw.boton_asisto_centro_deportivo);
        }

        if (bundle != null && bundle.containsKey("idEspacio")) {
            mostrarEspacio(bundle.getInt("idEspacio"), rootView);
            slidingUpPanelLayout.setPanelHeight(pixels);
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        }

        return rootView;
    }
    public void setActivity(Activity activity) {
        this.activity = activity;
    }



    @Override
    public void onMarkerDrag(Marker marker){

    }
    @Override
    public void onMarkerDragEnd(Marker marker){
        //Actualiza la informacion del Marcador
    }
    @Override
    public void onMarkerDragStart(Marker marker){

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

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
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

            TextView textView = (TextView) v.findViewById(R.id.etiqueta_marquer_nombre);
            textView.setText(marker.getTitle());
            return v;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }
    }

    public EspacioDeportivo obtenerEspacio (String titulo) {

        for (EspacioDeportivo espacioDeportivo : espacioDeportivos) {

            if (espacioDeportivo.getNombre().equals(titulo)) {

                return espacioDeportivo;
            }
        }

        return null;
    }

    public void mostrarInformacionEspacio(final EspacioDeportivo espacioDeportivo, View view){
        TextView textViewNombre = (TextView) view.findViewById(R.id.sliding_buscar_actividades_espacio_nombre);
        TextView textViewDescripciom = (TextView) view.findViewById(R.id.sliding_buscar_actividades_espacio_descripcion);
        ImageView imageViewInsertarResenia = (ImageView) view.findViewById(R.id.sliding_buscar_actividades_espacio_lapiz);
        TextView textViewValoracion = (TextView) view.findViewById(R.id.sliding_buscar_espacio_valoracion);
        TextView textViewHorario = (TextView) view.findViewById(R.id.sliding_buscar_espacio_horario);

        String descripcion = "";

        if (espacioDeportivo.getDescripcion().length() > 40) {
            descripcion = espacioDeportivo.getDescripcion().substring(0, 37) + "...";
        } else {
            descripcion = espacioDeportivo.getDescripcion();
        }

        textViewValoracion.setText((espacioDeportivo.getValoracion() + "").substring(0, 3));
        textViewDescripciom.setText(descripcion);
        textViewNombre.setText(espacioDeportivo.getNombre());
        textViewHorario.setText(espacioDeportivo.getHorario());

        //Se coloca el click para guardar una reseña
        imageViewInsertarResenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoInsertarResenia dialogoInsertarResenia = new DialogoInsertarResenia();
                dialogoInsertarResenia.setActivity(activity);
                dialogoInsertarResenia.setId(espacioDeportivo.getId());
                dialogoInsertarResenia.setTipo(1);
                dialogoInsertarResenia.show(fragmentManager, "dialogo_insertar_resenia");
            }
        });

        //Se manda el view para cargar todos los datos desde el hilo
        imageViewAsignarmeEspacio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConexionBaseDatosInsertar(activity).insertarEspacioDeportivo(espacioDeportivo);
                Toast.makeText(activity, "Se ha asignado este espacio a tus actividades", Toast.LENGTH_LONG).show();
            }
        });

        ConexionInformacionEspacio conexionInformacionEspacio = new ConexionInformacionEspacio(activity, espacioDeportivo.getId() + "");
        conexionInformacionEspacio.setView(view);
        conexionInformacionEspacio.obtenerEspacio();
    }

    public void mostrarEspacio (int idEspacio, View view) {

        ConexionBaseDatosObtener conexionBaseDatosObtener = new ConexionBaseDatosObtener(activity);

        EspacioDeportivo espacioDeportivo = conexionBaseDatosObtener.obtenerUnEspacio(idEspacio);

        if (espacioDeportivo != null) {
            mostrarInformacionEspacio(espacioDeportivo, view);

        }
    }
}
