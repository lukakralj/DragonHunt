package com.dragonhunt;

import java.util.ArrayList;

public class Group {
    private String mName;
    private String mImage;
    private int mChallengeComplete;
    private int mActive;
    private int mRequired;
    private double mDistance;

    public Group(String name, String imageFile, int numChallengeComplete, int numActive, int numRequired, double distanceFrom) {
        mName = name;
        mImage = imageFile;
        mChallengeComplete = numChallengeComplete;
        mActive = numActive;
        mRequired = numRequired;
        mDistance = distanceFrom;
    }

    public String getName() {
        return mName;
    }

    public String getImage() {
        return mImage;
    }

    public int getNumComplete() {
        return mChallengeComplete;
    }

    public int getActive() {
        return mActive;
    }

    public int getRequired() {
        return mRequired;
    }

    public double getDistance() {
        return mDistance;
    }

    private static int lastGroupId = 0;

    public static ArrayList<Group> createGroupsList(int numGroups) {
        ArrayList<Group> groups = new ArrayList<Group>();

        for (int i = 1; i <= numGroups; i++) {
            groups.add(new Group("Group " + ++lastGroupId, "@drawable/testimage1", 100, 1, 5, 0.44));
        }

        return groups;
    }
}