package com.example.driverapp


import androidx.appcompat.app.AppCompatActivity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_DENIED
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.appcompat.widget.SwitchCompat
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.location.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.example.driverapp.helper.*
//import com.example.driverapp.interfaces.IPositiveNegativeListener
//import com.example.driverapp.interfaces.LatLngInterpolator
//import com.example.driverapp.model.Driver
import kotlinx.android.synthetic.main.activity_login.*

class MapsActivity : AppCompatActivity() {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.supportMap) as SupportMapFragment

    }


}