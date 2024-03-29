package com.dragonhunt;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {



    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {

        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView statTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.group_description);
            statTextView = (TextView) itemView.findViewById(R.id.group_stats);
        }

    }

    // Store a member variable for the contacts
    private List<Group> mGroups;

    // Pass in the contact array into the constructor
    public GroupsAdapter(List<Group> groups) {
        mGroups = groups;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public GroupsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View groupView = inflater.inflate(R.layout.team_button, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(groupView);
        return viewHolder;
    }


    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(final GroupsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Group group = mGroups.get(position);

        // Set item views based on your views and data model
        TextView textView1 = viewHolder.nameTextView;
        textView1.setText(group.getName());
        TextView textView2 = viewHolder.statTextView;
        textView2.setText("Completed Challenges\n\t\t\t\t"+ group.getNumComplete() +"\nActive Members\n\t\t\t\t"+ group.getActive() +"/" + group.getRequired()+"\nDistance\n\t\t\t\t" + group.getDistance() + " km");
        textView1.setBackgroundResource(group.getmBackground());

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mGroups.size();
    }

}