package com.example.ytpillai.cmsc_355_proj

import android.support.test.filters.MediumTest
import android.support.test.runner.AndroidJUnit4
import com.example.ytpillai.cmsc_355_proj.security.SecurityUtils
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class TestSecurityUtil {

    val SENT_MESSAGE = "My password is redqueen21"

    //Scenario: 2.1 Check that encryption works
    @Test
    fun testEncryptMethod() {

        assertThat((equalTo(SecurityUtils.encryptMessage(SENT_MESSAGE)).toString()), not((SENT_MESSAGE)))
    }

    //Scenario: 2.2 Check that decryption works
    @Test
    fun testDecryptmethod() {

        val encryptedMsg = SecurityUtils.encryptMessage(SENT_MESSAGE).toString()

        assertThat((equalTo(SecurityUtils.decryptMessage(encryptedMsg))).toString(), `is`("\"" + SENT_MESSAGE + "\""))


    }

    //Scenario: 2.3 Ensure KeyPair is generated
    @Test
    fun testGenKeyPair(){
        SecurityUtils.encryptMessage(SENT_MESSAGE) //Should auto generate keypair
        assertTrue(SecurityUtils.keyPairExists())
    }
}


