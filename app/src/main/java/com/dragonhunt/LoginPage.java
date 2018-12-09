package com.dragonhunt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dragonhunt.backend.LoginRequest;

public class LoginPage extends AppCompatActivity {

    private TextView mTextMessage;
    private EditText usernameInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mTextMessage = (TextView) findViewById(R.id.message);

        usernameInput = (EditText) findViewById(R.id.loginUsername);
        passwordInput = (EditText) findViewById(R.id.loginPassword);

        final Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleLoginButton(v);
            }
        });

        final Button gotoSignUp = (Button) findViewById(R.id.gotoSignUp);
        gotoSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openRegistrationActivity();
            }
        });
    }

    private void openRegistrationActivity() {
        Intent intent = new Intent(this, SignUpPage.class);
        startActivity(intent);
    }

    private void handleLoginButton(View v) {
        boolean allValid = true;

        if (usernameInput.getText().toString().trim().equals("") || usernameInput.getText().toString().trim().length() < 5) {
            allValid = false;
        }
        if (passwordInput.getText().toString().length() < 8) {
            allValid = false;
        }

        if(allValid) {
            loginUser();
        }
        else {
            mTextMessage.setText("Please enter the valid credentials.");
        }

    }

    private void loginUser() {
        LoginRequest loginRequest = new LoginRequest(this);
        loginRequest.setUsername(usernameInput.getText().toString().trim());
        loginRequest.setPassword(passwordInput.getText().toString());

        loginRequest.execute("");
    }

    public void setMessage(String message) {
        mTextMessage.setText(message);
    }

    @Override
    public void onBackPressed() {
        // do nothing...
    }

}
