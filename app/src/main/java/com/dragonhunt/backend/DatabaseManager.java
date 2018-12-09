package com.dragonhunt.backend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.dragonhunt.LoginPage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class DatabaseManager {

    public static final String NEW_USER_URL = "http://dragonhunt.dx.am/new_user.php";
    public static final String NEW_CHALLENGE_URL = "http://dragonhunt.dx.am/new_challenge.php";
    public static final String LOGIN_URL = "http://dragonhunt.dx.am/login.php";

    // Status constants:
    public static final String QUERY_SUCCESSFUL = "QUERY_SUCCESSFUL";
    public static final String QUERY_ERROR = "QUERY_ERROR";
    public static final String INVALID_USERNAME = "INVALID_USERNAME";
    public static final String MISSING_PARAMETERS = "MISSING_PARAMETERS";
    public static final String INVALID_REQUEST = "INVALID_REQUEST";
    public static final String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";

    public static String createParameters(Map<String, String> postArguments) {
        StringBuilder builder = new StringBuilder();
        for (String key : postArguments.keySet()) {
            builder.append(key);
            builder.append("=");
            builder.append(postArguments.get(key));
            builder.append("&");
        }
        return builder.substring(0, builder.length() - 1); // remove final &
    }

    public static String getResponseJSON(String url, String parameters) throws IOException {
        URL u = new URL(url);

        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        con.setRequestMethod("POST");

        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(parameters);
        out.flush();
        out.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();
        return content.toString();
    }


    public static void requireLogin(Activity activity) {
        SharedPreferences preferences = activity.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        if (!preferences.contains("username")) {
            // no user is logged in
            Intent intent = new Intent(activity, LoginPage.class);
            activity.startActivity(intent);
        }
    }
}
