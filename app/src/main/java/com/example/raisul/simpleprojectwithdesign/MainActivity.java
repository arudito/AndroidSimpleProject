package com.example.raisul.simpleprojectwithdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    //The View Objects
    private Toolbar toolbar;

    private Button buttonSubmit;

    private AwesomeValidation aValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initializing validation objects
        aValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //initializing view objects
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        //adding validation
        aValidation.addValidation(this, R.id.editTextName, Patterns.EMAIL_ADDRESS, R.string.nameerror);
        aValidation.addValidation(this, R.id.editTextPassword, getString(R.string.regexPassword), R.string.passworderror);

        buttonSubmit.setOnClickListener(this);
    }

    private void submitForm() {
        //first validate the form then move ahead
        //if this becomes true that means validation is successfull

        if (aValidation.validate()) {
            Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();
            //process the data further
            final Intent gallery = new Intent(this, HomeActivity.class);
            startActivity(gallery);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        if(view == buttonSubmit){
            submitForm();
        }
    }
}
