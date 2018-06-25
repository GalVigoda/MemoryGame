package com.example.galv.myapplication.activitys;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class Map extends AppCompatActivity {
    private static final String TAG = "Map";
    private static final int ERROR_DIALOG_REQUEST = 9001;

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
