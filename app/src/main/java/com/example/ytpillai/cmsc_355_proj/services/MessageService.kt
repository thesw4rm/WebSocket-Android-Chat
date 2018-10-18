package com.example.ytpillai.cmsc_355_proj.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.ytpillai.cmsc_355_proj.messaging.MessageSocketServer
import java.net.InetSocketAddress

class MessageService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var messageSocketServer = MessageSocketServer(InetSocketAddress("10.1.1.1", 8112))
        messageSocketServer.start()
        return super.onStartCommand(intent, flags, startId)
    }
}
