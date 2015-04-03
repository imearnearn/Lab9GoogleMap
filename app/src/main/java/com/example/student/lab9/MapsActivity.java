package com.example.student.lab9;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMapLongClickListener, LocationListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    LatLng oldpoint;
    LocationManager lMgr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        mMap.setOnMapLongClickListener(this);

        lMgr = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        lMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();


    }
    int n=0;

    public void onMapLongClick(LatLng latLng) {
   mMap.addMarker(new MarkerOptions().position(latLng).title("Marker" + ++n));


        if(n!=1)
            mMap.addPolyline(new PolylineOptions().add(latLng).add(oldpoint));
        oldpoint = latLng;

    }

    int asdf;
    public void onLocationChanged(Location location){
        LatLng LL = new LatLng(location.getLatitude(),location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(LL).title("Location changed" + ++asdf));

        CameraUpdate center_point = CameraUpdateFactory.newLatLng(LL);
        mMap.moveCamera(center_point);

        CameraUpdate zoom_level = CameraUpdateFactory.zoomTo(7);
        mMap.animateCamera(zoom_level);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        LatLng BKK = new LatLng(13.7563,100.5018);
        LatLng HK = new LatLng(22.2783,114.1067);

        mMap.addMarker(new MarkerOptions().position(BKK).title("Bangkok"));
        mMap.addMarker(new MarkerOptions().position(HK).title("Hong Kong"));

        CameraUpdate center_point = CameraUpdateFactory.newLatLng(BKK);
        mMap.moveCamera(center_point);

        CameraUpdate zoom_level = CameraUpdateFactory.zoomTo(7);
        mMap.animateCamera(zoom_level);

        mMap.addPolyline(new PolylineOptions().add(BKK).add(HK));

    }



}
