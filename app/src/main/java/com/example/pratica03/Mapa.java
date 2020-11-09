package com.example.pratica03;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.awt.font.NumericShaper;

public class Mapa extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap map;
    public LatLng local;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        Intent itLat = getIntent();
        String lat = itLat.getStringExtra(MainActivity.EXTRA_LAT);
        Intent itLng = getIntent();
        String lng = itLng.getStringExtra(MainActivity.EXTRA_LNG);
        local = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        /*if(map!=null) {
            map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(
                    new CameraPosition.Builder()
                            .target(local)
                            .tilt(60)
                            .zoom(14)
                            .build());
            map.animateCamera(update);
        }*/
    }

    public Mapa() {
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.moveCamera(CameraUpdateFactory.newLatLng(local));
        map.animateCamera(CameraUpdateFactory.zoomTo(20));
    }
}
