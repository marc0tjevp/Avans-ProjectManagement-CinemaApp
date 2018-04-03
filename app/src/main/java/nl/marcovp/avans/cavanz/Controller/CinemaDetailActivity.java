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

import java.net.URI;

import nl.marcovp.avans.cavanz.R;

public class CinemaDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();

    ImageButton button;
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
                    goToTicketActivity();
                    return true;
                case R.id.navigation_info:
                    //     mTextMessage.setText(R.string.text_navbar_info);
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
        button = (ImageButton) findViewById(R.id.button_maps);
        button.setOnClickListener(this);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_info);
        findViewById(R.id.navigation_search).setEnabled(false);

    }

    @Override
    public void onClick(View view) {
        Uri uri = Uri.parse("google.navigation:q=" + Uri.encode("Chasséveld 15, Breda, Nederland"));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goToTicketActivity() {
        Intent intent = new Intent(this, TicketActivity.class);
        startActivity(intent);
    }
}
