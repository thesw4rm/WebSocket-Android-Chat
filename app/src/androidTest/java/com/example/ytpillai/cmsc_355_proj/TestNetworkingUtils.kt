package com.example.ytpillai.cmsc_355_proj

import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.MediumTest
import com.example.ytpillai.cmsc_355_proj.networking.NetworkingUtils
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestNetworkingUtils {

    @Test
    fun testgetIpAddress(){
        val ipAddress = NetworkingUtils.getWifiIPAddress()
        println(ipAddress.toString())
        assertNotNull(ipAddress)
    }
}