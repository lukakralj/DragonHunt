package com.dragonhunt.backend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.dragonhunt.Create_hunt;
import com.dragonhunt.MainActivity;
import com.dragonhunt.PinCodeDisplay;

import org.json.JSONException;
import org.json.JSONObject;

public class NewChallengeRequest extends HttpRequest {

    private JSONObject challenge;

    public NewChallengeRequest(Activity activity) {
        super(activity);
        url = DatabaseManager.NEW_CHALLENGE_URL;
    }

    public void setTitle(String title) {
        parameters.put("title", title);
    }

    public void setTask(String task) {
        parameters.put("task", task);
    }

    public void setLocation(String location) {
        parameters.put("location", location);
    }

    public void setChallengeOwner(String challengeOwner) {
        parameters.put("challengeOwner", challengeOwner);
    }

    public void setMinParticipants(String number) {
        parameters.put("minParticipants", number);
    }

    /**
     *
     * @param isPrivate 1 for true, 0 for false.
     */
    public void setIsPrivate(String isPrivate) {
        parameters.put("isPrivate", isPrivate);
    }

    @Override
    public boolean issueRequest() {
        if (parameters.size() != 6) {
            return false;
        }
        if (super.issueRequest()) {
            try {
                challenge = response.getJSONObject("challenge");
                return true;
            }
            catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }
        else {
            return false;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (wasSuccessful()) {
            ((Create_hunt)activity).setMessage("Success!");

            SharedPreferences preferences = activity.getSharedPreferences("Settings", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("code", getChallengePinCode());
            editor.apply();

            Intent intent = new Intent(activity, PinCodeDisplay.class);
            activity.startActivity(intent);
        }
        else {
            ((Create_hunt)activity).setMessage(getMessage());
        }
    }

    public String getChallengePinCode() {
        try {
            return challenge.getString("id");
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
