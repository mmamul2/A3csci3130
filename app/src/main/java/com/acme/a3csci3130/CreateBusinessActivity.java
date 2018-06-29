package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class CreateBusinessActivity extends Activity {

    private Button submitButton;
    private MyApplicationData appState;
    private EditText nameField, primaryBusinessField, numberField, addressField;
    private Spinner provinceField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        numberField = (EditText) findViewById(R.id.number);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (Spinner) findViewById(R.id.provinceSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.provinces, android.R.layout.simple_spinner_item);
        provinceField.setAdapter(adapter);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        Business newBusiness = new Business();
        String newKey = appState.firebaseReference.push().getKey();
        newBusiness.setName(nameField.getText().toString());
        newBusiness.setPrimaryBusiness(primaryBusinessField.getText().toString());
        newBusiness.setBusinessNumber(numberField.getText().toString());
        newBusiness.setAddress(addressField.getText().toString());
        newBusiness.setProvince(provinceField.getSelectedItem().toString());

        appState.firebaseReference.child(newKey).setValue(newBusiness,
                new DatabaseReference.CompletionListener(){
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference){
                if(databaseError != null){
                    Toast toast = Toast.makeText(appState, "Error: Creation failed!",
                            Toast.LENGTH_LONG);
                    toast.show();

                } else {
                    finish();
                    Toast toast = Toast.makeText(appState, "Business entry created",
                            Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }
}
