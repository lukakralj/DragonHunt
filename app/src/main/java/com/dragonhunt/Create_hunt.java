package com.dragonhunt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.dragonhunt.backend.NewChallengeRequest;
import com.example.lukak.dragonhunt.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Create_hunt extends AppCompatActivity {

    private TextView mTextMessage;
    private EditText title;
    private EditText task;
    private EditText location;
    private EditText minParticipants;
    private EditText isPrivate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requireLogin();
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

        final Button logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("===== Logging out.");
                SharedPreferences preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("username");
                editor.apply();
                requireLogin();
            }
        });
    }

    private void requireLogin() {
        SharedPreferences preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        if (!preferences.contains("username")) {
            // no user is logged in
            Intent intent = new Intent(this, LoginPage.class);
            startActivity(intent);
        }
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
        if (!(isPrivate.getText().toString().trim().equals("1") || isPrivate.getText().toString().trim().equals("0"))) {
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
