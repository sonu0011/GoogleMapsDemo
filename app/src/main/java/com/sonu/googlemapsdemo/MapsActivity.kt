package com.sonu.googlemapsdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sonu.googlemapsdemo.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val doraha = LatLng(30.798358161007453, 76.00648309682767)
        mMap.addMarker(MarkerOptions().position(doraha).title("Marker in Doraha"))
        //zoom levels are from 1 to 20
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(doraha, 10F))

        /*
          Google map gesture settings

          mMap.uiSettings.apply {
              isZoomControlsEnabled = true
              isZoomGesturesEnabled = false
              isScrollGesturesEnabled = false
              isMyLocationButtonEnabled = true //shows only when location layer ia activated
              isMapToolbarEnabled = true
              isCompassEnabled = false

          }
          */

    }
}