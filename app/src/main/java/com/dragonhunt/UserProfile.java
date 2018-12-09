package com.dragonhunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dragonhunt.backend.DatabaseManager;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseManager.requireLogin(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }
}
