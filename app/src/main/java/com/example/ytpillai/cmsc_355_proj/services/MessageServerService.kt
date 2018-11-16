package com.example.ytpillai.cmsc_355_proj.services

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.IBinder
import android.text.format.Formatter
import android.util.Log
import com.example.ytpillai.cmsc_355_proj.R
import com.example.ytpillai.cmsc_355_proj.networking.MessageSocketServer
import java.net.InetSocketAddress

class MessageServerService : Service() {
    private var messageSocketServer: MessageSocketServer?
    private val DEFAULT_PORT = 8112

    init {
        this.messageSocketServer = null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        this.messageSocketServer!!.start()

        return super.onStartCommand(intent, flags, startId)
    }

    fun sendMessage(yourmom: String){

    }

    override fun onBind(intent: Intent): IBinder? {
        return null;
    }

    override fun onCreate() {
        Log.e("IP", getIpAddressIHope())
        val ipAddress = InetSocketAddress("192.168.200.2", DEFAULT_PORT)
        this.messageSocketServer = MessageSocketServer(ipAddress, this)
        super.onCreate()
    }

    override fun onDestroy() {
        messageSocketServer!!.stop()
        super.onDestroy()
    }


    fun getIpAddressIHope(): String {
        val wm = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val ip = Formatter.formatIpAddress(wm.connectionInfo.ipAddress)
        Log.d("NETWORKING_GETIP", "Found IP address $ip")
        return ip
    }
}
