package com.dragonhunt;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dragonhunt.backend.DatabaseManager;
import com.dragonhunt.backend.JoinRequest;

import org.json.JSONObject;


public class join_group extends AppCompatActivity {

    private TextView mTextMessage;
    private EditText codeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseManager.requireLogin(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);

        mTextMessage = (TextView) findViewById(R.id.message);
        codeInput = (EditText) findViewById(R.id.codeInput);

        final Button loginButton = (Button) findViewById(R.id.joinButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleJoinGroup(v);
            }
        });
    }

    public void setMessage(String message) {
        mTextMessage.setText(message);
    }

    private void handleJoinGroup(View v) {
        boolean allValid = true;

        if (codeInput.getText().toString().trim().equals("")) {
            allValid = false;
        }
        try {
            int minPart = Integer.parseInt(codeInput.getText().toString().trim());
            if (minPart < 0) {
                allValid = false;
            }
        }
        catch (NumberFormatException e) {
            // not a number
            allValid = false;
        }

        if(allValid) {
            joinChallenge();
        }
        else {
            mTextMessage.setText("Please enter the valid credentials.");
        }
    }

    private void joinChallenge() {
        JoinRequest joinRequest = new JoinRequest(this);
        joinRequest.setOngoingId(codeInput.getText().toString().trim());
        SharedPreferences preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        String username = preferences.getString("username", "");
        if (username.equals("")) {
            throw new RuntimeException("No username was stored in the preferences, but new hunt tried to be created.");
        }
        joinRequest.setUsername(username);

        joinRequest.execute("");
    }

}
