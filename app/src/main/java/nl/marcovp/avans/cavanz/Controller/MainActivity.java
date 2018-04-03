package nl.marcovp.avans.cavanz.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import nl.marcovp.avans.cavanz.Data.ApiHelper;
import nl.marcovp.avans.cavanz.Data.OnMovieSetAvailable;
import nl.marcovp.avans.cavanz.Data.SQLiteHelper;
import nl.marcovp.avans.cavanz.Domain.Movie;
import nl.marcovp.avans.cavanz.Domain.Ticket;
import nl.marcovp.avans.cavanz.R;
import nl.marcovp.avans.cavanz.Util.MovieAdapter;

public class MainActivity extends AppCompatActivity implements OnMovieSetAvailable {
    private final String TAG = getClass().getSimpleName();
    private TextView mTextMessage;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private SearchView searchView;


    private boolean searchOn = false;


    private ArrayList<Movie> movies;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movies:
//                    mTextMessage.setText(R.string.text_navbar_films);

                    return true;
                case R.id.navigation_tickets:

                    goToTicketActivity();
                    return true;
                case R.id.navigation_info:
                    goToCinemaDetailActivity();
                    return true;
                case R.id.navigation_search:
                    TurnSearchBar();
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: called");

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                for(Movie m : movies){
                    if(m.getTitle().toLowerCase().contains(s.toLowerCase())){
                        System.out.println("Found: " + m.getTitle().toString());
                        Intent detailIntent = new Intent(searchView.getContext().getApplicationContext(),MovieDetailActivity.class);
                        detailIntent.putExtra("MOVIE", m);
                        startActivity(detailIntent);
                        break;
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        new ApiHelper(this).execute();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);


        // Debug
        Intent i = new Intent(this, PaymentActivity.class);
        startActivity(i);

    }

    @Override
    public void OnMovieSetAvailable(ArrayList<Movie> movies) {
        Log.d(TAG, "OnMovieSetAvailable: called");
        this.movies = movies;


        mAdapter = new MovieAdapter(movies, this);
        mRecyclerView.setAdapter(mAdapter);


        /////////////////////////////DB TEST
        SQLiteHelper db = new SQLiteHelper(this);


        if (db.getAllMovies().size() == 0) {

            for (Movie mo : movies
                    ) {
                db.insertMovie(mo);
            }

            Log.d(TAG, "OnMovieSetAvailable: creating test data");
            db.createTestData();
            Log.d(TAG, "OnMovieSetAvailable: found" + db.getAllMovies().size() + "results in db");
        } else {
            Log.d(TAG, "OnMovieSetAvailable: data found (Not creating test data)");
        }

        ////////////////EXCLUDE IN PRODUCTION
        Log.d(TAG, "OnMovieSetAvailable: showings planned: " + db.getAllShowings().size());
        Log.d(TAG, "OnMovieSetAvailable: movies in db: " + db.getAllMovies().size());
        Log.d(TAG, "OnMovieSetAvailable: found" + db.getAllMovies().size() + "results in db");


    }

    private void TurnSearchBar() {
        if (!searchOn) {
            searchView.setVisibility(View.VISIBLE);
            searchOn = true;
        } else {
            searchView.setVisibility(View.INVISIBLE);
            searchOn = false;
        }
    }

    private void goToTicketActivity() {
        Intent intent = new Intent(this, TicketActivity.class);
        startActivity(intent);
    }

    private void goToCinemaDetailActivity() {
        Intent intent = new Intent(this, CinemaDetailActivity.class);
        startActivity(intent);
    }
}
