package com.example.ytpillai.cmsc_355_proj

import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class AcceptanceTest<T : Activity>(clazz: Class<T>) {

    @Rule @JvmField
    val testRule: ActivityTestRule<T> = IntentsTestRule(clazz)

    val checkThat: Matchers = Matchers()
    val events: Events = Events()
}

