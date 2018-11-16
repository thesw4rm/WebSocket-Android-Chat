package com.example.ytpillai.cmsc_355_proj

import org.junit.Test
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import org.junit.Before

class InputPasswordActivityTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<InputPasswordActivity>(InputPasswordActivity::class.java)



    @Before
    fun requiredComponents() {
        Intents.init()
    }

    @Test
    fun createPasswordLater() {

//        Espresso.onView(ViewMatchers.withId(R.id.SkipPass)).perform(ViewActions.click())

        intended(hasComponent(InputPasswordActivity::class.java!!.getName()))
    }


}