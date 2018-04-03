package nl.marcovp.avans.cavanz.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import nl.marcovp.avans.cavanz.Data.SQLiteHelper;
import nl.marcovp.avans.cavanz.Domain.Movie;
import nl.marcovp.avans.cavanz.Domain.Showing;
import nl.marcovp.avans.cavanz.R;

public class ShowSelectionActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_selection);

        Bundle extras = getIntent().getExtras();
        Movie movie = (Movie) extras.getSerializable("MOVIE");

        ImageView image1 = (ImageView) findViewById(R.id.show_selection_image1);
        ImageView image2 = (ImageView) findViewById(R.id.show_selection_image2);
        ImageView image3 = (ImageView) findViewById(R.id.show_selection_image3);
        ImageView image4 = (ImageView) findViewById(R.id.show_selection_image4);


        LinearLayout showingLL = (LinearLayout) findViewById(R.id.Show_Selection_SelectShowingLL);
        LinearLayout daySelectionLL = (LinearLayout) findViewById(R.id.showSelection_DatesLL);

        SQLiteHelper db = new SQLiteHelper(this);
        ArrayList<Showing> showings = db.getAllShowingsFor(movie.getId());


        HashMap<String, String> dates = new HashMap<>();
        Log.d(TAG, "onCreate: Arraylist size: " + showings.size());


    }
}
