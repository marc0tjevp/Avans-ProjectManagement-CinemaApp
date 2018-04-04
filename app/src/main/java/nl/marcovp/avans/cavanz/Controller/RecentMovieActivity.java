package nl.marcovp.avans.cavanz.Controller;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nl.marcovp.avans.cavanz.Domain.Movie;
import nl.marcovp.avans.cavanz.R;
import nl.marcovp.avans.cavanz.Util.MovieAdapter;

import java.util.ArrayList;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

public class RecentMovieActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private static ArrayList<Movie> recentMovies = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SearchView searchView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movies:
//
                    goToMainActivity();
                    return true;
                case R.id.navigation_tickets:

                 //  goToRecentMovieActivity();
                    return true;
                case R.id.navigation_info:
                    goToCinemaDetailActivity();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_movie);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MovieAdapter(recentMovies, this);
        mRecyclerView.setAdapter(mAdapter);

        navigation.setSelectedItemId(R.id.navigation_tickets);

        searchView = findViewById(R.id.search_view);
        searchView.setBackgroundColor(Color.BLUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                for(Movie m : recentMovies){
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




        if (recentMovies.size() == 0){
            Toast.makeText(this, "Geen geschiedenis", Toast.LENGTH_LONG).show();
        }




    }

    public static void addRecentMovie(Movie movie){
            boolean add = true;
        for (Movie recentMovie: recentMovies
             ) {
            if (movie.getId() == recentMovie.getId()){
                add = false;
            }
        }
        if (add){
            recentMovies.add(movie);
        }

    }

    private void goToCinemaDetailActivity() {
        Intent intent = new Intent(this, CinemaDetailActivity.class);
        startActivity(intent);
    }

    private void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void goToRecentMovieActivity(){
        Intent intent = new Intent(this, RecentMovieActivity.class);
        startActivity(intent);
    }
}
