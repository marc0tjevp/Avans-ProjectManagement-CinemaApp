package nl.marcovp.avans.cavanz.Controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.ImageView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import nl.marcovp.avans.cavanz.Domain.Showing;
import nl.marcovp.avans.cavanz.Domain.Ticket;
import nl.marcovp.avans.cavanz.Domain.TicketType;
import nl.marcovp.avans.cavanz.R;
import nl.marcovp.avans.cavanz.Util.TicketTypeAdapter;

public class PaymentTicketActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    double ticketPrice = 0;
    private ArrayList<Ticket> tickets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_ticket);

        setTitle(getString(R.string.text_payment_overview));

        final Showing showing = (Showing) getIntent().getExtras().getSerializable("SHOWING");
        final ArrayList<TicketType> ticketType = (ArrayList<TicketType>) getIntent().getExtras().getSerializable("TICKETTYPE");

        ImageView imageView = findViewById(R.id.payment_ticket_imageview_poster);
        Picasso.with(this).load(showing.getMovie().getImageUrl()).into(imageView);

        TextView textViewTitle = findViewById(R.id.payment_ticket_textview_title);
        TextView textViewDate = findViewById(R.id.payment_ticket_textview_date);
        TextView textViewStartTime = findViewById(R.id.payment_ticket_textview_starttime);
        TextView textViewEndTime = findViewById(R.id.payment_ticket_textview_endtime);
        TextView textViewLocation = findViewById(R.id.payment_ticket_textview_location);
        TextView textViewPrice = findViewById(R.id.payment_ticket_textview_price);

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

        DecimalFormat df = new DecimalFormat("#.00");

        textViewPrice.setText("€" + df.format(ticketPrice));

        final EditText editTextName = findViewById(R.id.payment_ticket_edit_name);
        final EditText editTextSurname = findViewById(R.id.payment_ticket_edit_surname);
        final EditText editTextEmail = findViewById(R.id.payment_ticket_edit_email);

        Button paymentButton = findViewById(R.id.payment_ticket_button_next);
        Button cancelButton = findViewById(R.id.payment_ticket_button_cancel);

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = String.valueOf(editTextName.getText());
                String surname = String.valueOf(editTextSurname.getText());
                String email = String.valueOf(editTextEmail.getText());

                if (editTextName.getText().toString().equals("")) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(PaymentTicketActivity.this);
                    builder1.setMessage("Naam is een verplicht veld.");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Terug",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                } else if (editTextSurname.getText().toString().equals("")) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(PaymentTicketActivity.this);
                    builder1.setMessage("Achternaam is een verplicht veld.");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Terug",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                } else if (editTextEmail.getText().toString().equals("")) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(PaymentTicketActivity.this);
                    builder1.setMessage("Email is een verplicht veld.");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Terug",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                } else {

                    for (TicketType tt : ticketType) {
                        Ticket t = new Ticket(null, showing, name, surname, email, tt.getTicketPrice());
                        tickets.add(t);
                    }

//                    Intent i = new Intent(getApplicationContext(), PaymentProviderActivity.class);
//                    i.putExtra("TICKET", t);

                    Intent i = new Intent(getApplicationContext(), SeatSelectionActivity.class);
                    i.putExtra("TICKETS", tickets);
                    i.putExtra("SHOWING", showing);

                    startActivity(i);
                }


            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
