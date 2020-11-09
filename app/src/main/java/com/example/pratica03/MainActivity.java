package com.example.pratica03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends ListActivity {
    public static final String EXTRA_LAT = "com.example.pratica03.LAT";
    public static final String EXTRA_LNG = "com.example.pratica03.LNG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String menu [] = new String [] {"Minha casa na cidade natal", "Minha casa em Viçosa", "Meu departamento", "Fechar aplicação"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        Intent it = new Intent(getBaseContext(), Mapa.class);
        String aux = l.getItemAtPosition(position).toString();
        String lat;
        String lng;
        switch(position){
            case 0:
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                lat = "-20.765829";
                lng = "-42.882227";
                it.putExtra(EXTRA_LAT, lat);
                it.putExtra(EXTRA_LNG, lng);
                startActivity(it);
                break;
            case 1:
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                lat = "-20.765829";
                lng = "-42.882227";
                it.putExtra(EXTRA_LAT, lat);
                it.putExtra(EXTRA_LNG, lng);
                startActivity(it);
                break;
            case 2:
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                lat = "-20.764781";
                lng = "-42.868448";
                it.putExtra(EXTRA_LAT, lat);
                it.putExtra(EXTRA_LNG, lng);
                startActivity(it);
                break;
            case 3:
                finish();
        }
    }
}