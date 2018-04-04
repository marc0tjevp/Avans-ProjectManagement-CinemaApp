package nl.marcovp.avans.cavanz.Util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sander on 3/29/2018.
 */

public class GoogleMapsApi implements OnMapReadyCallback{
    private final String TAG = getClass().getSimpleName();
    private Geocoder geocoder;
    private GoogleMap.OnMarkerClickListener listener;

    public GoogleMapsApi(MapView view, GoogleMap.OnMarkerClickListener listener, Context context) {
        geocoder = new Geocoder(context);
        this.listener = listener;
        view.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i(TAG,"Map ready.");
        List<Address> addresses = new ArrayList<>();
        try {
            addresses = geocoder.getFromLocationName("Chasséveld 15, Breda, Nederland", 1);
        } catch (IOException e){
            Log.e(TAG,e.getLocalizedMessage());
        }
        Address address = addresses.get(0);
        LatLng location = new LatLng(address.getLatitude(),address.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(location)).setTitle("Cavanz Cinema");
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,10f));
        Log.i(TAG,googleMap.getCameraPosition().zoom+"");
        googleMap.setOnMarkerClickListener(listener);
    }
}
