package com.example.driverapp


import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import com.google.android.gms.location.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.example.driverapp.helper.*
import com.example.driverapp.model.Driver
//import com.example.driverapp.interfaces.IPositiveNegativeListener
import com.example.driverapp.interfaces.LatLngInterpolator
//import com.example.driverapp.model.Driver

class MapsActivity : AppCompatActivity() {
    companion object {
        private const val MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 2200
    }
    private lateinit var googleMap: GoogleMap
    private lateinit var locationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private var locationFlag = true
    private var driverOnlineFlag = false
    private var currentPositionMarker: Marker? = null
    private val googleMapHelper = GoogleMapHelper()
    private val firebaseHelper = FirebaseHelper()
    private val markerAnimationHelper = MarkerAnimationHelper()
    private val uiHelper = UiHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.supportMap) as SupportMapFragment

    }

    private fun createLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                super.onLocationResult(locationResult)
                if (locationResult!!.lastLocation == null) return
                val latLng = LatLng(locationResult.lastLocation.latitude, locationResult.lastLocation.longitude)
                Log.e("Location", latLng.latitude.toString() + " , " + latLng.longitude)
                if (locationFlag) {
                    locationFlag = false
                    animateCamera(latLng)
                }
                if (driverOnlineFlag) firebaseHelper.updateDriver(Driver(lat = latLng.latitude, lng = latLng.longitude,driverId = retrieveCurrentEmail()))
                showOrAnimateMarker(latLng)
            }
        }
    }
    private fun retrieveCurrentEmail(): String {
        lateinit var auth: FirebaseAuth
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val Email=user?.email.toString()
        return Email
    }


    private fun animateCamera(latLng: LatLng) {
        val cameraUpdate = googleMapHelper.buildCameraUpdate(latLng)
        googleMap.animateCamera(cameraUpdate, 10, null)
    }
    private fun showOrAnimateMarker(latLng: LatLng) {
        if (currentPositionMarker == null)
            currentPositionMarker = googleMap.addMarker(googleMapHelper.getDriverMarkerOptions(latLng))
        else markerAnimationHelper.animateMarkerToGB(currentPositionMarker!!, latLng, LatLngInterpolator.Spherical())
    }
}