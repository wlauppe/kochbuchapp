package de.psekochbuch.exzellenzkoch.uilayer


import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import de.psekochbuch.exzellenzkoch.MainActivity

import de.psekochbuch.exzellenzkoch.R

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginFragmentButtonsExistStaticTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loginFragmentStaticTest() {
        val appCompatImageButton = onView(
allOf(withContentDescription("Open navigation drawer"),
childAtPosition(
allOf(withId(R.id.toolbar),
childAtPosition(
withClassName(`is`("com.google.android.material.appbar.AppBarLayout")),
0)),
1),
isDisplayed()))
        appCompatImageButton.perform(click())
        
        val navigationMenuItemView = onView(
allOf(childAtPosition(
allOf(withId(R.id.design_navigation_view),
childAtPosition(
withId(R.id.nav_view),
0)),
1),
isDisplayed()))
        navigationMenuItemView.perform(click())
        
        val textView = onView(
allOf(withId(R.id.textView_register_email_text), withText("Gib deine E-Mail Adresse ein"),
childAtPosition(
allOf(withId(R.id.constraintLayout),
childAtPosition(
withId(R.id.nav_host_fragment),
0)),
0),
isDisplayed()))
        textView.check(matches(isDisplayed()))
        

        


        
        val button = onView(
allOf(withId(R.id.button_login_fragment_login),
childAtPosition(
allOf(withId(R.id.constraintLayout),
childAtPosition(
withId(R.id.nav_host_fragment),
0)),
4),
isDisplayed()))
        button.check(matches(isDisplayed()))
        

        
        val button2 = onView(
allOf(withId(R.id.button_login_fragment_register),
childAtPosition(
allOf(withId(R.id.constraintLayout),
childAtPosition(
withId(R.id.nav_host_fragment),
0)),
6),
isDisplayed()))
        button2.check(matches(isDisplayed()))

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
