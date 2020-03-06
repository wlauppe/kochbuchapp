package de.psekochbuch.exzellenzkoch.navigation.favouritefragment


import android.view.View
import android.view.ViewGroup
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import de.psekochbuch.exzellenzkoch.EspressoIdlingResource
import de.psekochbuch.exzellenzkoch.MainActivity
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp.PrivateRecipeRepositoryImp
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class navigation_favouritefragment_to_recipedisplayfragment {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp(){
        var repo = PrivateRecipeRepositoryImp(ApplicationProvider.getApplicationContext())
        repo.deleteAll()
        Thread.sleep(EspressoIdlingResource.Sleep.toLong())
    }

    @After
    fun tearDown(){
        var repo = PrivateRecipeRepositoryImp(ApplicationProvider.getApplicationContext())
        repo.deleteAll()
    }

    @Test
    fun navigation_favouritefragment_to_recipedisplayfragment() {
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

        val appCompatImageButton2 = onView(
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
        appCompatImageButton2.perform(click())

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

        val linearLayout2 = onView(
            allOf(
                withId(R.id.favourite_layout_item),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerView_favourites),
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
        linearLayout2.perform(click())

        val linearLayout3 = onView(
            allOf(
                withId(R.id.linearLayout4),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout3.check(matches(isDisplayed()))
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
