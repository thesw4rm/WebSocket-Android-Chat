package com.example.ytpillai.cmsc_355_proj

import android.support.test.filters.MediumTest
import android.support.test.runner.AndroidJUnit4
import com.example.ytpillai.cmsc_355_proj.security.RSA
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class TestRSA {

    val rsa = RSA.instance

    val SENT_MESSAGE = "My password is redqueen21"

    // Scenario: 2.1 Check that encryption works
    @Test
    fun testEncryptMethod() {

        assertThat((equalTo(rsa.encrypt(SENT_MESSAGE, rsa.KEY_ALIAS)).toString()), not((SENT_MESSAGE)))
    }

    // Scenario: 2.2 Check that decryption works
    @Test
    fun testDecryptMethod() {

        val encryptedMsg = rsa.encrypt(SENT_MESSAGE, rsa.KEY_ALIAS).toString()

        assertThat((equalTo(rsa.decrypt(encryptedMsg, rsa.KEY_ALIAS))).toString(), `is`("\"" + SENT_MESSAGE + "\""))

    }

    // Scenario: 2.3 Ensure KeyPair is generated
    @Test
    fun testGenKeyPair(){
        rsa.encrypt(SENT_MESSAGE, rsa.KEY_ALIAS) //Should auto generate keypair
        assertTrue(rsa.keyStorage.keyPairExists(rsa.KEY_ALIAS))
    }
}


