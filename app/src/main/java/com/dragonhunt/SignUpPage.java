package com.dragonhunt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dragonhunt.backend.NewUserRequest;
import com.example.lukak.dragonhunt.R;

public class SignUpPage extends AppCompatActivity {

    private TextView mTextMessage;
    private EditText nameInput;
    private EditText surnameInput;
    private EditText usernameInput;
    private EditText passwordInput;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        nameInput = (EditText) findViewById(R.id.signUpName);
        surnameInput = (EditText) findViewById(R.id.signUpSurname);
        usernameInput = (EditText) findViewById(R.id.signUpUsername);
        passwordInput = (EditText) findViewById(R.id.signUpPassword);

        final Button button = (Button) findViewById(R.id.signUpConfirm);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleSignUpConfirmButton(v);
            }
        });
    }

    private void handleSignUpConfirmButton(View v) {
        boolean allValid = true;

        if (nameInput.getText().toString().trim().equals("")) {
            allValid = false;
        }
        if (surnameInput.getText().toString().trim().equals("")) {
            allValid = false;
        }
        if (usernameInput.getText().toString().trim().equals("") || usernameInput.getText().toString().trim().length() < 5) {
            allValid = false;
        }
        if (passwordInput.getText().toString().length() < 8) {
            allValid = false;
        }
        String message = "";
        if (allValid) {
            createNewUser();
        }
        else {
            message = "Please fill all the fields. Username must be at least 5 characters long, and password at least 8.";
        }
        mTextMessage.setText(message);
    }

    private void createNewUser() {
        NewUserRequest newUser = new NewUserRequest(this);
        newUser.setName(nameInput.getText().toString().trim());
        newUser.setSurname(surnameInput.getText().toString().trim());
        newUser.setUsername(usernameInput.getText().toString().trim());
        newUser.setPassword(passwordInput.getText().toString().trim());

        newUser.execute("");
    }

    public void setMessage(String message) {
        mTextMessage.setText(message);
    }

}
