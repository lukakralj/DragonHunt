package com.example.lukak.dragonhunt.backend;

public class NewUserRequest extends HttpRequest {

    public NewUserRequest() {
        super();
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


}