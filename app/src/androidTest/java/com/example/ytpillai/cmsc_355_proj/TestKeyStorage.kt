package com.example.ytpillai.cmsc_355_proj

import android.support.test.filters.MediumTest
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

import com.example.ytpillai.cmsc_355_proj.security.KeyStorage

@RunWith(AndroidJUnit4::class)
@MediumTest
class TestKeyStorage {

    val ks = KeyStorage.instance

    // Scenario: 2.1 Check that encryption works
    @Test
    fun testKeyInsertion() {


    }


}


