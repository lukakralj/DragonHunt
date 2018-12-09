package com.dragonhunt;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class Group {
    private String mName;
    private int mBackground;
    private int mChallengeComplete;
    private int mActive;
    private int mRequired;
    private double mDistance;

    public Group(String name, int background, int numChallengeComplete, int numActive, int numRequired, double distanceFrom) {
        mName = name;
        mBackground = background;
        mChallengeComplete = numChallengeComplete;
        mActive = numActive;
        mRequired = numRequired;
        mDistance = distanceFrom;
    }

    public String getName() {
        return mName;
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

    public int getmBackground() {
        return mBackground;
    }

    private static int lastGroupId = -1;

    public static ArrayList<Group> createGroupsList(int numGroups) {
        ArrayList<Group> groups = new ArrayList<Group>();

        String[] namearray = {"DragonSlayers", "//TODO:Team_Name", "TechSupport", "Clownbois", "Codebusters", "404NameNotFound", "GoldDiggers", "Prawn Stars", "HashSlashers", "Aquaholics"};
        int[] numarray1 = {219, 121, 88, 714, 91, 47, 312, 66, 102, 94, 416, 68};
        int[] numarray2 = {1, 4, 6, 2, 1, 4, 5, 2, 4, 3, 7, 1};
        int[] numarray3 = {3, 6, 9, 3, 3, 5, 7, 4, 5, 5, 11, 2};
        double[] numarray4 = {0.21, 2.11, 0.65, 1.91, 1.42, 0.87, 0.47, 0.19, 1.51, 1.78, 1.52, 2.78};
        int[] drawarray = {R.drawable.dragon10, R.drawable.testimage1, R.drawable.dragon2, R.drawable.dragon3, R.drawable.dragon4, R.drawable.dragon5, R.drawable.dragon6, R.drawable.dragon7, R.drawable.dragon8, R.drawable.dragon9};

        /*
        for (int i = 1; i <= numGroups; i++) {
            groups.add(new Group("Group " + ++lastGroupId, "@drawable/testimage1", numarray1[++lastGroupId], numarray2[++lastGroupId], numarray3[++lastGroupId], numarray4[++lastGroupId]));
        }
        */

        for (int i = 1; i <= numGroups; i++) {
            groups.add(new Group(namearray[i-1], drawarray[i-1] ,numarray1[i-1], numarray2[i-1], numarray3[i-1], numarray4[i-1]));
        }

        return groups;
    }
}