package com.example.ytpillai.cmsc_355_proj.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.ytpillai.cmsc_355_proj.BuildConfig
import com.example.ytpillai.cmsc_355_proj.messaging.MessageSocketServer
import com.example.ytpillai.cmsc_355_proj.networking.NetworkingUtils
import java.lang.NullPointerException
import java.net.InetSocketAddress

class MessageService : Service() {
    var messageSocketServer: MessageSocketServer
    init{
        val ipAddress = NetworkingUtils.getWifiIPAddress()
        if(ipAddress == null){
            Log.e("MESSAGE_SERVICE_INIT", "IP address was null for local address")
            throw NullPointerException("IP address was null for local address")
        }
        
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
         = MessageSocketServer(InetSocketAddress("10.1.1.1", 8112))
        messageSocketServer.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        messageSo
        super.onDestroy()
    }
}
