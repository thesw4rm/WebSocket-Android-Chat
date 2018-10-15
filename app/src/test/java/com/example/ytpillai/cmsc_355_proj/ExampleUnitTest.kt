package com.example.ytpillai.cmsc_355_proj

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val WaterIsWet = true

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun wet_isCorrect() {
        assertEquals(WaterIsWet,true)
    }
}
