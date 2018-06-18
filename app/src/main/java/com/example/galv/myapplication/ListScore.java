package com.example.galv.myapplication;

import android.os.Bundle;
import android.database.Cursor;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListScore extends AppCompatActivity{

    DatabaseHelper myDB;
    ArrayList<User> userList;
    ListView listView;
    User user;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_one_layout_list);

        myDB=new DatabaseHelper(this);
        userList= new ArrayList<>();
        Cursor data= myDB.getListContents();
        int numberOfRows = data.getColumnCount();
        if(numberOfRows==0) {
            Toast.makeText(ListScore.this, "empty Table nothing to show", Toast.LENGTH_SHORT).show();
            }else{
                while (data.moveToNext()){
                    user=new User(data.getString(0),data.getLong(1));//0== NAME , 1==SCORE
                userList.add(user);
                }

                column_listAdapter adapter = new column_listAdapter(this,R.layout.list_adapter,userList);
                listView=(ListView) findViewById(R.id.theListScore);
                listView.setAdapter(adapter);
        }

    }
}
