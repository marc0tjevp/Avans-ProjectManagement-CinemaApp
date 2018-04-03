package nl.marcovp.avans.cavanz.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import nl.marcovp.avans.cavanz.Domain.Hall;
import nl.marcovp.avans.cavanz.Domain.Movie;
import nl.marcovp.avans.cavanz.Domain.Showing;
import nl.marcovp.avans.cavanz.Domain.TicketType;
import nl.marcovp.avans.cavanz.R;
import nl.marcovp.avans.cavanz.Util.TicketTypeAdapter;

public class PaymentActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private Showing showing;
    private ArrayList<TicketType> tobuytickets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(R.string.text_payment_order);

        // TODO: Get Movie from showing.
        Movie m = new Movie("Titel van de film", 1, 2.0, null, null, "Dit is een film", "Dutch", "05-05-1998");

        // TODO: Get Showing from intent (MovieOfferActivity or MovieDetailActivity).
        showing = new Showing(m, "12:00", "14:30", "2018-04-05", new Hall("1", 15, 25));

        TextView textViewTitle = findViewById(R.id.payment_textview_title);
        TextView textViewDate = findViewById(R.id.payment_textview_date);
        TextView textViewStartTime = findViewById(R.id.payment_textview_starttime);
        TextView textViewEndTime = findViewById(R.id.payment_textview_endtime);
        TextView textViewLocation = findViewById(R.id.payment_textview_location);

        textViewTitle.setText(showing.getMovie().getTitle());
        textViewDate.append(" " + showing.getDate());
        textViewStartTime.append(" " + showing.getStartingTime());
        textViewEndTime.append(" " + showing.getEndingTime());
        textViewLocation.append(" " + getString(R.string.text_payment_hall) + " " + showing.getHall().getHallNumber());

        final ArrayList<TicketType> tickets = new ArrayList<>();
        tickets.add(TicketType.TICKET_ADULT);
        tickets.add(TicketType.TICKET_KIDS);
        tickets.add(TicketType.TICKET_STUDENT);

        ListView listViewTickets = findViewById(R.id.payment_listview_tickets);
        Button nextButton = findViewById(R.id.payment_button_next);

        TicketTypeAdapter adapter = new TicketTypeAdapter(this, tickets);

        listViewTickets.setAdapter(adapter);

        listViewTickets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long l) {

                TicketType tt = (TicketType) adapter.getItemAtPosition(position);
                tobuytickets.add(tt);

                Toast.makeText(PaymentActivity.this, "Added " + tt.getTicketTypeName(), Toast.LENGTH_SHORT).show();

            }

        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Intent time
                Intent i = new Intent(getApplicationContext(), PaymentTicketActivity.class);

                i.putExtra("TICKETTYPE", tobuytickets);
                i.putExtra("SHOWING", showing);

                startActivity(i);

            }
        });


    }
}
