package com.example.ytpillai.cmsc_355_proj.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.IBinder
import android.text.format.Formatter
import android.util.Log
import com.example.ytpillai.cmsc_355_proj.networking.MessageSocketClient
import java.net.URI

class MessageClientService : Service() {
    private var messageSocketClient: MessageSocketClient?
    private val DEFAULT_PORT = 8112

    init {
        this.messageSocketClient = null;
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val destIP = intent!!.extras["destIP"]
        val uri = URI("wss://$destIP:$DEFAULT_PORT/")
        this.messageSocketClient = MessageSocketClient(uri, applicationContext) //TODO: Destroy this somehow in onDestroy


        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null;
    }


    fun getIpAddressIHope(): String {
        val wm = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val ip = Formatter.formatIpAddress(wm.connectionInfo.ipAddress)
        Log.d("NETWORKING_GETIP", "Found IP address $ip")
        return ip
    }
}
