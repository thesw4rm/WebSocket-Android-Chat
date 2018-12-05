package com.example.ytpillai.cmsc_355_proj

import org.junit.Test
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.pressBack
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.not
import org.junit.Before

import com.example.ytpillai.cmsc_355_proj.InputPasswordActivity

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