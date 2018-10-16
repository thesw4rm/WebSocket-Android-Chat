package com.example.ytpillai.cmsc_355_proj

import android.util.Log
import com.example.ytpillai.cmsc_355_proj.security.SecurityUtils
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not

import org.junit.Test
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(Log::class)
class TestSecurityUtils{

    @Before
    fun beforeTest() {
        PowerMockito.mockStatic(Log::class.java)
        Mockito.`when`(Log.e(any(), any())).then {
            println(it.arguments[1] /*as String*/)
            1
        }
    }

    @Test
    fun testEncrypt() {

        //plainText: String, alias: String = KEY_ALIAS
        assertThat(SecurityUtils.encryptMessage(
                "My password is redqueen21"), `is` (("My Password is redqueen21")))

    }

}