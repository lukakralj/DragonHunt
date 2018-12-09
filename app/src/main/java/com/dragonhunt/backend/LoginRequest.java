package com.dragonhunt.backend;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.dragonhunt.LoginPage;


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
        if (parameters.size() != 2) {
            return false;
        }
        return super.issueRequest();
    }

    @Override
    protected void onPostExecute(String result) {
        if (wasSuccessful()) {
            SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", parameters.get("username"));
            editor.apply();

            ((LoginPage)activity).setMessage("Login successful.");

        }
        else {
            ((LoginPage)activity).setMessage(getMessage());
        }
    }

}
