package nl.marcovp.avans.cavanz.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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

    }

    @Override
    public void onClick(View view) {

    }
}
