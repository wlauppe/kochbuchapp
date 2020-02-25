package de.psekochbuch.exzellenzkoch.uilayer


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import de.psekochbuch.exzellenzkoch.MainActivity
import de.psekochbuch.exzellenzkoch.R
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
class MainMenuisCompleteTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainMenuisCompleteTest() {
        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Open navigation drawer"),
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            withClassName(`is`("com.google.android.material.appbar.AppBarLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val textView = onView(
            allOf(
                withText("Menü"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation_header_container),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Menü")))

        val textView2 = onView(
            allOf(
                withId(R.id.textView), withText("Exzellenz-Koch"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation_header_container),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Exzellenz-Koch")))

        val checkedTextView = onView(
            allOf(
                withId(R.id.design_menu_item_text),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.design_navigation_view),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        checkedTextView.check(matches(isDisplayed()))

        val checkedTextView2 = onView(
            allOf(
                withId(R.id.design_menu_item_text),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.design_navigation_view),
                        2
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        checkedTextView2.check(matches(isDisplayed()))

        val checkedTextView3 = onView(
            allOf(
                withId(R.id.design_menu_item_text),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.design_navigation_view),
                        3
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        checkedTextView3.check(matches(isDisplayed()))

        val checkedTextView4 = onView(
            allOf(
                withId(R.id.design_menu_item_text),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.design_navigation_view),
                        4
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        checkedTextView4.check(matches(isDisplayed()))

        val checkedTextView5 = onView(
            allOf(
                withId(R.id.design_menu_item_text),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.design_navigation_view),
                        5
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        checkedTextView5.check(matches(isDisplayed()))

        val checkedTextView6 = onView(
            allOf(
                withId(R.id.design_menu_item_text),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.design_navigation_view),
                        6
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        checkedTextView6.check(matches(isDisplayed()))

        val checkedTextView7 = onView(
            allOf(
                withId(R.id.design_menu_item_text),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.design_navigation_view),
                        6
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        checkedTextView7.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

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
