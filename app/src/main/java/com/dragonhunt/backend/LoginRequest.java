package com.dragonhunt.backend;

import android.app.Activity;

import com.dragonhunt.SignUpPage;

public class LoginRequest extends HttpRequest {

    public LoginRequest(Activity activity) {
        super(activity);
        url = DatabaseManager.LOGIN_URL;
    }

    public void setUsername(String username) {
        parameters.put("username", username);
    }

    public void setPassword(String password) {
        parameters.put("password", password);
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
        }
        else {
            ((SignUpPage)activity).setMessage(getMessage());
        }
    }

}
