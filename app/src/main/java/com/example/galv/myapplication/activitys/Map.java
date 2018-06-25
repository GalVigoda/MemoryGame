package com.example.galv.myapplication.activitys;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import com.example.galv.myapplication.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class Map implements OnMapReadyCallback {


    private GoogleMap mMap;
    private int latitude, longitude;
    private Geocoder gc;
    private LatLng MyLatLng;
    private MarkerOptions markerOptionsMyLocation;

//    private static final String TAG = "Map";
//    private static final int ERROR_DIALOG_REQUEST = 9001;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        if(isServiceOk()){
//            init();
//        }
//
//    }


//    public boolean isServiceOk(){
//        Log.d(TAG,"isServiceOk: checking google service version"); //log for details
//        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Map.this);
//
//        if(available == ConnectionResult.SUCCESS){
//            //everything is fine and the user can make map requests
//            Log.d(TAG, "isServicesOK: Google Play Services is working");
//            return true;
//        }
//        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
//            //an error occured but we can resolve it
//            Log.d(TAG, "isServicesOK: an error occured but its can fix it");
//            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Map.this, available, ERROR_DIALOG_REQUEST);
//            dialog.show();
//        }else{
//            Toast.makeText(this, "You can't make the map requests", Toast.LENGTH_SHORT).show();
//        }
//        return false;
//    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            mMap = googleMap;
            setMyLocationOnTheMap();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setMyLocationOnTheMap() throws IOException {
        //to specific location
        MyLatLng = new LatLng(latitude, longitude);
        markerOptionsMyLocation = new MarkerOptions();

        //to specific address
        List<Address> ls=gc.getFromLocation(latitude, longitude, 1);
        android.location.Address address=ls.get(0);
        String street=address.getAddressLine(0);

        //Place current location marker
        markerOptionsMyLocation.position(MyLatLng);
        markerOptionsMyLocation.title("Current Position");
        markerOptionsMyLocation.snippet("Location: " + street);
        mMap.addMarker(markerOptionsMyLocation);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(MyLatLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
    }

    public void moveCameraToCurrentPos() {
        mMap.clear();
        mMap.addMarker(markerOptionsMyLocation);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(MyLatLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
    }

}

