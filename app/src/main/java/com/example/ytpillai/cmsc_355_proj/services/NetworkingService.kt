package com.example.ytpillai.cmsc_355_proj.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Eventually will have network methods which I lazily put in MessageServerService
 */
class NetworkingService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
