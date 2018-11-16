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
import com.example.ytpillai.cmsc_355_proj.networking.MessageSocketClient
import com.example.ytpillai.cmsc_355_proj.networking.MessageSocketServer
import com.tinder.scarlet.Scarlet
import okhttp3.OkHttpClient
import okhttp3.Request
import org.java_websocket.WebSocket
import org.java_websocket.WebSocketFactory
import java.net.InetSocketAddress
import java.net.URI
import java.util.concurrent.ExecutorService

class MessageClientService : Service() {
    private var messageSocketClient: MessageSocketClient?
    private val DEFAULT_PORT = 8112
    private lateinit var okHttpClientService: ExecutorService
    private lateinit var okHttpClient: OkHttpClient
    init{
        messageSocketClient = null;
    }

    override fun onCreate() {
        Log.e("IP", getIpAddressIHope())
        okHttpClient = OkHttpClient.Builder().build()

        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val destIP = intent!!.extras!!["destIP"]
        val uri ="ws://$destIP:$DEFAULT_PORT/"
        //TODO: Destroy this somehow in onDestroy
        val request = Request.Builder().url(uri).build()
        val messageSocketClient = MessageSocketClient(URI(uri), applicationContext)
        val webSocket = okHttpClient.newWebSocket(request, messageSocketClient)
        okHttpClientService = okHttpClient.dispatcher().executorService()

        val messageBroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent!!.action!!.equals(resources.getString(R.string.ACTION_SENT_MESSAGE))) {
                    webSocket.send("${intent.extras!!["message"]}")
                    Log.d("PROGRAM_ACTIVITY", "Something happened : ${intent.extras!!["message"]}")

                } else {
                    Log.d("PROGRAM_ACTIVITY", "Something happened : ${intent.extras!!["message"]}")
                }
            }

        }


        Log.e("THETHING", uri)

        return super.onStartCommand(intent, flags, startId)
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
