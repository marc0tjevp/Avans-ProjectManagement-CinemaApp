package nl.marcovp.avans.cavanz.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import nl.marcovp.avans.cavanz.Data.DataHelper;
import nl.marcovp.avans.cavanz.Data.SQLiteHelper;
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

        TextView textViewStatus = findViewById(R.id.payment_completion_text_message);
        TextView textViewExplanation = findViewById(R.id.payment_completion_text_explanation);

        Button buttonDownload = findViewById(R.id.payment_completion_button_download);
        buttonDownload.setVisibility(View.INVISIBLE);


        if (statuscode.equals("200")) {

            textViewStatus.setText(R.string.text_payment_completed_title);
            textViewExplanation.setText(R.string.text_payment_completed_explanation);

            // Show download button
            buttonDownload.setVisibility(View.VISIBLE);

            SQLiteHelper db = new SQLiteHelper(getApplicationContext());

            // TODO: Insert ticket into database.

            // TODO: Generate PDF

            buttonDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        } else if (statuscode.equals("500")) {

            textViewStatus.setText(R.string.text_payment_failed_title);
            textViewExplanation.setText(R.string.text_payment_failed_explanation);

            t = null;

        } else {
            Toast.makeText(this, "Something went wrong :(, try again later", Toast.LENGTH_SHORT).show();

            t = null;
        }

    }
}
