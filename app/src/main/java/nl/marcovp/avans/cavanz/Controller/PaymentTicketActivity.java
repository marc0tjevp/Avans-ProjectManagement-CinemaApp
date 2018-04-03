package nl.marcovp.avans.cavanz.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import nl.marcovp.avans.cavanz.Domain.Showing;
import nl.marcovp.avans.cavanz.Domain.Ticket;
import nl.marcovp.avans.cavanz.Domain.TicketType;
import nl.marcovp.avans.cavanz.R;
import nl.marcovp.avans.cavanz.Util.TicketTypeAdapter;

public class PaymentTicketActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    double ticketPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_ticket);

        setTitle(getString(R.string.text_payment_overview));

        final Showing showing = (Showing) getIntent().getExtras().getSerializable("SHOWING");
        final ArrayList<TicketType> ticketType = (ArrayList<TicketType>) getIntent().getExtras().getSerializable("TICKETTYPE");

        // TODO: Use User input to generate ticket and intent to payment
        // TODO: Get Ticket types from array

        TextView textViewTitle = findViewById(R.id.payment_ticket_textview_title);
        TextView textViewDate = findViewById(R.id.payment_ticket_textview_date);
        TextView textViewStartTime = findViewById(R.id.payment_ticket_textview_starttime);
        TextView textViewEndTime = findViewById(R.id.payment_ticket_textview_endtime);
        TextView textViewLocation = findViewById(R.id.payment_ticket_textview_location);

        textViewTitle.setText(showing.getMovie().getTitle());
        textViewDate.append(" " + showing.getDate());
        textViewStartTime.append(" " + showing.getStartingTime());
        textViewEndTime.append(" " + showing.getEndingTime());
        textViewLocation.append(" " + getString(R.string.text_payment_hall) + " " + showing.getHall().getHallNumber());

        ListView listViewTickets = findViewById(R.id.payment_ticket_listview_tickets);
        TicketTypeAdapter adapter = new TicketTypeAdapter(this, ticketType);
        listViewTickets.setAdapter(adapter);

        for (TicketType ticket : ticketType) {
            ticketPrice += ticket.getTicketPrice();
        }

        final EditText editTextName = findViewById(R.id.payment_ticket_edit_name);
        final EditText editTextSurname = findViewById(R.id.payment_ticket_edit_surname);
        final EditText editTextEmail = findViewById(R.id.payment_ticket_edit_email);

        Button paymentButton = findViewById(R.id.payment_ticket_button_next);

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = String.valueOf(editTextName.getText());
                String surname = String.valueOf(editTextSurname.getText());
                String email = String.valueOf(editTextEmail.getText());

                Ticket t = new Ticket(0, 0, showing, name, surname, email, ticketPrice);

                // TODO: Start payment intent and generate the ticket as pdf.
                // TODO: Save the ticket to the database.

                Intent i = new Intent(getApplicationContext(), PaymentProviderActivity.class);

                i.putExtra("TICKET", t);

                startActivity(i);

            }
        });
    }
}
