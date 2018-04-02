package nl.marcovp.avans.cavanz.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nl.marcovp.avans.cavanz.R;

public class TicketActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
    }
}
