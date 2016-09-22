package com.example.nazmul.hospitalfinder;

import android.app.Dialog;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class MapActivity extends FragmentActivity {
    private static final int GPS_ERRORDIALOG_REQUEST = 9001;

    Button GoButton;

    GoogleMap mMap;
    //MapView mMapView;

    private static final double DHAKA_LAT = 23.812521,
                                DHAKA_LNG = 90.413833 ;
    private static final float DEFAULTZOOM = 15;

    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (services()) {

            setContentView(R.layout.activity_geo);
            if (initMap()) {
                Toast.makeText(this, "Ready to Map", Toast.LENGTH_SHORT).show();

                //get my position using gps
                mMap.setMyLocationEnabled(true);

                //initial location at Dhaka
                gotoLocation(DHAKA_LAT, DHAKA_LNG, DEFAULTZOOM);
            }

            else {
                Toast.makeText(this, "NOT Ready to Map", Toast.LENGTH_SHORT).show();
            }



        }
        else {

            setContentView(R.layout.activity_main);
        }

        //Go button
        GoButton = (Button)findViewById(R.id.buttonGo);
        GoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //locate place
                geoLocate(v);
            }
        });
    }

    //set location with moving camera
    private void gotoLocation(double lat, double lng, float zoom) {

        LatLng ll = new LatLng(lat,lng);
        //set the camera on the location with zoom level
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll,zoom);
        mMap.moveCamera(update);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //check Google Play Service is available to the phone
    public boolean services(){
        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if(isAvailable == ConnectionResult.SUCCESS){
            //return true value if Google Play Service connected
            return true;
        }

        else if(GooglePlayServicesUtil.isUserRecoverableError(isAvailable)){
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, this , GPS_ERRORDIALOG_REQUEST);
            dialog.show();
        }
        else {
            Toast.makeText(this, "cant connect to google play service", Toast.LENGTH_SHORT).show();
        }
        return false;

    }

    //show the google map
    private boolean initMap(){
        if(mMap == null)
        {
            //show map on fragment
            SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mMap = mapFrag.getMap();
        }
        return (mMap != null);
    }


    //set the location when place is searched
    public void geoLocate(View v){

        try {

            hideSoftKeyboard(v);
            EditText et = (EditText) findViewById(R.id.editText1);

            //make input text as a string
            String location = et.getText().toString();

            //if no input is found
            if (location.length() == 0) {
                Toast.makeText(this, "Please enter a location", Toast.LENGTH_SHORT).show();
                return;
            }

            //track the place from google address list
            Geocoder gc = new Geocoder(this);
            List<Address> list = gc.getFromLocationName(location, 1);
            Address add = list.get(0);

            //get the location name and show as toast
            String locality = add.getLocality();
            Toast.makeText(this, locality, Toast.LENGTH_LONG).show();

            //find the searched place latitude and longitude
            double lat = add.getLatitude();
            double lng = add.getLongitude();

            //set the map on the place
            gotoLocation(lat, lng, DEFAULTZOOM);

            //show only one marker at a time
            //remove previous marker before set new marker
            if (marker != null) {
                marker.remove();
            }

            //set the marker point on location
            MarkerOptions options = new MarkerOptions()
                    .title(locality)
                    .position(new LatLng(lat, lng));
            marker = mMap.addMarker(options);
        }catch (Exception e)
        {
            Toast.makeText(this, "not found", Toast.LENGTH_LONG).show();
        }


    }

    //show key board
    //hide after Go Button pressed
    private void hideSoftKeyboard(View v){
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),0);
    }




}
