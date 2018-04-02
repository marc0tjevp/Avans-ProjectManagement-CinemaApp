package nl.marcovp.avans.cavanz.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import nl.marcovp.avans.cavanz.Domain.Hall;
import nl.marcovp.avans.cavanz.Domain.Movie;
import nl.marcovp.avans.cavanz.Domain.Showing;
import nl.marcovp.avans.cavanz.Domain.Ticket;
import nl.marcovp.avans.cavanz.R;

public class PaymentActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(R.string.text_payment_order);

        // TODO: Get Movie from showing.
        Movie m = new Movie("title", 1, 2.0, null, null, "Dit is een film", "Dutch", "05-05-1998");

        // TODO: Get Showing from intent.
        Showing s = new Showing(m, "05-05-2018", new Hall(1, 5, 5), "11:30", "13:10");

        TextView textViewTitle = findViewById(R.id.payment_textview_title);
        TextView textViewDate = findViewById(R.id.payment_textview_date);
        TextView textViewStartTime = findViewById(R.id.payment_textview_starttime);
        TextView textViewEndTime = findViewById(R.id.payment_textview_endtime);
        TextView textViewLocation = findViewById(R.id.payment_textview_location);

        textViewTitle.setText(" " + s.getMovie().getTitle());
        textViewDate.append(" " + s.getDate());
        textViewStartTime.append(" " + s.getStarttime());
        textViewEndTime.append(" " + s.getEndtime());
        textViewLocation.append(" " + getString(R.string.payment_text_hall) + " " + s.getHall().getHallNumber());

        // TODO: Get Ticket types and add them to the listview
        // TODO: Add option to select multiple tickets

        ArrayList<String> tickets = new ArrayList<>();
        tickets.add("Type 1");
        tickets.add("Type 2");
        tickets.add("Type 3");

        ListView listViewTickets = findViewById(R.id.payment_listview_tickets);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                tickets
        );

        listViewTickets.setAdapter(arrayAdapter);
        
        // Realtime total price

        // (Send to chair intent)

    }

}
