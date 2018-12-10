package com.dragonhunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchGroups extends AppCompatActivity {

    ArrayList<Group> groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_groups);
        RecyclerView rvGroups = (RecyclerView) findViewById(R.id.rvGroups);
        groups = Group.createGroupsList(10);
        GroupsAdapter adapter = new GroupsAdapter(groups);
        rvGroups.setAdapter(adapter);
        rvGroups.setLayoutManager(new LinearLayoutManager(this));

        rvGroups.addOnItemTouchListener(
                new RecyclerItemClickListener(rvGroups.getContext(), rvGroups ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        startActivity(new Intent(SearchGroups.this, GroupInformation.class));
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        startActivity(new Intent(SearchGroups.this, GroupInformation.class));
                    }
                })
        );

    }


}
