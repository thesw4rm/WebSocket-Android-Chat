package com.example.ytpillai.cmsc_355_proj

import android.content.Intent
import android.os.IBinder
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ServiceTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeoutException

@RunWith(AndroidJUnit4::class)

class TestNetworkingUtils {
    @get:Rule
    val mMessageServiceRule = ServiceTestRule()

    @Test
    @Throws(TimeoutException::class)
    fun testgetIpAddress(){
        val serviceIntent = Intent(
                InstrumentationRegistry.getTargetContext(),
                MessageService::class.java
        )

        val binder: IBinder = mMessageServiceRule.bindService(serviceIntent)
        val service: MessageService = (binder as MessageService.LocalBinder).service
        assertNotNull(service.getIpAddressIHope())


    }
}