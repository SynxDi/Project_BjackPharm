package com.example.project_bjackpharm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_bjackpharm.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Activity_AboutUs extends AppCompatActivity {

    private GoogleMap maps;
    Button backToHomeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        backToHomeBtn = findViewById(R.id.backToHomeBtn);

        backToHomeBtn.setOnClickListener(e->{

            startActivity(new Intent(Activity_AboutUs.this, HomeActivity.class));

        });


        SupportMapFragment mp = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.Gmaps);
        mp.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                maps = googleMap;
                double latitude = -6.20201;
                double longitude = 106.78113;

                LatLng Binus = new LatLng(latitude, longitude);
                maps.addMarker(new MarkerOptions().position(Binus).title("Bluejack Pharmacy"));
                maps.moveCamera(CameraUpdateFactory.zoomTo(15));
                maps.moveCamera(CameraUpdateFactory.newLatLng(Binus));


            }
        });

    }


}