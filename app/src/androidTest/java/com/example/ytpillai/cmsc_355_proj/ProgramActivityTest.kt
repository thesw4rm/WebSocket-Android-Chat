package com.example.ytpillai.cmsc_355_proj


import android.content.Intent
import org.junit.Test

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.content.ContextCompat.startActivity
import org.junit.Rule
import org.junit.runner.RunWith


class ProgramActivityTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<ProgramActivity>(ProgramActivity::class.java::class.java)


    @Test
    fun onCreate() {

    }

    @Test
    fun connectChat() {

        Espresso.onView(ViewMatchers.withId(R.id.startChat)).perform(ViewActions.click())

        val intent = Intent(this, OtherActivity::class.java)
        startActivity(intent)


     /*   Espresso.onView(ViewMatchers.withId(R.id.MyTextView))
                .check(ViewAssertions.matches(ViewMatchers.withText("Hello mars")))*/

    }
}