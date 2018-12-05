package com.example.ytpillai.cmsc_355_proj.services

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.IBinder
import android.os.SystemClock.sleep
import android.text.format.Formatter
import android.util.Log
import com.example.ytpillai.cmsc_355_proj.R
import com.example.ytpillai.cmsc_355_proj.networking.MessageSocketServer
import java.net.InetSocketAddress

class MessageServerService : Service() {
    private lateinit var messageSocketServer: MessageSocketServer
    private val DEFAULT_PORT = 8112



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        this.messageSocketServer.start()
        for(i in 1..10000000){
            messageSocketServer.broadcast("bitch")
        }
        val messageBroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent!!.action!!.equals(resources.getString(R.string.ACTION_SENT_MESSAGE))) {
                    messageSocketServer.broadcast("${intent.extras!!["message"]}")
                    Log.d("PROGRAM_ACTIVITY", "Something happened : ${intent.extras!!["message"]}")

                } else {
                    Log.d("PROGRAM_ACTIVITY", "Something happened : ${intent.extras!!["message"]}")
                }
            }

        }
        return START_STICKY;
    }

    fun sendMessage(yourmom: String){

    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    /**
     * In a real device get the actual IP. In this case we are using ADB tactics
     */
    override fun onCreate() {
        Log.e("IP", getIpAddressIHope())
        val ipAddress = InetSocketAddress("127.0.0.1", DEFAULT_PORT) //127.0.0.1 should be getIpAddressIHope()
        this.messageSocketServer = MessageSocketServer(ipAddress, this)
        super.onCreate()
    }

    override fun onDestroy() {
        messageSocketServer.stop()
        super.onDestroy()
    }


    fun getIpAddressIHope(): String {
        val wm = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val ip = Formatter.formatIpAddress(wm.connectionInfo.ipAddress)
        Log.d("NETWORKING_GETIP", "Found IP address $ip")
        return ip
    }
}
