package com.example.ytpillai.cmsc_355_proj


import android.content.Context
import org.junit.Test
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers.`is`

import org.junit.Assert
import java.net.InetAddress
import android.content.Context.WIFI_SERVICE
import android.net.wifi.WifiManager
import android.support.test.filters.MediumTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.example.ytpillai.cmsc_355_proj.security.SecurityUtils
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class testSecurityUtil {


    val SENT_MESSAGE = "My password is redqueen21"
    val RECEIVED_MESSAGE = SENT_MESSAGE

    //Scenario: 2.1
    @Test
    fun testEncryptMethod() {

       assertThat((equalTo(SecurityUtils.encryptMessage(SENT_MESSAGE)).toString()),not((SENT_MESSAGE)))
    }

    @Test
    fun testDecryptmethod() {

        var encryptedMsg = SecurityUtils.encryptMessage(SENT_MESSAGE).toString()

            assertThat((equalTo(SecurityUtils.decryptMessage(encryptedMsg))).toString(), `is`(SENT_MESSAGE))


        }
    }


