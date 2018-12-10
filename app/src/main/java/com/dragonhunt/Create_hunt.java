package com.dragonhunt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dragonhunt.backend.DatabaseManager;
import com.dragonhunt.backend.NewChallengeRequest;


public class Create_hunt extends AppCompatActivity {

    private TextView mTextMessage;
    private EditText title;
    private EditText task;
    private EditText location;
    private EditText minParticipants;
    private EditText isPrivate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseManager.requireLogin(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hunt);

        mTextMessage = (TextView) findViewById(R.id.message);

        title = (EditText)findViewById(R.id.Title);
        task = (EditText)findViewById(R.id.Description);
        location = (EditText)findViewById(R.id.Location);
        minParticipants = (EditText) findViewById(R.id.minParticipants);
        isPrivate = (EditText) findViewById(R.id.isPrivate);

        final Button createHuntButton = (Button) findViewById(R.id.createHunt);
        createHuntButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleCreateHunt(v);
            }
        });
    }

    private void handleCreateHunt(View v) {
        boolean allValid = true;

        if (title.getText().toString().trim().equals("")) {
            allValid = false;
        }
        if (task.getText().toString().trim().equals("")) {
            allValid = false;
        }
        if (location.getText().toString().trim().equals("")) {
            allValid = false;
        }
        try {
            int minPart = Integer.parseInt(minParticipants.getText().toString().trim());
            if (minPart < 1) {
                allValid = false;
            }
        }
        catch (NumberFormatException e) {
            // not a number
            allValid = false;
        }
        if (!(isPrivate.getText().toString().trim().equals("true") || isPrivate.getText().toString().trim().equals("false"))) {
            allValid = false;
        }

        if (allValid) {
            createHunt();
        }
        else {
            mTextMessage.setText("Invalid inputs.");
        }
    }

    private void createHunt() {
        NewChallengeRequest newChallenge = new NewChallengeRequest(this);
        newChallenge.setTitle(title.getText().toString().trim());
        newChallenge.setTask(task.getText().toString().trim());
        newChallenge.setLocation(location.getText().toString().trim());
        SharedPreferences preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        String username = preferences.getString("username", "");
        if (username.equals("")) {
            throw new RuntimeException("No username was stored in the preferences, but new hunt tried to be created.");
        }
        newChallenge.setChallengeOwner(username);
        String isPr = isPrivate.getText().toString().trim().equals("true") ? "1" : "0";
        newChallenge.setIsPrivate(isPrivate.getText().toString().trim());
        newChallenge.setMinParticipants(minParticipants.getText().toString().trim());

        newChallenge.execute("");
    }

    public void setMessage(String message) {
        mTextMessage.setText(message);
    }

    @Override
    public void onBackPressed() {
        // do nothing...
    }

}
