package com.example.ytpillai.cmsc_355_proj.services

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.text.format.Formatter
import android.util.Log
import com.example.ytpillai.cmsc_355_proj.R
import com.example.ytpillai.cmsc_355_proj.messaging.MessageSocketServer
import java.net.InetSocketAddress

// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS


/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
class MessageIntentService : IntentService("MessageIntentService") {
    private var messageSocketServer: MessageSocketServer?
    private val DEFAULT_PORT = 8112

    init {
        this.messageSocketServer = null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val ipAddress = InetSocketAddress(getIpAddressIHope(), DEFAULT_PORT)
        this.messageSocketServer = MessageSocketServer(ipAddress, this)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            resources.getString(R.string.ACTION_START) -> {
                handleActionStartServer()
            }
            resources.getString(R.string.ACTION_STOP) -> {
                handleActionStopServer()
            }
        }
    }

    fun handleActionStartServer() {
        messageSocketServer!!.start()
    }

    fun handleActionStopServer() {
        messageSocketServer!!.stop()
    }


    fun getIpAddressIHope(): String {
        val wm = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val ip = Formatter.formatIpAddress(wm.connectionInfo.ipAddress)
        Log.d("NETWORKING_GETIP", "Found IP address $ip")
        return ip
    }

}

