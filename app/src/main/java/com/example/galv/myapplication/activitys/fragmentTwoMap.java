package com.example.galv.myapplication.activitys;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.galv.myapplication.R;

public class fragmentTwoMap extends Fragment {

      //  private LocationManager locationManager;
       // private LocationListener locationListener;

      //  DatabaseHelper dbHelper;

     //   @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_two_layout_map,container,false);

       }



}
