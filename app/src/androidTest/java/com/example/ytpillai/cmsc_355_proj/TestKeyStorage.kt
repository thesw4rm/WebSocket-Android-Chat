package com.example.ytpillai.cmsc_355_proj

/**
 * As a user, when I get someone else's public encryption key, then I want to be able to store it in the app. #13
 */
import android.support.test.filters.MediumTest
import android.support.test.runner.AndroidJUnit4
import com.example.ytpillai.cmsc_355_proj.security.KeyStorage
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.security.KeyPairGenerator

@RunWith(AndroidJUnit4::class)
@MediumTest
class TestKeyStorage {

    private val ks = KeyStorage.instance

    // Scenario 1
    @Test
    fun testKeyInsertion() {

        val keyGen = KeyPairGenerator.getInstance("DSA")
        val pk = keyGen.generateKeyPair().public

        ks.insertKey(pk, "TEST_KEY")

        assertTrue("Inserting Key", ks.containsAlias("TEST_KEY"))

        ks.deleteKey("TEST_KEY")

    }

    // Scenario 2
    @Test
    fun testKeyDeletion() {

        val keyGen = KeyPairGenerator.getInstance("DSA")
        val pk = keyGen.generateKeyPair().public

        ks.insertKey(pk, "TEST_KEY")

        ks.deleteKey("TEST_KEY")

        assertFalse("Deleting Key", ks.containsAlias("TEST_KEY"))

    }

    // Scenario 3
    @Test
    fun testGetEncryptionKey() {

        val keyGen = KeyPairGenerator.getInstance("DSA")
        val pk = keyGen.generateKeyPair().public

        ks.insertKey(pk, "TEST_KEY")

        assertNotNull("Should be a public key", ks.containsAlias("TEST_KEY"))

        ks.deleteKey("TEST_KEY")

    }


}


