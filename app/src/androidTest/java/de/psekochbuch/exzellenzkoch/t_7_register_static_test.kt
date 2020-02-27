package de.psekochbuch.exzellenzkoch


import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewInteraction
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*

import de.psekochbuch.exzellenzkoch.R

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`

@LargeTest
@RunWith(AndroidJUnit4::class)
class t_7_register_static_test {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun t_7_register_static_test() {
        val appCompatImageButton = onView(
allOf(withContentDescription("Navigationsleiste öffnen"),
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
3),
isDisplayed()))
        navigationMenuItemView.perform(click())
        
        val appCompatButton = onView(
allOf(withId(R.id.button_login_fragment_register), withText("Registrieren"),
childAtPosition(
allOf(withId(R.id.constraintLayout),
childAtPosition(
withId(R.id.nav_host_fragment),
0)),
6),
isDisplayed()))
        appCompatButton.perform(click())
        
        val textView = onView(
allOf(withId(R.id.textView_register_email_text), withText("Gib deine E-Mail Adresse ein"),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
1),
isDisplayed()))
        textView.check(matches(withText("Gib deine E-Mail Adresse ein")))
        
        val editText = onView(
allOf(withId(R.id.editText_register_email_input), withText("max.musterman@muster.de"),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
2),
isDisplayed()))
        editText.check(matches(isDisplayed()))
        
        val textView2 = onView(
allOf(withId(R.id.textView_register_userid_text), withText("Gib dir einen Nutzernamen (optional)"),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
3),
isDisplayed()))
        textView2.check(matches(withText("Gib dir einen Nutzernamen (optional)")))
        
        val editText2 = onView(
allOf(withId(R.id.editText_register_usernid_input), withText("Nutzername"),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
4),
isDisplayed()))
        editText2.check(matches(isDisplayed()))
        
        val textView3 = onView(
allOf(withId(R.id.textView_register_password_text), withText("Gib dein Passwort ein"),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
5),
isDisplayed()))
        textView3.check(matches(withText("Gib dein Passwort ein")))
        
        val editText3 = onView(
allOf(withId(R.id.editText_register_password_input), withText("Passwort"),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
6),
isDisplayed()))
        editText3.check(matches(isDisplayed()))
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
