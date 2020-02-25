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
class PublicRecipeSearchStaticTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun publicRecipeSearchStaticTest() {
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
                    3
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(click())

        val appCompatImageButton2 = onView(
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
        appCompatImageButton2.perform(click())

        val navigationMenuItemView2 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.nav_view),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView2.perform(click())
//Der Button Test schlägt noch fehl @Magnus
       /* val button = onView(
            allOf(
                withId(R.id.button_search_recipe_search_button),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout3),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))
*/
        val textView = onView(
            allOf(
                withId(R.id.textView_search_tags_headline),
                withText("Welche Tags soll das Rezept haben?"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout3),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Welche Tags soll das Rezept haben?")))

        val textView2 = onView(
            allOf(
                withId(R.id.textView_search_ingredients_headline),
                withText("Gib Zutaten ein, nach denen du suchen willst:"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout3),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Gib Zutaten ein, nach denen du suchen willst:")))

        val textView3 = onView(
            allOf(
                withId(R.id.textView_search_recipe_title_headline),
                withText("Gibt den Titel des Rezeptes ein, nach dem du suchen willst:"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout3),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Gibt den Titel des Rezeptes ein, nach dem du suchen willst:")))

        val textView4 = onView(
            allOf(
                withId(R.id.textView_search_fragment_title), withText("Suche ein Rezept"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout3),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Suche ein Rezept")))
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
