package nl.marcovp.avans.cavanz.Controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import nl.marcovp.avans.cavanz.Data.ApiHelper;
import nl.marcovp.avans.cavanz.Data.OnMovieSetAvailable;
import nl.marcovp.avans.cavanz.Data.SQLiteHelper;
import nl.marcovp.avans.cavanz.Domain.Movie;
import nl.marcovp.avans.cavanz.R;

public class MainActivity extends AppCompatActivity implements OnMovieSetAvailable {
    private final String TAG = getClass().getSimpleName();
    private TextView mTextMessage;


    private ArrayList<Movie> movies;

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
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        new ApiHelper(this).execute();


        // Hello World!

    }


    @Override
    public void OnMovieSetAvailable(ArrayList<Movie> movies) {
        this.movies = movies;
        SQLiteHelper db = new SQLiteHelper(this);

        for (Movie mo :movies
                ) {db.insertMovie(mo);

        }


        Log.d(TAG, "OnMovieSetAvailable: found" + db.getAllMovies().size() + "results in db");


    }
}
