package nl.marcovp.avans.cavanz.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import nl.marcovp.avans.cavanz.Domain.Showing;
import nl.marcovp.avans.cavanz.Domain.TicketType;
import nl.marcovp.avans.cavanz.R;

public class PaymentTicketActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_ticket);

        Showing showing = (Showing) getIntent().getExtras().getSerializable("SHOWING");
        TicketType ticketType = (TicketType) getIntent().getExtras().getSerializable("TICKETTYPE");

        Toast.makeText(this, ticketType.getTicketTypeName(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, showing.getMovie().getTitle(), Toast.LENGTH_SHORT).show();

        // TODO: Use User input to generate ticket and intent to payment

    }
}
