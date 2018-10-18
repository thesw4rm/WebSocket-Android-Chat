package com.example.ytpillai.cmsc_355_proj

import org.junit.Test
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before

//import android.app.PendingIntent.getActivity
//import android.content.Intent
//import android.support.test.espresso.assertion.ViewAssertions
//import android.support.test.runner.AndroidJUnit4
//import android.support.v4.content.ContextCompat.startActivity
//import android.support.test.espresso.matcher.ViewMatchers.withText
//import org.junit.runner.RunWith
//import android.support.test.InstrumentationRegistry.getTargetContext
//import android.content.ComponentName
//import java.util.regex.Pattern.matches
//import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
//import android.support.test.espresso.matcher.RootMatchers.withDecorView
//import android.support.test.espresso.Espresso.onView
//import org.hamcrest.CoreMatchers.`is`
//import org.hamcrest.CoreMatchers.not


class ProgramActivityTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<ProgramActivity>(ProgramActivity::class.java)

    @Before
    fun reqiuredComponents() {
        Intents.init()
    }


    //Scenario: 3.4
    @Test
    fun connectChat() {

        onView(withId(R.id.ipAddress)).perform(typeText("192.168.1.1"));

        Espresso.onView(ViewMatchers.withId(R.id.startChat)).perform(ViewActions.click())

        intended(hasComponent(ConversationActivity::class.java!!.getName()))
    }

//    @Test
//    fun notValidIP() {
//
//
//
//
//        onView(withText(R.string.TOAST_STRING)).inRoot(withDecorView(not(getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
//
//        onView(withId(R.id.ipAddress)).perform(typeText("1234--"));
//
//        Espresso.onView(ViewMatchers.withId(R.id.startChat)).perform(ViewActions.click())
//
////        onView(withText(R.string.TOAST_STRING)).inRoot(withDecorView(not(`is`(getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()))
//    }

}