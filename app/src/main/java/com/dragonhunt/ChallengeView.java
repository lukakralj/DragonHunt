package com.dragonhunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.dragonhunt.backend.DatabaseManager;

import org.json.JSONException;
import org.json.JSONObject;

public class ChallengeView extends AppCompatActivity {

    public static JSONObject challenge;

    private TextView displayTask;
    private TextView displayOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseManager.requireLogin(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_view);

        displayOwner = (TextView) findViewById(R.id.displayOwner);
        displayTask = (TextView) findViewById(R.id.displayTask);

        try {
            displayOwner.setText("Owner: " + challenge.getString("challengeOwner"));
            displayTask.setText("Task: " + challenge.getString("task"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
