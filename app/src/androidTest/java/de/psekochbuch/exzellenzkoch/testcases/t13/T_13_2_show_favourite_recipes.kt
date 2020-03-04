package de.psekochbuch.exzellenzkoch.testcases


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
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

/**
 * This test first adds new favors from the existing published recipes and then checks,
 * if they are shown correctly in the favorite menu
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class T_13_2_show_favourite_recipes {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun t_13_2_show_favourite_recipes() {

        Thread.sleep(3000)

        val linearLayout = onView(
            allOf(
                withId(R.id.feed_layout_item),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerView_feed),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout.perform(click())

        Thread.sleep(3000)

        val appCompatImageButton = onView(
            allOf(
                withId(R.id.imageButton_favourite),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout4),
                        1
                    ),
                    1
                )
            )
        )
        appCompatImageButton.perform(scrollTo(), click())

        Thread.sleep(3000)

        val appCompatImageButton4 = onView(
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
        appCompatImageButton4.perform(click())

        val navigationMenuItemView = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.nav_view),
                            0
                        )
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(click())

        Thread.sleep(3000)

        val recyclerView = onView(
            allOf(
                withId(R.id.recyclerView_favourites),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        recyclerView.check(matches(isDisplayed()))

        Thread.sleep(3000)

        val textView = onView(
            allOf(
                withId(R.id.textView_recipe_name_favourite),
                withText("Windbeutel mit Schokofüllung"),
                childAtPosition(
                    allOf(
                        withId(R.id.favourite_layout_item),
                        childAtPosition(
                            withId(R.id.recyclerView_favourites),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Windbeutel mit Schokofüllung")))

        Thread.sleep(3000)

        val textView2 = onView(
            allOf(
                withId(R.id.textView_recipe_name_favourite), withText("Testrunde1, rezept:3"),
                childAtPosition(
                    allOf(
                        withId(R.id.favourite_layout_item),
                        childAtPosition(
                            withId(R.id.recyclerView_favourites),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Testrunde1, rezept:3")))

        Thread.sleep(3000)

        val textView3 = onView(
            allOf(
                withId(R.id.textView_recipe_name_favourite), withText("Testrunde1, rezept:8"),
                childAtPosition(
                    allOf(
                        withId(R.id.favourite_layout_item),
                        childAtPosition(
                            withId(R.id.recyclerView_favourites),
                            2
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Testrunde1, rezept:8")))
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
