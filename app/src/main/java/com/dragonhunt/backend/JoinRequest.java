package com.dragonhunt.backend;

import android.app.Activity;
import android.content.Intent;

import com.dragonhunt.ChallengeView;
import com.dragonhunt.join_group;

import org.json.JSONException;
import org.json.JSONObject;

public class JoinRequest extends HttpRequest {

    private JSONObject challenge;

    public JoinRequest(Activity activity) {
        super(activity);
        url = DatabaseManager.JOIN_URL;
    }

    public void setUsername(String username) {
        parameters.put("username", username);
    }

    public void setOngoingId(String id) {
        parameters.put("id", id);
    }

    @Override
    public boolean issueRequest() {
        if (parameters.size() != 2) {
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
            ((join_group)activity).setMessage("Join successful.");
            System.out.println("===== Redirecting to challenge page.");
            ChallengeView.challenge = challenge;
            Intent intent = new Intent(activity, ChallengeView.class);
            activity.startActivity(intent);
        }
        else {
            ((join_group)activity).setMessage(getMessage());
        }
    }
}
