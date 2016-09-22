package com.example.nazmul.hospitalfinder;

import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class HospitalMarker extends FragmentActivity {

    private static final int GPS_ERRORDIALOG_REQUEST = 9001;

    GoogleMap mMap;

    private static final double DHAKA_LAT = 23.812521,
            DHAKA_LNG = 90.413833 ;
    private static final float DEFAULTZOOM = 12;

    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (services()) {

            setContentView(R.layout.activity_hospital__marker);
            if (initMap()) {
                //Toast.makeText(this, "Ready to Map", Toast.LENGTH_SHORT).show();

                //get my position using gps
                mMap.setMyLocationEnabled(true);

                //initial location at Dhaka
                gotoLocation(DHAKA_LAT, DHAKA_LNG, DEFAULTZOOM);

                show();
            }

            else {
                Toast.makeText(this, "NOT Ready to Map", Toast.LENGTH_SHORT).show();
            }

        }
        else {

            setContentView(R.layout.activity_main);
        }


    }

    private void show() {

        String dmc="Dhaka Medical College";
        String ssmc="Sir Salimullah Medical College Hospital";
        String smc="Shaheed Suhrawardy Medical College Hospital";
        String bmc="Bangladesh Medical College";
        String dnmc="Dhaka National Medical College";
        String swmc="Sikder Womens Medical College";
        String imc="Ibrahim Medical College";
        String hmc="Holy Family Medical College ";
        String akmc="Anwar Khan Modern Medical College";
        String admc="Ad-din Women’s Medical College";
        String pmc="Popular Medical College";
        String lch="Labaid Cardiac Hospital";
        String uh="United Hospital";
        String ah="Apollo Hospital";
        String ibch="Islami Bank Central Hospital";
        String bh="Birdem Hospital";
        String bsmmu="Bangabandhu Sheikh Mujib Medical University";
        String ieh="Islamia Eye Institue and Hospital";



        double dmc_lat =23.725180,dmc_lng=90.397436;
        double ssmc_lat =23.711207,ssmc_lng=90.401210;
        double smc_lat =23.769393,smc_lng=90.370967;
        double bmc_lat =23.750306,bmc_lng=90.369881;
        double dnmc_lat =23.710765,dnmc_lng=90.412297;
        double swmc_lat =23.791487,swmc_lng=90.420066;
        double imc_lat =23.738389,imc_lng=90.396463;
        double hmc_lat =23.7464198  ,hmc_lng=90.4023018;
        double akmc_lat =23.74526  ,akmc_lng=90.382166;
        double admc_lat =23.748374 ,admc_lng=90.40521;
        double pmc_lat =23.7385201 ,pmc_lng=90.3797068;
        double lch_lat =23.74161  ,lch_lng=90.383475;
        double uh_lat =23.80456 ,uh_lng=90.415611;
        double ah_lat =23.807883  ,ah_lng=90.4270878;
        double ibch_lat =23.737584 ,ibch_lng=90.4098241;
        double bh_lat =23.7382838  ,bh_lng=90.3956889;
        double bsmmu_lat =23.738641,bsmmu_lng=90.3952865;
        double ieh_lat =23.7583394 ,ieh_lng=90.3835867;


        setMarker(dmc_lat, dmc_lng,dmc);
        setMarker(ssmc_lat,ssmc_lng,ssmc);
        setMarker(smc_lat,smc_lng,smc);
        setMarker(bmc_lat,bmc_lng,bmc);
        setMarker(dnmc_lat,dnmc_lng,dnmc);
        setMarker(swmc_lat,swmc_lng,swmc);
        setMarker(imc_lat,imc_lng,imc);
        setMarker(hmc_lat,hmc_lng,hmc);
        setMarker(akmc_lat,akmc_lng,akmc);
        setMarker(admc_lat,admc_lng,admc);
        setMarker(pmc_lat,pmc_lng,pmc);
        setMarker(lch_lat,lch_lng,lch);
        setMarker(uh_lat,uh_lng,uh);
        setMarker(ah_lat,ah_lng,ah);
        setMarker(ibch_lat,ibch_lng,ibch);
        setMarker(bh_lat,bh_lng,bh);
        setMarker(bsmmu_lat,bsmmu_lng,bsmmu);
        setMarker(ieh_lat,ieh_lng,ieh);
    }

    private void setMarker(double lat,double lng,String name)
    {
        double lati=lat;
        double longi=lng;
        String hos_name = name;

        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lati, longi))
                .title(hos_name));
    }

    //set location with moving camera
    private void gotoLocation(double lat, double lng, float zoom) {

        LatLng ll = new LatLng(lat,lng);
        //set the camera on the location with zoom level
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mMap.moveCamera(update);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hospital__marker, menu);
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

}
