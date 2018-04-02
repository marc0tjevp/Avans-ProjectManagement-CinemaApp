package nl.marcovp.avans.cavanz.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import nl.marcovp.avans.cavanz.Domain.Showing;
import nl.marcovp.avans.cavanz.Domain.TicketType;
import nl.marcovp.avans.cavanz.R;

public class PaymentCompletionActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_completion);

    }
}
