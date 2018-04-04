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

//new constructor, can be replaced by the next line
public class CinemaDetailActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener {
    private final String TAG = getClass().getSimpleName();

    private TextView mTextMessage;
    //"ImageButton imagebutton" has been replaced with the next line
    private GoogleMapsApi mapsApi;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movies:
                    goToMainActivity();

                    return true;
                case R.id.navigation_tickets:
                    goToTicketActivity();
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


        MapView mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapsApi = new GoogleMapsApi(mapView,this,this);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_info);

    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goToTicketActivity() {
        Intent intent = new Intent(this, TicketActivity.class);
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
        return true;
    }
}
