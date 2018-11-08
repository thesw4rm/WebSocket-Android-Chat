package com.example.ytpillai.cmsc_355_proj

import android.support.test.filters.MediumTest
import android.support.test.runner.AndroidJUnit4

import java.security.KeyPairGenerator

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers.`is`
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

import com.example.ytpillai.cmsc_355_proj.security.KeyStorage

@RunWith(AndroidJUnit4::class)
@MediumTest
class TestKeyStorage {

    val ks = KeyStorage.instance

    @Test
    fun testKeyInsertion() {

        val keyGen = KeyPairGenerator.getInstance("DSA")
        val pk = keyGen.generateKeyPair().public

        ks.insertKey(pk, "TEST_KEY")

        assertTrue("Inserting Key", ks.containsAliases("TEST_KEY"))

        ks.deleteKey("TEST_KEY")

    }

    @Test
    fun testKeyDeletion() {

        val keyGen = KeyPairGenerator.getInstance("DSA")
        val pk = keyGen.generateKeyPair().public

        ks.insertKey(pk, "TEST_KEY")

        ks.deleteKey("TEST_KEY")

        assertFalse("Deleting Key", ks.containsAliases("TEST_KEY"))

    }

    @Test
    fun testGetEncryptionKey() {

        val keyGen = KeyPairGenerator.getInstance("DSA")
        val pk = keyGen.generateKeyPair().public

        ks.insertKey(pk, "TEST_KEY")

        assertNotNull("Should be a public key", ks.containsAliases("TEST_KEY"))

        ks.deleteKey("TEST_KEY")

    }


}


