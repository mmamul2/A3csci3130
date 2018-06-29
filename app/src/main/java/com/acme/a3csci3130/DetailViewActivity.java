package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;


public class DetailViewActivity extends Activity {
    private MyApplicationData appState;
    private EditText nameField, primaryBusinessField, numberField, addressField;
    private Spinner provinceField;
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
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (Spinner) findViewById(R.id.provinceSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.provinces, android.R.layout.simple_spinner_item);
        provinceField.setAdapter(adapter);

        if(receivedBusinessInfo != null){
            nameField.setText(receivedBusinessInfo.getName());
            primaryBusinessField.setText(receivedBusinessInfo.getPrimaryBusiness());
            numberField.setText(receivedBusinessInfo.getBusinessNumber());
            addressField.setText(receivedBusinessInfo.getAddress());
            provinceField.setSelection(adapter.getPosition(receivedBusinessInfo.getProvince()));
        }
    }

    public void updateContact(View v){
        receivedBusinessInfo.setName(nameField.getText().toString());
        receivedBusinessInfo.setBusinessNumber(numberField.getText().toString());
        receivedBusinessInfo.setPrimaryBusiness(primaryBusinessField.getText().toString());
        receivedBusinessInfo.setAddress(addressField.getText().toString());
        receivedBusinessInfo.setProvince(provinceField.getSelectedItem().toString());

        appState.firebaseReference.setValue(receivedBusinessInfo, new DatabaseReference.CompletionListener(){
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError != null){
                    Toast toast = Toast.makeText(appState, "Error: Update failed!", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    finish();
                    Toast toast = Toast.makeText(appState, "Business entry updated", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    public void eraseContact(View v)
    {
        appState.firebaseReference.removeValue(new DatabaseReference.CompletionListener(){
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError != null){
                    Toast toast = Toast.makeText(appState, "Error: Delete failed!", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    finish();
                    Toast toast = Toast.makeText(appState, "Business entry deleted", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}
