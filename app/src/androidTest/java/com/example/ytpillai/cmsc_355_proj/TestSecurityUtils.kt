package com.example.ytpillai.cmsc_355_proj

import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.MediumTest
import com.example.ytpillai.cmsc_355_proj.security.SecurityUtils
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class TestSecurityUtils {
    @Test
    fun testEncryptMethod() {
        assertThat(
                SecurityUtils.decryptMessage(SecurityUtils.encryptMessage("My Password is redqueen21")!!), `is` ("My password is redqueen21"))
    }
}

