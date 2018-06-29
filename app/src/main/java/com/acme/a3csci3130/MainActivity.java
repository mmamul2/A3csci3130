package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Main Activity for the app
 * @author Editted by Matthew MacMullin
 */
public class MainActivity extends Activity {

    private ListView businessListView;
    private FirebaseListAdapter<Business> firebaseAdapter;

    /**
     * Method that sets up and populates the main activity when it is created.
     *
     * @param savedInstanceState - Saved state from previous tiems the main activity was running.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the app wide shared variables
        final MyApplicationData appData = (MyApplicationData)getApplication();

        //Set-up Firebase
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReference("businesses");

        //Get the reference to the UI contents
        businessListView = (ListView) findViewById(R.id.listView);

        //Set up the List View
        firebaseAdapter = new FirebaseListAdapter<Business>(this, Business.class,
                android.R.layout.simple_list_item_2, appData.firebaseReference) {
            @Override
            protected void populateView(View v, Business model, int position) {
                TextView businessName = (TextView)v.findViewById(android.R.id.text1);
                businessName.setText(model.getName());
                TextView businessNumber = (TextView)v.findViewById(android.R.id.text2);
                businessNumber.setText(model.getBusinessNumber());
                businessName.setTag(model.getName());
            }
        };

        businessListView.setAdapter(firebaseAdapter);
        businessListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //onItemClick method is called every time a user clicks an item on the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Business person = firebaseAdapter.getItem(position);
                appData.firebaseReference = firebaseAdapter.getRef(position);
                showDetailView(person);
            }
        });
    }

    /**
     * Method to switch to the new business entry activity
     * @param v - view that ios clicked
     */
    public void createContactButton(View v)
    {
        Intent intent=new Intent(this, CreateBusinessActivity.class);
        startActivity(intent);
    }

    /**
     * Method that is called when a List view entry is clicked
     * @param business - business object related to the list entry that was clicked
     */
    private void showDetailView(Business business)
    {
        Intent intent = new Intent(this, DetailViewActivity.class);
        intent.putExtra("Business", business);
        startActivity(intent);
    }
}
