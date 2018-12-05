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

class CreatePasswordTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<CreatePasswordActivity>(CreatePasswordActivity::class.java)



    @Before
    fun requiredComponents() {
        Intents.init()
    }

    // Scenario 302

    @Test
    fun createPasswordLater() {

        Espresso.onView(ViewMatchers.withId(R.id.SkipPass)).perform(ViewActions.click())

        intended(hasComponent(ProgramActivity::class.java!!.name))
    }


}