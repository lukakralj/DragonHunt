package com.example.lukak.dragonhunt.backend;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    protected String url;
    protected Map<String, String> parameters;
    protected JSONObject response;
    protected boolean wasSuccessful;
    protected String message;

    public HttpRequest() {
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

            response = new JSONObject(responseJSON);
            wasSuccessful = response.get("success").equals("1");
            message = (String) response.get("message");
        }
        catch (IOException | JSONException e) {
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

}
