package com.example.lessgo_android;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.lessgo_android.model.UserBean;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private final ArrayList<UserBean> listOfUsers = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //demande permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Pas la permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    ArrayList<UserBean> temp = WSUtils.getPositions();
                    listOfUsers.clear();
                    listOfUsers.addAll(temp);
                    refreshMap();
                } catch (Exception e) {
                    e.printStackTrace();
                    //todo mettre a jour message erreur
                }
            }
        };
        thread.start();
    Thread thread1= new Thread(){
        @Override
        public void run() {
            super.run();
            Location location = getLocation();
            while(location == null){
                location = getLocation();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //todo envoyer la localisation au serveur
        }
    };
    thread1.start();
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
        refreshMap();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        refreshMap();
    }

    public Location getLocation(){
//Contrôle de la permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_DENIED) {
            return null;
        }
//Récupération du provider (Réseau ou GPS)
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provider = lm.getBestProvider(new Criteria(), false);
        if (provider == null) {
            Toast.makeText(this, "Pas de provider", Toast.LENGTH_SHORT).show();
            return null;
        }
//Récupération de la localisation
        return lm.getLastKnownLocation(provider);


    }
    public void refreshMap(){
        if(mMap == null){
            return;
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                }
                mMap.clear();
                    for(UserBean u : listOfUsers){
                        MarkerOptions markerUser = new MarkerOptions();
                        markerUser.position(new LatLng(u.getLat(),u.getLon()));
                        markerUser.title(u.getPseudo());
                        mMap.addMarker(markerUser);

                }
            }
        });
    }





}