package com.example.ytpillai.cmsc_355_proj


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ConversationTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun conversationTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(600)

        val appCompatButton = onView(
                allOf(withId(R.id.SkipPass), withText("Set up Later"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()))
        appCompatButton.perform(click())

        val textInputEditText = onView(
                allOf(withId(R.id.nickname),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nicknameHolder),
                                        0),
                                0),
                        isDisplayed()))
        textInputEditText.perform(replaceText("Danny Mc"), closeSoftKeyboard())

        val textInputEditText2 = onView(
                allOf(withId(R.id.ipAddress),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.ipAddressHolder),
                                        0),
                                0),
                        isDisplayed()))
        textInputEditText2.perform(replaceText("128.172.245.82"), closeSoftKeyboard())

        val appCompatButton2 = onView(
                allOf(withId(R.id.startChat), withText("Start Chat"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()))
        appCompatButton2.perform(click())

        val appCompatEditText = onView(
                allOf(withId(R.id.chatbox),
                        childAtPosition(
                                allOf(withId(R.id.chatboxLayout),
                                        childAtPosition(
                                                withClassName(`is`("android.support.constraint.ConstraintLayout")),
                                                3)),
                                0),
                        isDisplayed()))
        appCompatEditText.perform(replaceText("Hello world!"), closeSoftKeyboard())

        val appCompatButton3 = onView(
                allOf(withId(R.id.sendBtn),
                        childAtPosition(
                                allOf(withId(R.id.chatboxLayout),
                                        childAtPosition(
                                                withClassName(`is`("android.support.constraint.ConstraintLayout")),
                                                3)),
                                1),
                        isDisplayed()))
        appCompatButton3.perform(click())

        val textView = onView(
                allOf(withId(R.id.ChatBodySent), withText("Hello world!"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.chatRecycler),
                                        0),
                                0),
                        isDisplayed()))
        textView.check(matches(withText("Hello world!")))

        val recyclerView = onView(
                allOf(withId(R.id.chatRecycler),
                        childAtPosition(
                                withClassName(`is`("android.support.constraint.ConstraintLayout")),
                                2)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
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
