package de.psekochbuch.exzellenzkoch.testcases.t9


import android.view.View
import android.view.ViewGroup
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
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
class t_9_1_save_recipe_automatically {


    @Before
    fun setup(){
        val repo =  PrivateRecipeRepositoryImp(ApplicationProvider.getApplicationContext())
        repo.deleteAll()
    }

    @After
    fun tearDown(){
        val repo =  PrivateRecipeRepositoryImp(ApplicationProvider.getApplicationContext())
        repo.deleteAll()
    }



    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun t_9_1_save_recipe_automatically() {
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
                    4
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(click())

        val appCompatButtonNewRecipe = onView(
            allOf(
                withId(R.id.button_create_recipe), withText("Neues Rezept erstellen"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout),
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
        appCompatButtonNewRecipe.perform(click())

        val appCompatEditTextCookingTime = onView(
            allOf(
                withId(R.id.editText_cooking_time_create_recipe_fragment), withText("0"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        4
                    ),
                    1
                )
            )
        )
        appCompatEditTextCookingTime.perform(scrollTo(), longClick())

        val appCompatEditTextCookingTime2 = onView(
            allOf(
                withId(R.id.editText_cooking_time_create_recipe_fragment), withText("0"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        4
                    ),
                    1
                )
            )
        )
        appCompatEditTextCookingTime2.perform(scrollTo(), replaceText("20"))

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.editText_cooking_time_create_recipe_fragment), withText("20"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        4
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(closeSoftKeyboard())

        val appCompatEditTextPrepTime = onView(
            allOf(
                withId(R.id.editText_preparing_time_create_recipe_fragment), withText("0"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        3
                    ),
                    1
                )
            )
        )
        appCompatEditTextPrepTime.perform(scrollTo(), replaceText("40"))

        val appCompatEditTextPrepTime2 = onView(
            allOf(
                withId(R.id.editText_preparing_time_create_recipe_fragment), withText("40"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        3
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditTextPrepTime2.perform(closeSoftKeyboard())

        val appCompatEditTextRecipeTitle = onView(
            allOf(
                withId(R.id.editText_recipe_title_create_recipe_fragment),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    1
                )
            )
        )
        appCompatEditTextRecipeTitle.perform(
            scrollTo(),
            replaceText("Pizza teig"),
            closeSoftKeyboard()
        )

        val appCompatCheckBox = onView(
            allOf(
                withId(R.id.checkBox_vegan_create_recipe_fragment), withText("vegan"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    0
                )
            )
        )
        appCompatCheckBox.perform(scrollTo(), click())

        // go back to recipelist
        Espresso.pressBack()
        Thread.sleep(5000)

        val linearLayout = onView(
            allOf(
                withId(R.id.recipe_list_layout_item),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerView_recipe_list_fragment),
                        childAtPosition(
                            withId(R.id.constraintLayout),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )

        Thread.sleep(2000)
        // click on recipe
        linearLayout.perform(click())

        val editTextTitle = onView(
            allOf(
                withId(R.id.editText_recipe_title_create_recipe_fragment), withText("Pizza teig"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.ScrollView::class.java),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        editTextTitle.check(matches(withText("Pizza teig")))


        val editTextPrepTime = onView(
            allOf(
                withId(R.id.editText_preparing_time_create_recipe_fragment), withText("40"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        3
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        editTextPrepTime.check(matches(withText("40")))

        val editTextCookingTime = onView(
            allOf(
                withId(R.id.editText_cooking_time_create_recipe_fragment), withText("20"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        4
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        editTextCookingTime.check(matches(withText("20")))

        val checkBox = onView(
            allOf(
                withId(R.id.checkBox_vegan_create_recipe_fragment),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        checkBox.check(matches(isDisplayed()))

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
