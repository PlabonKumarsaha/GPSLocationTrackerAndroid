package com.example.gpslocationtrackerandroid;

import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<Location>savedLocation;
    public int clicks =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        MyApplication myApplication = (MyApplication) getApplicationContext();
        savedLocation =myApplication.getMyLocation();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
      //  LatLng sydney = new LatLng(-34, 151);
      //  mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
      //  mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
          LatLng lastLocationPlaced = null;
        for (Location location:savedLocation
             ) {
            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Latitude : "+location.getLatitude()+ "Longitude : "+location.getLatitude());
            mMap.addMarker(markerOptions);
            // mMap.addMarker(new MarkerOptions().position(latLng).title(""));
            lastLocationPlaced = latLng;
        }


         mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLocationPlaced,15.0f));
        //set on marker click listener
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //set a click tag
                clicks++;
                if(clicks>0){
                    Toast.makeText(getApplicationContext(),"Markter :"+marker.getTitle()+ " "+ String.valueOf(clicks),Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });



    }
}