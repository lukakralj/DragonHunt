package com.dragonhunt.backend;

import android.app.Activity;
import android.content.Intent;

import com.dragonhunt.LoginPage;
import com.dragonhunt.SignUpPage;

public class NewUserRequest extends HttpRequest {

    public NewUserRequest(Activity activity) {
        super(activity);
        url = DatabaseManager.NEW_USER_URL;
    }

    public void setUsername(String username) {
        parameters.put("username", username);
    }

    public void setPassword(String password) {
        parameters.put("password", password);
    }

    public void setName(String name) {
        parameters.put("name", name);
    }

    public void setSurname(String surname) {
        parameters.put("surname", surname);
    }

    @Override
    public boolean issueRequest() {
        if (parameters.size() != 4) {
            return false;
        }
        return super.issueRequest();
    }

    @Override
    protected void onPostExecute(String result) {
        if (wasSuccessful()) {
            ((SignUpPage)activity).setMessage("Registration successful.");
            Intent intent = new Intent(activity, LoginPage.class);
            activity.startActivity(intent);
        }
        else {
            ((SignUpPage)activity).setMessage(getMessage());
        }
    }


}
