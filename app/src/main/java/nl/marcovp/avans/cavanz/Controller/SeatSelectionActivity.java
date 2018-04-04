package nl.marcovp.avans.cavanz.Controller;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;
import java.util.zip.Inflater;

import nl.marcovp.avans.cavanz.Data.SQLiteHelper;
import nl.marcovp.avans.cavanz.Domain.Hall;
import nl.marcovp.avans.cavanz.Domain.Movie;
import nl.marcovp.avans.cavanz.Domain.Seat;
import nl.marcovp.avans.cavanz.Domain.Showing;
import nl.marcovp.avans.cavanz.Domain.Ticket;
import nl.marcovp.avans.cavanz.R;

public class SeatSelectionActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    private int ColorSelected = Color.GREEN;
    private int ColorTaken = Color.DKGRAY;
    private int ColorAvail = Color.BLUE;
    private String seatImagePath = "https://cdn2.iconfinder.com/data/icons/movie-icons/512/Directors_Chair-128.png";
    private ArrayList<Ticket> tickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        final ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.chair_selection_frame);
        final Button nextButton = (Button) findViewById(R.id.chair_selection_next);
        Button prevButton = (Button) findViewById(R.id.chair_selection_prev);
        final TextView amountOfChairToDistribute = (TextView) findViewById(R.id.AmountOfChairToDistribute);

        amountOfChairToDistribute.setText("Stoelen te verdelen: ");

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // TODO: 4/4/2018  Coupling
        final Showing s = (Showing) getIntent().getExtras().getSerializable("SHOWING");
        tickets = (ArrayList<Ticket>) getIntent().getExtras().getSerializable("TICKETS");

        amountOfChairToDistribute.setText("Stoelen te verdelen: " + tickets.size());

        Hall hall = s.getHall();

        final int rows = hall.getRowAmount();
        final int seatsPerRow = hall.getSeatPerRow();
        final ArrayList<Seat> seatsSelected = new ArrayList<>();

        nextButton.setEnabled(false);

        final GridLayout gridLayout = new GridLayout(this);
        gridLayout.setLayoutParams(cl.getLayoutParams());

        gridLayout.setColumnCount(seatsPerRow + 1);
        gridLayout.setRowCount(rows + 1);
        gridLayout.setLayoutParams(new GridLayout.LayoutParams());
        gridLayout.setFitsSystemWindows(true);
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        cl.addView(gridLayout);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (Ticket t : tickets) {
                    for (Seat s : seatsSelected) {
                        s = new Seat(s.getX(), s.getY());
                        t.setSeat(s);
                    }
                }

                goToNextStep(s, tickets);
            }
        });

        Log.d(TAG, "onCreate: Hall! " + hall.getSeatPerRow() + " bij " + hall.getRowAmount());
        for (int y = 0; y < rows; y++) {


            for (int x = 0; x < seatsPerRow; x++) {

                final Seat seat = new Seat(x, y);
                seat.setSeat(new ImageView(this));
                seat.getSeat().setLayoutParams(new GridLayout.LayoutParams());

                Picasso.with(this).
                        load(seatImagePath).
                        resize(
                                ((int) (getWindowManager().getDefaultDisplay().getWidth() * 0.79)) / seatsPerRow,
                                ((int) (getWindowManager().getDefaultDisplay().getHeight() * 0.50)) / seatsPerRow)
                        .into(seat.getSeat());

                seat.getSeat().setBackgroundColor(ColorAvail);

                if (seat.isTaken()) {
                    seat.getSeat().setBackgroundColor(ColorTaken);
                }

                gridLayout.addView(seat.getSeat());
                seat.getSeat().setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        Log.d(TAG, "onClick: " + seat.getX() + "  " + seat.getY() + " selected =: " + seat.isSelected());
                        nextButton.setEnabled(false);
                        if (seat.isTaken()) {
                            seat.getSeat().setBackgroundColor(ColorTaken);
                            return;
                        }
                        seat.setSelected(!seat.isSelected());

                        if (seat.isSelected()) {
                            seatsSelected.add(seat);
                            seat.getSeat().setBackgroundColor(ColorSelected);

                            amountOfChairToDistribute.setText("Stoelen te verdelen: " + (tickets.size() - seatsSelected.size()));
                            Picasso.with(seat.getSeat().getContext()).load(seatImagePath).into(seat.getSeat());

                            // TODO: 4/4/2018  Coupling

                            if (tickets.size() == seatsSelected.size()) {
                                nextButton.setEnabled(true);
                            }
                        } else {
                            seat.getSeat().setBackgroundColor(ColorAvail);
                            seatsSelected.remove(seat);
                        }
                        Log.d(TAG, "onClick: amount of seats in array: " + seatsSelected.size());
                    }
                });
            }
        }
    }

    public void goToNextStep(Showing s, ArrayList<Ticket> tickets) {
        Intent intent = new Intent(this, PaymentProviderActivity.class);
        intent.putExtra("TICKETS", tickets);

        startActivity(intent);
    }
}
