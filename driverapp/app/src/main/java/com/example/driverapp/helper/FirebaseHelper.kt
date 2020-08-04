package com.example.driverapp.helper

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.driverapp.model.Driver

class FirebaseHelper {

    companion object {
        private const val ONLINE_DRIVERS = "online_drivers"
    }

    private val onlineDriverDatabaseReference: DatabaseReference = FirebaseDatabase
        .getInstance()
        .reference
        .child(ONLINE_DRIVERS)
        .child(retrieveCurrentUID())

    init {
        onlineDriverDatabaseReference
            .onDisconnect()
            .removeValue()
    }
    fun retrieveCurrentUID(): String {
        lateinit var auth: FirebaseAuth
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val UID=user?.uid.toString()
        return UID

    }

    fun updateDriver(driver: Driver) {
        onlineDriverDatabaseReference
            .setValue(driver)
        Log.e("Driver Info", " Updated")
    }

    fun deleteDriver() {
        onlineDriverDatabaseReference
            .removeValue()
    }
}