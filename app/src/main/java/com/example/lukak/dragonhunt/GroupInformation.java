package com.example.lukak.dragonhunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GroupInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_information);
        TextView tv1 = (TextView)findViewById(R.id.memberlist);
        tv1.setText("\nHello\nGoodbye\nhello again\njk bye nerd");
    }
}
