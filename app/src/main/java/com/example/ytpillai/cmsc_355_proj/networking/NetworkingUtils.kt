package com.example.ytpillai.cmsc_355_proj.networking

import android.util.Log
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.NetworkInterface.getNetworkInterfaces
import java.net.SocketException


class NetworkingUtils {

    companion object {
        fun getWifiIPAddress(): InetAddress{
            var ipAddress: String? = null
            try {
                val en = NetworkInterface.getNetworkInterfaces()
                while (en.hasMoreElements()) {
                    val intf = en.nextElement()
                    val enumIpAddr = intf.getInetAddresses()
                    while (enumIpAddr.hasMoreElements()) {
                        val inetAddress = enumIpAddr.nextElement()

                        if (!inetAddress.isLoopbackAddress()) {
                            ipAddress = inetAddress.getHostAddress().toString()
                            return inetAddress
                        }
                    }
                }
            } catch (ex: SocketException) {
                Log.e("NETWORKING_UTILS_GET_IP", "Error getting Wifi IP address", ex);
                return InetAddress.getLocalHost();
            }
        }
    }
}