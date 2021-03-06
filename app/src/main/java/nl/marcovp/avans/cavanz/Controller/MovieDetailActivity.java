package nl.marcovp.avans.cavanz.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import nl.marcovp.avans.cavanz.Domain.Movie;
import nl.marcovp.avans.cavanz.R;


/**
 * Created by Djim on 28-3-2018.
 */

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Button orderButton = (Button) findViewById(R.id.Movie_Detail_OrderNow);

        ImageView poster = findViewById(R.id.iv_movie_detail_image);
        TextView title = findViewById(R.id.tv_movie_detail_title);
//        TextView genre = findViewById(R.id.tv_movie_detail_genre);
        TextView releaseDate = findViewById(R.id.tv_movie_detail_releasedate);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView movieDuration = findViewById(R.id.tv_movie_detail_time);
        TextView movieDescription = findViewById(R.id.tv_movie_detail_description);
        TextView language = findViewById(R.id.tv_movie_detail_language_select);
//        TextView actors = findViewById(R.id.tv_movie_detail_actors);


        Bundle extras = getIntent().getExtras();
        final Movie movie = (Movie) extras.getSerializable("MOVIE");
        RecentMovieActivity.addRecentMovie(movie);
        //Turn double rating to int rating
        Double dRating = movie.getRating();
        Integer iRating = dRating.intValue();

        //Random movie duration (NO API SUPPORT)
        String placeholderDuration = " 150 minutes";

        Picasso.with(this).load(movie.getImageUrl()).into(poster);
        title.setText(movie.getTitle());
        releaseDate.setText(movie.getReleaseDate());
        movieDescription.setText(movie.getSummary());
        language.setText(movie.getLanguage());
        ratingBar.setProgress(iRating);
        movieDuration.setText(placeholderDuration);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShowSelectionActivity.class);

                intent.putExtra("MOVIE", movie);
                startActivity(intent);


            }
        });


    }

    @Override
    public void onClick(View view) {

    }
}