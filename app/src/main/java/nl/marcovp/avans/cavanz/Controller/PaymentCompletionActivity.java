package nl.marcovp.avans.cavanz.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import nl.marcovp.avans.cavanz.Domain.Showing;
import nl.marcovp.avans.cavanz.Domain.Ticket;
import nl.marcovp.avans.cavanz.Domain.TicketType;
import nl.marcovp.avans.cavanz.R;

public class PaymentCompletionActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_completion);

        Ticket t = (Ticket) getIntent().getExtras().get("TICKET");
        String statuscode = getIntent().getStringExtra("PAYMENT_CODE");

        if (statuscode.equals(200)) {
            Toast.makeText(this, "Payment Succes", Toast.LENGTH_SHORT).show();
        } else if (statuscode.equals(500)) {
            Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Something went wrong :(, try again later", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, t.toString(), Toast.LENGTH_SHORT).show();

    }
}
