package com.example.lukak.dragonhunt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DatabaseManager {

    public static void main(String[] args) {
        try {
            String url = "http://dragonhunt.dx.am/new_user.php";
            String parameters = "username=lukakralj&password=simplePassword&name=Luka&surname=Kralj";

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
            System.out.println(content);
            in.close();

            con.disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
