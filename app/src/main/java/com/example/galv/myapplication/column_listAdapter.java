package com.example.galv.myapplication;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class column_listAdapter extends ArrayAdapter<User> {

    private LayoutInflater mInflater;
    private ArrayList<User> users;
    private int mViewResourceId;

    public column_listAdapter(Context context, int textViewResourceId, ArrayList<User> users) {
        super(context, textViewResourceId, users);
        this.users = users;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;

    }

    public View getView(int position, View converView, ViewGroup parents) {
        converView = mInflater.inflate(mViewResourceId, null);
        User user = users.get(position);

            TextView name = (TextView) converView.findViewById(R.id.textName);
            TextView points = (TextView) converView.findViewById(R.id.Score);

            name.setText(user.getName());
            points.setText(user.getName());
            return converView;
    }
}
