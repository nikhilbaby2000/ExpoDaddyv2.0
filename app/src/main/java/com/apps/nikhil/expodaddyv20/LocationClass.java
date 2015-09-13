package com.apps.nikhil.expodaddyv20;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

import java.text.DateFormat;
import java.util.Date;


/**
 * Created by Nikhil on 30-08-2015.
 */
public class LocationClass implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private double latitude;
    private double longitude;
    Context context;
    private String provider, result;
    MoveToNewActivity moveToNewActivity = new MoveToNewActivity();

    EditText locValue;
    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private boolean mRequestingLocationUpdates = true;
    private String mLastUpdateTime;
    private Location mCurrentLocation;

   public LocationClass() {

       createLocationRequest();

   }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(3000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    protected void startLocationUpdates() {
        //LocationServices.FusedLocationApi.requestLocationUpdates(
              //  mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnected(Bundle bundle) {

        if (mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }





    public String getNetworkProvidedLocation(Context context) {
        LocationManager locManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

        boolean network_enabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Location location;

        if(network_enabled){

            location = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if(location!=null){
                longitude = location.getLongitude();
                latitude = location.getLatitude();
            }
        }
        return (latitude+","+longitude);
    }



    public String getGPSLocation(Context context) {
        this.context = context;
        LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        //Check if gps enabled
        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!enabled) {
            moveToNewActivity.moveToGPSSettings(context);
        }

        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            Toast.makeText(context, "Provider " + provider + " has been selected.", Toast.LENGTH_LONG).show();
            onLocationChanged(location);
        } else {
            result=getNetworkProvidedLocation(context);
        }

        return result;

    }


    @Override
    public void onLocationChanged(Location location) {

        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());

        result=location.getLatitude()+","+location.getLongitude();
        Toast.makeText(context,result,Toast.LENGTH_LONG).show();


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(context, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(context, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }
}
