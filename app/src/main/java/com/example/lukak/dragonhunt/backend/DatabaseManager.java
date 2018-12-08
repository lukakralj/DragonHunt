package com.example.lukak.dragonhunt.backend;

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

    public static String createParameters(Map<String, String> postArguments) {
        StringBuilder builder = new StringBuilder();
        for (String key : postArguments.keySet()) {
            builder.append(key);
            builder.append("=");
            builder.append(postArguments.get(key));
            builder.append("&");
        }
        return builder.substring(0, builder.length() - 2); // remove final &
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
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();

        return content.toString();
    }


}