package com.example.galv.myapplication.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//package com.galv.android.projectnumber_1;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.galv.myapplication.R;

public class MainActivity extends AppCompatActivity {


    EditText etFirstName;
    EditText etAge;
    EditText etError;

    Button btNext;
    Button Result;

    String regexStr = "^[0-9]*$";
    String errorUser = "Invalid input";
    String name;
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
                    intent.putExtra("Name", name);
                    //intent.putExtra("L_NAME", lastName);
                    intent.putExtra("AGE", age);

                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                } else
                    etError.setText(errorUser);
            }

        });
        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(MainActivity.this, ListScore.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }

        });

    }

    boolean checkValid() {
        if (name.length() > 0
                && age.length() > 0
                && age.trim().matches(regexStr))
            return true;
        return false;

    }

    private void findViewOfId() {
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etAge = (EditText) findViewById(R.id.etAge);
        etError = (EditText) findViewById(R.id.etError);
        btNext = (Button) findViewById(R.id.btNext);
        Result = (Button) findViewById(R.id.Result);
    }

//    private void newIntent(){
//        Intent intent = new Intent(getBaseContext(etFirstName.getText()), SecendActivity.class);
//        intent.putExtra("firstName", sessionId);
//        startActivity(intent);
//        }

    private void getTheText() {
        name = etFirstName.getText().toString();
        age = etAge.getText().toString();
    }

}
