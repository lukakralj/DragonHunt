package com.dragonhunt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dragonhunt.backend.DatabaseManager;

public class PinCodeDisplay extends AppCompatActivity {

    private TextView codeDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseManager.requireLogin(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_code_display);

        final Button homeButton = (Button) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goHome();
            }
        });

        codeDisplay = (TextView) findViewById(R.id.pinCodeDisplay);
        SharedPreferences preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        codeDisplay.setText(preferences.getString("code", "err"));
    }

    private void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // do nothing...
    }
}
