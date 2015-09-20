package com.apps.nikhil.expodaddyv20.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.apps.nikhil.expodaddyv20.FileReaderWriterClass;
import com.apps.nikhil.expodaddyv20.JsonUtil;
import com.apps.nikhil.expodaddyv20.LocationClass;
import com.apps.nikhil.expodaddyv20.MoveToNewActivity;
import com.apps.nikhil.expodaddyv20.Person;
import com.apps.nikhil.expodaddyv20.R;
import com.google.android.gms.common.api.GoogleApiClient;

public class Register extends ActionBarActivity  {

    private int enable_disable = 0; //for value 5 or above enable the register button, until then display necessary error msgs.
    MoveToNewActivity moveToNewActivity = new MoveToNewActivity();
    LocationClass locationClass = new LocationClass();
    private GoogleApiClient mGoogleApiClient;

    EditText locValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button register = (Button) findViewById(R.id.register_submit_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });


        Button getLocation = (Button) findViewById(R.id.location_button);
        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locValue = (EditText) findViewById(R.id.location_value);
                Context context = Register.this;
                //locValue.setText(locationClass.getNetworkProvidedLocation(Register.this) );
                locValue.setText(locationClass.getGPSLocation(context));


            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    

    private class Holder{
        EditText name, email, password, passwordConfirm, age, phone, zip, location;
        ToggleButton gender;
        Holder(){

            name = (EditText) findViewById(R.id.name_value);
                if (name.getText().toString().length() == 0)  name.setError("Name is required"); else enable_disable++;
            email = (EditText) findViewById(R.id.email_value);
                if (email.getText().toString().length() == 0)  email.setError("Email is required"); else enable_disable++;

            password = (EditText) findViewById(R.id.password_value);
                if (password.getText().toString().length() == 0)  password.setError("Password is required"); else enable_disable++;

            age = (EditText) findViewById(R.id.age_value);
            gender = (ToggleButton) findViewById(R.id.gender_value_toggleButton);
            phone = (EditText) findViewById(R.id.phone_value);
                if (phone.getText().toString().length() == 0)  phone.setError("PhoneNumber is required"); else enable_disable++;

            zip = (EditText) findViewById(R.id.zipCode_value);
            location = (EditText) findViewById(R.id.location_value);
            passwordConfirm = (EditText) findViewById(R.id.password_confirm_value);
                if (passwordConfirm.getText().toString().length() == 0)  passwordConfirm.setError("Password needs to be confirmed"); else enable_disable++;

        }

    }

    public void registerNewUser() {
        Person person = new Person();
        Holder holder = new Holder();

        person.setName(holder.name.getText().toString());
        person.setEmail(holder.email.getText().toString());
        person.setPassword(holder.password.getText().toString());
        person.setAge(holder.age.getText().toString());
        person.setPhone(holder.phone.getText().toString());
        person.setZip(holder.zip.getText().toString());
        person.setLocation(holder.location.getText().toString());
        person.setGender(holder.gender.getText().toString());

        JsonUtil convertedJSON = new JsonUtil();
        String jsonString = convertedJSON.toJSon(person);

        if (holder.password.getText().toString().equals(holder.passwordConfirm.getText().toString()) && enable_disable>=5) {
            Toast.makeText(getApplicationContext(), jsonString, Toast.LENGTH_LONG).show();

            //Write User Data to file
            FileReaderWriterClass dataWriter = new FileReaderWriterClass();
            dataWriter.writeToFile(Register.this, jsonString + "" );

            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setMessage(jsonString)
                    .setIcon(R.drawable.ic_launcher)
                    .setPositiveButton("Ok",null)
                    .setNegativeButton("Nope",null)
                    .setNeutralButton("Cancel",null)
                    .setCancelable(false)
                    .create();

            alertDialog.show();
        }
        else
           Toast.makeText(getApplicationContext(),"Password Mismatch or Validation errors exist",Toast.LENGTH_SHORT).show();
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            moveToNewActivity.moveToLoginActivity(this);
        }
        return true;
    }
}
