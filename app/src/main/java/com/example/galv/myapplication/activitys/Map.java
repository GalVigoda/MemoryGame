package com.example.galv.myapplication.activitys;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import com.example.galv.myapplication.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.OnMapReadyCallback;

public class Map implements OnMapReadyCallback {

    private static final String TAG = "Map";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isServiceOk()){
            init();
        }

    }


    public boolean isServiceOk(){
        Log.d(TAG,"isServiceOk: checking google service version"); //log for details
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Map.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but its can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Map.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make the map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    }




}
