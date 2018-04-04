package nl.marcovp.avans.cavanz.Controller;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nl.marcovp.avans.cavanz.Domain.TicketType;
import nl.marcovp.avans.cavanz.R;
import nl.marcovp.avans.cavanz.Util.TicketTypeAdapter;

//new constructor, can be replaced by the next line
public class CinemaDetailActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener {
    private final String TAG = getClass().getSimpleName();


    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movies:
                    goToMainActivity();

                    return true;
                case R.id.navigation_tickets:
                    goToRecentMovieActivity();
                    return true;
                case R.id.navigation_info:

                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_detail);
        Log.d(TAG, "onCreate: aangeroepen");

        ListView listView = findViewById(R.id.cinemaDetailTarifList);
        final ArrayList<TicketType> types = new ArrayList<>();
        for (TicketType type : TicketType.values()) {
            types.add(type);
            Log.i(TAG, type.getTicketTypeName() + " added to Ticket Type list");
        }
        TicketTypeAdapter adapter = new TicketTypeAdapter(this, types);
        listView.setAdapter(adapter);

        ImageView imageView = findViewById(R.id.image_cinema);
        Picasso.with(this).load("http://icons.iconarchive.com/icons/blackvariant/button-ui-requests-2/256/PopcornTime-icon.png").into(imageView);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_info);

        final MapView mapView = findViewById(R.id.mapView);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {

                Geocoder geocoder = new Geocoder(getApplicationContext());
                List<Address> addresses = new ArrayList<>();

                try {
                    addresses = geocoder.getFromLocationName("Chasséveld 15, Breda, Nederland", 1);
                } catch (IOException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                }

            //    Address address = addresses.get(0);

                LatLng location = new LatLng(51.588969, 4.785808);
                googleMap.addMarker(new MarkerOptions().position(location)).setTitle("Cavanz Cinema");
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 14f));

                mapView.onResume();
            }
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goToRecentMovieActivity() {
        Intent intent = new Intent(this, RecentMovieActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Uri uri = Uri.parse("google.navigation:q=" + Uri.encode("Chasséveld 15, Breda, Nederland"));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
            //Add next 3 lines too
            return true;
        } else {
            return false;
        }
    }
}
