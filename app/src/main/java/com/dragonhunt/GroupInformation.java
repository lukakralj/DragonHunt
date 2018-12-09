package com.dragonhunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;



public class GroupInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_information);
        TextView tv1 = (TextView)findViewById(R.id.memberlist);
        tv1.setText("\nXinyi_666\nNoyan_404\nLuka_1337\nJake_420");
        TextView tv2 = (TextView)findViewById(R.id.group_info);
    }
}
