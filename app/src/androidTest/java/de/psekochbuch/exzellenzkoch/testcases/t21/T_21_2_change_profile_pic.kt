package de.psekochbuch.exzellenzkoch.testcases.t21

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import de.psekochbuch.exzellenzkoch.MainActivity
import de.psekochbuch.exzellenzkoch.R
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * This test sets up a logged in user before it starts. The user has given the app permission to access
 * the picture intent. The user is in his profile edit view and wants to load a new profile pic.
 * He clicks the profile picture and chooses one picture to upload then clicks save.
 * The picture is shown in his profile display view. Assert that the picture coming from the server
 * is the same as the previously set.
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class T_21_2_change_profile_pic {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {

    }

}