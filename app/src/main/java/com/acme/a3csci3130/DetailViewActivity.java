package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Description: Shows current contact info
 */
public class DetailViewActivity extends Activity {

    private EditText nameField, businessNumberField,
            primaryBusinessField, addressField, province_territoryField;
    BusinessContact receivedPersonInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (BusinessContact)getIntent().getSerializableExtra("BusinessContact");

        nameField = (EditText) findViewById(R.id.name);
        businessNumberField = (EditText) findViewById(R.id.businessNumber);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        province_territoryField = (EditText) findViewById(R.id.province_territory);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            businessNumberField.setText(receivedPersonInfo.businessNumber);
            primaryBusinessField.setText(receivedPersonInfo.primaryBusiness);
            addressField.setText(receivedPersonInfo.address);
            province_territoryField.setText(receivedPersonInfo.province_territory);
        }
    }

    /**
     * Description: update contact information
     * @param v current view
     */
    public void updateContact(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String businessNumber = businessNumberField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String address = addressField.getText().toString();
        String province_territory = province_territoryField.getText().toString();

        BusinessContact person = new BusinessContact(personID, name, businessNumber,
                primaryBusiness,address,province_territory);

        appState.firebaseReference.child(personID).setValue(person);

        finish();
    }

    /**
     * Description: Deletes current contact
     * @param v the current view
     */
    public void eraseContact(View v) {
        String personID = appState.firebaseReference.push().getKey();
        appState.firebaseReference.child(personID).removeValue();
    }
}
