package nl.marcovp.avans.cavanz.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import nl.marcovp.avans.cavanz.Domain.Hall;
import nl.marcovp.avans.cavanz.Domain.Movie;
import nl.marcovp.avans.cavanz.Domain.Showing;
import nl.marcovp.avans.cavanz.Domain.Ticket;
import nl.marcovp.avans.cavanz.Domain.TicketType;
import nl.marcovp.avans.cavanz.R;

public class PaymentActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private Showing showing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(R.string.text_payment_order);

        // TODO: Get Movie from showing.
        Movie m = new Movie("Titel van de film", 1, 2.0, null, null, "Dit is een film", "Dutch", "05-05-1998");

        // TODO: Get Showing from intent.
        showing = new Showing(m, "05-05-2018", new Hall(1, 5, 5), "11:30", "13:10");

        TextView textViewTitle = findViewById(R.id.payment_textview_title);
        TextView textViewDate = findViewById(R.id.payment_textview_date);
        TextView textViewStartTime = findViewById(R.id.payment_textview_starttime);
        TextView textViewEndTime = findViewById(R.id.payment_textview_endtime);
        TextView textViewLocation = findViewById(R.id.payment_textview_location);

        textViewTitle.setText(showing.getMovie().getTitle());
        textViewDate.append(" " + showing.getDate());
        textViewStartTime.append(" " + showing.getStarttime());
        textViewEndTime.append(" " + showing.getEndtime());
        textViewLocation.append(" " + getString(R.string.payment_text_hall) + " " + showing.getHall().getHallNumber());

        // TODO: Add option to select multiple tickets via arrayadapter

        ArrayList<TicketType> tickets = new ArrayList<>();
        tickets.add(TicketType.TICKET_ADULT);
        tickets.add(TicketType.TICKET_KIDS);
        tickets.add(TicketType.TICKET_STUDENT);

        ListView listViewTickets = findViewById(R.id.payment_listview_tickets);

        ArrayAdapter<TicketType> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                tickets
        );

        listViewTickets.setAdapter(arrayAdapter);

        // TODO: Generate ticket object and intent to Chair selection

        listViewTickets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long l) {

                // Get TicketType
                TicketType tt = (TicketType) adapter.getItemAtPosition(position);

                // Intent time
                Intent i = new Intent(getApplicationContext(), PaymentCompletionActivity.class);

                i.putExtra("TICKETTYPE", tt);
                i.putExtra("SHOWING", showing);

                startActivity(i);

            }

        });

    }
}
