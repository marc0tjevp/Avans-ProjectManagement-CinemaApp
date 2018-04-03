package nl.marcovp.avans.cavanz.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import nl.marcovp.avans.cavanz.Data.OnDataSetAvail;
import nl.marcovp.avans.cavanz.Data.SQLiteHelper;
import nl.marcovp.avans.cavanz.Domain.Movie;
import nl.marcovp.avans.cavanz.Domain.Showing;
import nl.marcovp.avans.cavanz.R;
import nl.marcovp.avans.cavanz.Util.GetDataSetAsync;

public class ShowSelectionActivity extends AppCompatActivity implements OnDataSetAvail{
    private final String TAG = getClass().getSimpleName();

    private ImageView image1;

    private LinearLayout showingLL;
    private LinearLayout daySelectionLL;

    private Movie movie;
    private ArrayList<Showing> showings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_selection);

        Bundle extras = getIntent().getExtras();
        movie = (Movie) extras.getSerializable("MOVIE");

        this.image1 = (ImageView) findViewById(R.id.show_selection_image1);
        this.showingLL = (LinearLayout) findViewById(R.id.Show_Selection_SelectShowingLL);
        this.daySelectionLL = (LinearLayout) findViewById(R.id.showSelection_DatesLL);


        Picasso.with(this).load(movie.getImageUrl()).into(image1);


        new GetDataSetAsync(this).execute(movie);


    }

    @Override
    public void onDataSetAvail(ArrayList<String> dates, ArrayList<Showing> showings) {
        this.showings = showings;

        int i = 0;
        for (String date: dates
             ) {
                if (i == 4){
                    break;
                }
            Button dateButton = new Button(this);
            dateButton.setText(date.substring(5,10));
            daySelectionLL.addView(dateButton);

            dateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button button = (Button) view;

                    showingLL.removeAllViews();
                    createShowButtons(button.getText().toString());

                }
            });

        i++;
        }

    }

    public void createShowButtons(String date){
        for (Showing showing: showings
             ) {if (showing.getDate().contains(date)){
                 Button showButton = new Button(this);
                 showButton.setText(showing.getStartingTime() + "\n Zaal: " + showing.getHall().getHallNumber());
                 showingLL.addView(showButton);
                 showButton.setTransitionName("" +showing.getShowID());

                 showButton.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                            Button button = (Button) view;

                         Intent intent = new Intent(button.getContext(), PaymentActivity.class);
                         intent.putExtra("SHOWING",button.getTransitionName());
                         startActivity(intent);

                     }
                 });
        }

        }

    }

    @Override
    public Context getContext() {
        return this;
    }
}
