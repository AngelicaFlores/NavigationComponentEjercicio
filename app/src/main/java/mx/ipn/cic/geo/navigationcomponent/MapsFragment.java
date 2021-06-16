package mx.ipn.cic.geo.navigationcomponent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import mx.ipn.cic.geo.navigationcomponent.databinding.FragmentMapsBinding;

public class MapsFragment extends Fragment {
    private FragmentMapsBinding binding;
    GoogleMap google_map;
    //ESTE ES EL BUENO
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng CDMX = new LatLng(19.432688, -99.133209);
            googleMap.addMarker(new MarkerOptions().position(CDMX).title("CDMX"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(CDMX));
            google_map = googleMap;
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_maps, container, false);
        binding = FragmentMapsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
        binding.sitio1.setOnClickListener(v ->
                cambiarSitio(-41.087921, -71.276796,"San Carlos de Bariloche, Argentina" ));
        binding.sitio2.setOnClickListener(v ->
                cambiarSitio(40.4167278, -3.7033387,"Puerta del Sol, España" ));
        binding.sitio3.setOnClickListener(v ->
                cambiarSitio(41.899130000, 12.473200000,"Plaza Novva, Italia" ));
        binding.sitio4.setOnClickListener(v ->
                cambiarSitio(55.75222 , 37.61556,"Plaza Roja, Moscú" ));
    }

    public void cambiarSitio(double latitud, double longitud, String nombre){
        LatLng sitio = new LatLng(latitud, longitud);
        google_map.clear();
        google_map.addMarker(new MarkerOptions().position(sitio).title(nombre));
        google_map.moveCamera(CameraUpdateFactory.newLatLng(sitio));
    }
}