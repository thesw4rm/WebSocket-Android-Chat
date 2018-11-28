package com.example.ytpillai.cmsc_355_proj


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ContactsActivityTest2 {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun contactsActivityTest2() {
        val appCompatButton = onView(
                allOf(withId(R.id.SkipPass), withText("Set up Later"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()))
        appCompatButton.perform(click())


        val appCompatEditText = onView(
                allOf(withId(R.id.nickname),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()))
        appCompatEditText.perform(click())

        val appCompatEditText2 = onView(
                allOf(withId(R.id.nickname),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()))
        appCompatEditText2.perform(replaceText("Danny Mc"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
                allOf(withId(R.id.ipAddress),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()))
        appCompatEditText3.perform(replaceText("192.168.1.1"), closeSoftKeyboard())

        val appCompatButton3 = onView(
                allOf(withId(R.id.startChat), withText("Start Chat"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()))
        appCompatButton3.perform(click())

        val appCompatImageButton = onView(
                allOf(withId(R.id.backArrowBtn),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()))
        appCompatImageButton.perform(click())

        val viewGroup = onView(
                allOf(withId(R.id.contactRecyclerView),
                        childAtPosition(
                                allOf(withId(R.id.contactList),
                                        childAtPosition(
                                                IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                                                2)),
                                0),
                        isDisplayed()))
        viewGroup.check(matches(isDisplayed()))

        val textView = onView(
                allOf(withId(R.id.nicknameContactList), withText("Danny Mc"),
                        childAtPosition(
                                allOf(withId(R.id.contactRecyclerView),
                                        childAtPosition(
                                                withId(R.id.contactList),
                                                0)),
                                2),
                        isDisplayed()))
        textView.check(matches(withText("Danny Mc")))
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
