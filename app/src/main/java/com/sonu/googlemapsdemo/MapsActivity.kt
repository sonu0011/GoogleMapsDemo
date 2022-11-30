package com.sonu.googlemapsdemo

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
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
        applyMapStyle(mMap)

        /*
        move controls away from container e.x sidebar and center point also moved to that padding
        mMap.setPadding(0, 0, 300, 0)
         */

        /*
            New way to create options menu
         */

        addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.map_types_menu, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    when (menuItem.itemId) {
                        R.id.normal_map -> mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                        R.id.hybrid_map -> mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
                        R.id.satellite_map -> mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                        R.id.terrain_map -> mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
                        R.id.none_map -> mMap.mapType = GoogleMap.MAP_TYPE_NONE
                    }
                    return true
                }
            }
        )
    }

    /*
        Map Types

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_types_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.normal_map -> mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            R.id.hybrid_map -> mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            R.id.satellite_map -> mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            R.id.terrain_map -> mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            R.id.none_map -> mMap.mapType = GoogleMap.MAP_TYPE_NONE
        }
        return true
    }
    */

    /*
        Map style
        two ways to create map style from website and from cloud
        website : https://mapstyle.withgoogle.com/
        copy json file
     */
    private fun applyMapStyle(map: GoogleMap) {
        try {
            val success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this,
                    R.raw.map_style
                )
            )
            if (!success) {
                Log.d("Map", "Failed in applying map style")
            }

        } catch (e: Exception) {
            Log.e("Map", e.toString())
        }
    }

}