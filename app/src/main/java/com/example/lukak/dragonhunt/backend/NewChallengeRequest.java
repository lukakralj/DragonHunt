package com.example.lukak.dragonhunt.backend;

public class NewChallengeRequest extends HttpRequest {

    public NewChallengeRequest() {
        super();
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
        return super.issueRequest();
    }

}
