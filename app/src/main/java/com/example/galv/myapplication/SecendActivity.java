package com.example.galv.myapplication;
//
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;*/

public class SecendActivity extends MainActivity implements View.OnClickListener{


    private TextView tvDetailsUser;
    private String userName;
    private String lastName;
    private String age;
    private TextView messageDisplay;
    private int Easy= 2 , Noraml=4, Hard=6;
    private int levels[]= {Easy,Noraml,Hard};
    private int level;
    private Button btEasy,btNormal,btHard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secend);

        getIntents();
        findViewOfButton();
        setTheText();
        // Bundle extras= getIntent().getExtras();



//
//        btEazy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SecendActivity.this, ThirdActivity.class);
//                intent.putExtra("level",levels[0]);
//                startActivity(intent);
//            }
//        });
//
//
//        btNormal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(SecendActivity.this, ThirdActivity.class);
//                intent.putExtra("level",levels[1]);
//                startActivity(intent);
//            }
//
//
//        });
//        btHard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(SecendActivity.this, ThirdActivity.class);
//                intent.putExtra("level",levels[2]);
//                startActivity(intent);
//            }
//
//
//        });
//        btEazy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SecendActivity.this, ThirdActivity.class);
//                intent.putExtra("level",levels[0]);
//                startActivity(intent);
//            }
//        });
//
//
//        btNormal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(SecendActivity.this, ThirdActivity.class);
//                intent.putExtra("level",levels[1]);
//                startActivity(intent);
//            }
//
//
//        });
//        btHard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(SecendActivity.this, ThirdActivity.class);
//                intent.putExtra("level",levels[2]);
//                startActivity(intent);
//            }
//
//
//        });
    }



    private void setTheText() {
        tvDetailsUser.setText("Welcome " + userName +" ,"+ age +"\n\n");
        messageDisplay.setText("Level:");
    }


    private void getIntents() {
        userName=getIntent().getExtras().getString("Name");
        //lastName=getIntent().getExtras().getString("L_NAME");
        age=getIntent().getExtras().getString("AGE");
    }

    private void findViewOfButton() {
        tvDetailsUser=(TextView)findViewById(R.id.theTvDetailsUser);
        messageDisplay=findViewById(R.id.tvMessageDisplay);
        btEasy=(Button)findViewById(R.id.btEasy);
        btNormal=(Button)findViewById(R.id.btNormal);
        btHard=(Button)findViewById(R.id.btHard);

        setOnClickListener();
    }

    private void setOnClickListener() {
        btEasy.setOnClickListener(this);
        btNormal.setOnClickListener(this);
        btHard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btEasy:
                level=levels[0];
                break;
            case R.id.btNormal:
                level=levels[1];
                break;
            case R.id.btHard:
                level=levels[2];
                break;
        }
        Intent intent = new Intent(SecendActivity.this, ThirdActivity.class);
        intent.putExtra("level",level);
        intent.putExtra("userName",userName);
        startActivity(intent);
    }

}
