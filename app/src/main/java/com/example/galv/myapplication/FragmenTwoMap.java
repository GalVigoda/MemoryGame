package com.example.galv.myapplication;

import android.app.Fragment;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmenTwoMap extends Fragment{

    private LocationManager locationManager;
    private LocationListener locationListener;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two_layout_map,container,false);
    }

//    protected  void OnCreate (Bundle bundle){
//
//        locationManager= (LocationManager)getSys
//    }
}
