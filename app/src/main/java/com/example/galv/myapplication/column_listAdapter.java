package com.example.galv.myapplication;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.galv.myapplication.R;
import com.example.galv.myapplication.User;

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

        if (user != null) {
            TextView firstName = (TextView) converView.findViewById(R.id.textFirstName);
            TextView lastName = (TextView) converView.findViewById(R.id.textLastName);
            TextView favFood = (TextView) converView.findViewById(R.id.textFavFood);
            if (firstName != null) {
                firstName.setText(user.getFirstName());
            }
            if (lastName != null) {
                lastName.setText((user.getLastName()));
            }
            if (favFood != null) {
                favFood.setText((user.getFavFood()));
            }
        }

        return converView;
    }
}

            //
//            name.setText(user.getName());
//            points.setText((int) user.getPoint());
//            return conveArView;
//        } else {
//            TextView name = (TextView) converView.findViewById(R.id.textName);
//            name.setText("not good");
//        }
//    return converView;
//    }

//}