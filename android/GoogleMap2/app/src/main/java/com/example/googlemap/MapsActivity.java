package com.example.googlemap;
import android.content.Intent;
import android.graphics.Color;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;


import static com.example.googlemap.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap, map1, map2, map3, map4, map5;
    HttpURLConnection conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
        JSONObject json = new JSONObject();

        try {
            json.put("name", "teju");
            json.put("age", new Integer(24));
            Log.d("TAG", json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        map1 = googleMap;
        map2 = googleMap;
        map3 = googleMap;
        map4 = googleMap;
        map5 = googleMap;

//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mMap.setMyLocationEnabled(true);
//
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-33.87365, 151.20689);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        Circle circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(-33.87365, 151.20689))
                .radius(10000)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));


        circle = map5.addCircle(new CircleOptions()
                .center(new LatLng(12.9716, 77.5946))
                .radius(10000)
                .strokeColor(Color.GREEN)
                .fillColor(Color.YELLOW));


        LatLng Lalbag = new LatLng(12.9507, 77.5848);
        map1.addMarker(new MarkerOptions().position(Lalbag).title("Marker in Lalbag"));
        map1.moveCamera(CameraUpdateFactory.newLatLng(Lalbag));


        LatLng Mahabaleshwar = new LatLng(17.9307, 73.6477);
        map2.addMarker(new MarkerOptions().position(Mahabaleshwar).title(" Mahabaleshwar,Info about place"));
        map2.moveCamera(CameraUpdateFactory.newLatLng(Mahabaleshwar));

        LatLng EsconTemple = new LatLng(13.0096, 77.5511);
        map3.addMarker(new MarkerOptions().position(EsconTemple).title("Marker in Escon Temple"));
        map3.moveCamera(CameraUpdateFactory.newLatLng(EsconTemple));
        LatLng IndiaGate = new LatLng(28.6129, 77.2295);
        map4.addMarker(new MarkerOptions().position(IndiaGate).title("Marker in India Gate"));
        map4.moveCamera(CameraUpdateFactory.newLatLng(IndiaGate));

        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        LatLng position =marker.getPosition();
        double lati, longi;
        lati=position.latitude;
        longi=position.longitude;

        Intent intent =new Intent(MapsActivity.this,DetailsActivity.class);
        intent.putExtra("lati", lati);
        intent.putExtra("longi",longi);
        startActivity(intent);

        return false;
    }
}

