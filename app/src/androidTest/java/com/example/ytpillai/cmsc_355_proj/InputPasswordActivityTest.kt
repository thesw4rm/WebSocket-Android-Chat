package com.example.ytpillai.cmsc_355_proj

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.pressBack
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InputPasswordActivityTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<InputPasswordActivity>(InputPasswordActivity::class.java)



    @Before
    fun requiredComponents() {
        Intents.init()
    }

//    @Test
//    fun createPasswordLater() {
//
//        intended(hasComponent(InputPasswordActivity::class.java!!.getName()))
//    }


    // Scenario 301
    @Test
    fun fingerPrintDialogisShown() {

        onView(withText("CANCEL")).check(matches((isDisplayed()))).perform(click());

        onView(withText("Inko")).check(doesNotExist());

        Intents.release()

    }

    // Scenario 303
    @Test
    fun reopenFingerPrintDialog() {

        onView(withText("Inko")).check(matches((isDisplayed()))).perform(pressBack());

        onView(withText("Fingerprint")).check(matches((isDisplayed()))).perform(click())

    }



}