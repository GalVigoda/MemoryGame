package com.example.galv.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//package com.galv.android.projectnumber_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText etFirstName;
    EditText etLastName;
    EditText etAge;
    Button btNext;
    EditText etError;
    String regexStr = "^[0-9]*$";
    String errorUser = "Invalid input";
    String firstName;
    String lastName;
    String age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewOfId();
        setOnClickButton();//


    }

    private void setOnClickButton() {
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getTheText();


                if (checkValid() == true) {

                    Intent intent = new Intent(MainActivity.this, SecendActivity.class);
                    intent.putExtra("F_NAME", firstName);
                    intent.putExtra("L_NAME", lastName);
                    intent.putExtra("AGE", age);

                    startActivity(intent);

                } else
                    etError.setText(errorUser);
            }

            boolean checkValid() {
                if (firstName.length() > 0
                        && lastName.length() > 0
                        && age.length() > 0
                        && age.trim().matches(regexStr))
                    return true;
                return false;

            }


        });

    }

    private void findViewOfId() {
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etAge = (EditText) findViewById(R.id.etAge);
        etError = (EditText) findViewById(R.id.etError);
        btNext = (Button) findViewById(R.id.btNext);
    }


//    private void newIntent(){
//        Intent intent = new Intent(getBaseContext(etFirstName.getText()), SecendActivity.class);
//        intent.putExtra("firstName", sessionId);
//        startActivity(intent);
//        }


    private void getTheText() {
        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();
        age = etAge.getText().toString();
    }

}
