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
 * Description: The primary activity of the app
 */
public class MainActivity extends Activity {

    private ListView contactListView;
    private FirebaseListAdapter<BusinessContact> firebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the app wide shared variables
        MyApplicationData appData = (MyApplicationData)getApplication();

        //Set-up Firebase
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReference("businesses");

        //Get the reference to the UI contents
        contactListView = (ListView) findViewById(R.id.listView);

        //Set up the List View
       firebaseAdapter = new FirebaseListAdapter<BusinessContact>(this, BusinessContact.class,
                android.R.layout.simple_list_item_1, appData.firebaseReference) {
            @Override
            protected void populateView(View v, BusinessContact model, int position) {
                TextView contactName = (TextView)v.findViewById(android.R.id.text1);
                contactName.setText(model.name);
            }
        };
        contactListView.setAdapter(firebaseAdapter);
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // onItemClick method is called everytime a user clicks an item on the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BusinessContact person = (BusinessContact) firebaseAdapter.getItem(position);
                showDetailView(person);
            }
        });
    }

    /**
     * Description: a button to submit contact info to create a new contact
     * @param v the current view
     */
    public void createContactButton(View v)
    {
        Intent intent=new Intent(this, CreateContactAcitivity.class);
        startActivity(intent);
    }

    /**
     * Description: shows the details of the contact
     * @param person the object that holds all of the business contact info
     */
    private void showDetailView(BusinessContact person)
    {
        Intent intent = new Intent(this, DetailViewActivity.class);
        intent.putExtra("BusinessContact", person);
        startActivity(intent);
    }

    /**
     * Description: returns count of children in ListView (databinded to firebase)
     * @return count of children in ListView (databinded to firebase)
     */
    public int getCount () {
        return firebaseAdapter.getCount();
    }

}
