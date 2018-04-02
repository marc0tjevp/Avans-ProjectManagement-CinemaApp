package nl.marcovp.avans.cavanz.Controller;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.Marker;

import java.net.URI;

import nl.marcovp.avans.cavanz.R;
import nl.marcovp.avans.cavanz.Util.GoogleMapsApi;

public class CinemaDetailActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener{
    private final String TAG = getClass().getSimpleName();

    ImageButton button;
    private TextView mTextMessage;
    private GoogleMapsApi mapsApi;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movies:
                    mTextMessage.setText(R.string.text_navbar_films);
                    return true;
                case R.id.navigation_tickets:
                    mTextMessage.setText(R.string.text_navbar_tickets);
                    return true;
                case R.id.navigation_info:
                    mTextMessage.setText(R.string.text_navbar_info);
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: aangeroepen");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_detail);
        MapView mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapsApi = new GoogleMapsApi(mapView,this,this);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Uri uri = Uri.parse("google.navigation:q=" + Uri.encode("Chasséveld 15, Breda, Nederland"));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
            return true;
        } else {
            return false;
        }
    }
}
