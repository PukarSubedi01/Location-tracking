package com.team.passengerapp.interfaces

import com.team.passengerapp.model.Driver


interface FirebaseDriverListener {

    fun onDriverAdded(driver: Driver)

    fun onDriverRemoved(driver: Driver)

    fun onDriverUpdated(driver: Driver)
}