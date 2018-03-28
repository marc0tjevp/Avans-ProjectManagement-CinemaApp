package nl.marcovp.avans.cavanz;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.net.URI;

public class CinemaDetailActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton button;
    private TextView mTextMessage;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_detail);
        button = (ImageButton) findViewById(R.id.button_maps);
        button.setOnClickListener(this);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        
    }

    @Override
    public void onClick(View view) {
        Uri uri = Uri.parse("google.navigation:q=" + Uri.encode("Chasséveld 15, Breda, Nederland"));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW,uri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }
}
