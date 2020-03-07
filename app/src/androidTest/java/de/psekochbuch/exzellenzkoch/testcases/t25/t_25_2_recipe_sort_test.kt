package de.psekochbuch.exzellenzkoch.testcases.t25


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import de.psekochbuch.exzellenzkoch.EspressoIdlingResource
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
class t_25_2_recipe_sort_test {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun t_25_2_recipe_sort_test() {
        Thread.sleep(EspressoIdlingResource.Sleep)
        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Navigationsleiste öffnen"),
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
                    2
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.editText_search_recipe_title),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout3),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText(""), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.button_search_recipe_search_button), withText("Suchen"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout3),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    8
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        Thread.sleep(800)

        val appCompatRadioButton = onView(
            allOf(
                withId(R.id.radioButton_titel), withText("Titel"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatRadioButton.perform(click())

        Thread.sleep(300)

        val textView = onView(
            allOf(
                withId(R.id.textView_recipe_name),
                childAtPosition(
                    allOf(
                        withId(R.id.display_searchlist_layout_Item),
                        childAtPosition(
                            withId(R.id.recyclerView_searchlist_fragment),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Beutel")))

        val textView2 = onView(
            allOf(
                withId(R.id.textView_recipe_name), withText("Windbeutel mit Schokofüllung"),
                childAtPosition(
                    allOf(
                        withId(R.id.display_searchlist_layout_Item),
                        childAtPosition(
                            withId(R.id.recyclerView_searchlist_fragment),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Windbeutel mit Schokofüllung")))
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
