package nl.marcovp.avans.cavanz.Controller;

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
import nl.marcovp.avans.cavanz.R;
import nl.marcovp.avans.cavanz.Util.MovieAdapter;

public class MainActivity extends AppCompatActivity implements OnMovieSetAvailable {
    private final String TAG = getClass().getSimpleName();
    private TextView mTextMessage;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private SearchView searchView;

    private ArrayList<Movie> movies;

    private boolean searchOn = false;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movies:
//                    mTextMessage.setText(R.string.text_navbar_films);
                    return true;
                case R.id.navigation_tickets:
//                    mTextMessage.setText(R.string.text_navbar_tickets);
                    return true;
                case R.id.navigation_info:
//                    mTextMessage.setText(R.string.text_navbar_info);
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
        searchView.setVisibility(View.INVISIBLE);
        searchOn = false;
        
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        new ApiHelper(this).execute();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        mLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    public void OnMovieSetAvailable(ArrayList<Movie> movies) {
        Log.d(TAG, "OnMovieSetAvailable: called");
        this.movies = movies;


        mAdapter = new MovieAdapter(movies,this);
        mRecyclerView.setAdapter(mAdapter);

  /////////////////////////////DB TEST
       SQLiteHelper db = new SQLiteHelper(this);
        for (Movie mo :movies
                ) {db.insertMovie(mo);
        }
        Log.d(TAG, "OnMovieSetAvailable: found" + db.getAllMovies().size() + "results in db");

    }

    private void TurnSearchBar(){
        if(!searchOn) {
            searchView.setVisibility(View.VISIBLE);
            searchOn = true;
        } else {
            searchView.setVisibility(View.INVISIBLE);
            searchOn = false;
        }
    }
}