package nl.marcovp.avans.cavanz.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import nl.marcovp.avans.cavanz.Domain.Ticket;
import nl.marcovp.avans.cavanz.R;

public class PaymentProviderActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_provider);

        ImageButton paypal = findViewById(R.id.payment_provider_button_paypal);
        ImageButton creditcard = findViewById(R.id.payment_provider_button_creditcard);
        ImageButton apple = findViewById(R.id.payment_provider_button_apple);
        ImageButton amazon = findViewById(R.id.payment_provider_button_amazon);

        // Spinner to simulate statuscodes
        final Spinner spinner = findViewById(R.id.payment_provider_spinner_statuscode);
        ArrayList<String> statuscodes = new ArrayList<>();
        statuscodes.add("200");
        statuscodes.add("500");
        statuscodes.add("Something that doesn't make sense");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, statuscodes);
        spinner.setAdapter(dataAdapter);

        final Ticket ticket = (Ticket) getIntent().getSerializableExtra("TICKET");

        paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PaymentProviderActivity.this, "Simulatie betaling", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), PaymentCompletionActivity.class);

                i.putExtra("TICKET", ticket);
                i.putExtra("PAYMENT_CODE", spinner.getSelectedItem().toString());

                startActivity(i);

            }
        });

        creditcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PaymentProviderActivity.this, "Simulatie betaling", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), PaymentCompletionActivity.class);

                i.putExtra("TICKET", ticket);
                i.putExtra("PAYMENT_CODE", spinner.getSelectedItem().toString());

                startActivity(i);

            }
        });

        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PaymentProviderActivity.this, "Simulatie betaling", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), PaymentCompletionActivity.class);

                i.putExtra("TICKET", ticket);
                i.putExtra("PAYMENT_CODE", spinner.getSelectedItem().toString());

                startActivity(i);

            }
        });

        amazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PaymentProviderActivity.this, "Simulatie betaling", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), PaymentCompletionActivity.class);

                i.putExtra("TICKET", ticket);
                i.putExtra("PAYMENT_CODE", spinner.getSelectedItem().toString());

                startActivity(i);

            }
        });

    }
}
