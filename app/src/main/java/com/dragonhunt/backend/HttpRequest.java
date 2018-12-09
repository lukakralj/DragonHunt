package com.dragonhunt.backend;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest extends AsyncTask<String, Void, String> {

    protected Activity activity;
    protected String url;
    protected Map<String, String> parameters;
    protected JSONObject response;
    protected boolean wasSuccessful;
    protected String message;

    public HttpRequest(Activity activity) {
        this.activity = activity;
        parameters = new HashMap<>();
        wasSuccessful = false;
    }

    /**
     *
     * @return True if no exceptions.
     */
    public boolean issueRequest() {
        try {
            String responseJSON = DatabaseManager.getResponseJSON(url, DatabaseManager.createParameters(parameters));

            System.out.println("====== Request type: " + this.getClass());
            System.out.println("====== ResponseJSON: " + responseJSON);
            response = new JSONObject(responseJSON);
            System.out.println("====== Response: " + response);
            wasSuccessful = response.getInt("success") == 1;
            System.out.println("====== wasSuccessful: " + wasSuccessful);
            message = (String) response.get("message");
            System.out.println("====== Message: " + message);
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean wasSuccessful() {
        return wasSuccessful;
    }

    public String getMessage() {
        return message;
    }

    @Override
    protected String doInBackground(String... strings) {
        issueRequest();

        return "Executed";
    }

    @Override
    protected void onPostExecute(String result) {

    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}

}
