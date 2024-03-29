package com.example.ytpillai.cmsc_355_proj.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.IBinder
import android.text.format.Formatter
import android.util.Log
import com.example.ytpillai.cmsc_355_proj.networking.MessageSocketClient
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import java.net.URI
import java.util.concurrent.ExecutorService


class MessageClientService : Service() {
    private lateinit var messageSocketClient: MessageSocketClient
    private val DEFAULT_PORT = 8112
    private lateinit var okHttpClientService: ExecutorService
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var webSocket: WebSocket

    override fun onCreate() {
        Log.e("IP", getIpAddressIHope())
        okHttpClient = OkHttpClient.Builder().build()

        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val destIP = intent!!.extras!!["destIP"]
        val uri = "ws://$destIP/"
        Log.e("THETHING", uri)
        //TODO: Destroy this somehow in onDestroy
        val request = Request.Builder().url(uri).build()
        val messageSocketClient = MessageSocketClient(
                URI(uri),
                applicationContext
        )
        webSocket = okHttpClient.newWebSocket(request, messageSocketClient)
        okHttpClientService = okHttpClient.dispatcher().executorService()





        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }


    fun getIpAddressIHope(): String {
        val wm = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val ip = Formatter.formatIpAddress(wm.connectionInfo.ipAddress)
        Log.d("NETWORKING_GETIP", "Found IP address $ip")
        return ip
    }

    override fun onDestroy() {
        okHttpClientService.shutdown()
        super.onDestroy()
    }
}
