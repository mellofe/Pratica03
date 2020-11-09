package com.example.pratica03;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.awt.font.NumericShaper;

public class Mapa extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap map;
    private LatLng local;
    private final LatLng dpi = new LatLng(-20.764781, -42.868448);
    private final LatLng vicosa = new LatLng(-20.765829, -42.882227);
    private final LatLng cddNatal = new LatLng(-20.765829, -42.882227);
    private Marker marcador = null;
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
    }

    public Mapa() {
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.moveCamera(CameraUpdateFactory.newLatLng(local));
        map.animateCamera(CameraUpdateFactory.zoomTo(20));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);
    }

    public void onClick_DPI(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(dpi, 20);
        map.animateCamera(update);
    }

    public void onClick_VICOSA(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(vicosa, 20);
        map.animateCamera(update);
    }

    public void onClick_NATAL(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(cddNatal, 20);
        map.animateCamera(update);
    }

    public void onClick_defLocal(View v) {
        Location current = map.getMyLocation();
        Location casa = new Location("");
        casa.setLongitude(vicosa.longitude);
        casa.setLatitude(vicosa.latitude);
        if(current!=null) {
            if(marcador!=null)
                marcador.remove();
            double lat = current.getLatitude();
            double lng = current.getLongitude();
            Log.i("LAT", "LAT " + lat);
            Log.i("LNG", "LNG " + lng);
            LatLng ATUAL = new LatLng(current.getLatitude(), current.getLongitude());
            marcador = map.addMarker(new MarkerOptions().position(ATUAL).title("Minha localização atual")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ATUAL, 20);
            map.animateCamera(update);
            final float dist = current.distanceTo(casa);
            int distance = (int) dist;
            Toast.makeText(getApplicationContext(), "Distancia até sua casa: "+String.valueOf(distance)+" m", Toast.LENGTH_LONG).show();
        }
    }
}
