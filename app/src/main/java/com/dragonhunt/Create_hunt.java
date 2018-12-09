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
    private EditText description;
    private EditText location;
    private EditText minParticipants;
    private EditText isPrivate;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requireLogin();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hunt);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        title = (EditText)findViewById(R.id.Title);
        description = (EditText)findViewById(R.id.Description);
        location = (EditText)findViewById(R.id.Location);
        minParticipants = (EditText) findViewById(R.id.minParticipants);
        isPrivate = (EditText) findViewById(R.id.isPrivate);

        final Button createHuntButton = (Button) findViewById(R.id.createHunt);
        createHuntButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("===== Creating hunt.");
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

    @Override
    public void onBackPressed() {
        // do nothing...
    }

}
