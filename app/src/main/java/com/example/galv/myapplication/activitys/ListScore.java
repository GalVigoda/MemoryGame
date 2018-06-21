package com.example.galv.myapplication.activitys;

import android.os.Bundle;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.galv.myapplication.DatabaseHelper;
import com.example.galv.myapplication.R;
import com.example.galv.myapplication.User;
import com.example.galv.myapplication.column_listAdapter;

import java.util.ArrayList;

public class ListScore extends AppCompatActivity {

    DatabaseHelper myDB;
    ArrayList<User> userList; // string ==name+score
    ListView listView;
    User user;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_one_layout_list);

        myDB = new DatabaseHelper(this);

        userList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(ListScore.this, "The Database is empty  :(.", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                user = new User(data.getString(1), data.getString(2), data.getString(3));
                userList.add(i, user);
                System.out.println(data.getString(1) + " " + data.getString(2) + " " + data.getString(3));
                System.out.println(userList.get(i).getFirstName());
                i++;
            }
            column_listAdapter adapter = new column_listAdapter(this, R.layout.list_adapter, userList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
    }
}


