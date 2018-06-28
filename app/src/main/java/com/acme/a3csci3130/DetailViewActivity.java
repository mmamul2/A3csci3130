package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class DetailViewActivity extends Activity {
    private MyApplicationData appState;
    private EditText nameField, primaryBusinessField, numberField, provinceField, addressField;
    Business receivedBusinessInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        appState = ((MyApplicationData) getApplicationContext());
        receivedBusinessInfo = (Business)getIntent().getSerializableExtra("Business");

        nameField = (EditText) findViewById(R.id.name);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        numberField = (EditText) findViewById(R.id.number);
        provinceField = (EditText) findViewById(R.id.province);
        addressField = (EditText) findViewById(R.id.address);

        if(receivedBusinessInfo != null){
            nameField.setText(receivedBusinessInfo.getName());
            primaryBusinessField.setText(receivedBusinessInfo.getPrimaryBusiness());
            numberField.setText(receivedBusinessInfo.businessNumber.toString());
            addressField.setText(receivedBusinessInfo.getAddress());
            provinceField.setText(receivedBusinessInfo.getProvince());
        }
    }

    public void updateContact(View v){
        receivedBusinessInfo.setName(nameField.getText().toString());
        receivedBusinessInfo.setBusinessNumber(numberField.getText().toString());
        receivedBusinessInfo.setPrimaryBusiness(primaryBusinessField.getText().toString());
        receivedBusinessInfo.setAddress(addressField.getText().toString());
        receivedBusinessInfo.setProvince(provinceField.getText().toString());

        appState.firebaseReference.setValue(receivedBusinessInfo);
    }

    public void eraseContact(View v)
    {
        appState.firebaseReference.removeValue();
       //need to transition back to list, probably will for update too, if so will make a method to handle it
    }
}
