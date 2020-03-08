package de.psekochbuch.exzellenzkoch.testcases.t9


import android.view.View
import android.view.ViewGroup
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import de.psekochbuch.exzellenzkoch.EspressoIdlingResource
import de.psekochbuch.exzellenzkoch.MainActivity
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp.PrivateRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeListViewmodel
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

@LargeTest
@RunWith(AndroidJUnit4::class)
class T_9_2_autosave_published_recipe {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource(){
        val repo = PrivateRecipeRepositoryImp(ApplicationProvider.getApplicationContext())
        repo.deleteAll()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        AuthentificationImpl.logout()
    }

    @After
    fun unregister(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        val repo = PrivateRecipeRepositoryImp(ApplicationProvider.getApplicationContext())
        repo.deleteAll()
        AuthentificationImpl.logout()
    }

    @Test
    fun t_9_2_autosave_published_recipe() {

        val vm = RecipeListViewmodel(PrivateRecipeRepositoryImp(ApplicationProvider.getApplicationContext()), PublicRecipeRepositoryImp())
        vm.recipes.blockingObserve()

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
                    3
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.editText_login_fragment_email),
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
        appCompatEditText.perform(replaceText("max"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.editText_login_fragment_email), withText("max"),
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
        appCompatEditText2.perform(click())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.editText_login_fragment_email), withText("max"),
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
        appCompatEditText3.perform(replaceText("max.musterman@muster.de"))

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.editText_login_fragment_email), withText("max.musterman@muster.de"),
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
        appCompatEditText4.perform(closeSoftKeyboard())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.editText_login_fragment_password),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout),
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
        appCompatEditText5.perform(replaceText("123456"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.button_login_fragment_login), withText("Einloggen"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

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

        val appCompatButton2 = onView(
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
        appCompatButton2.perform(click())

        val appCompatEditText6 = onView(
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
        appCompatEditText6.perform(scrollTo(), replaceText("Superrezept"), closeSoftKeyboard())

        val appCompatEditText7 = onView(
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
        appCompatEditText7.perform(scrollTo(), replaceText("40"))

        val appCompatEditText8 = onView(
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
        appCompatEditText8.perform(closeSoftKeyboard())

        val appCompatEditText9 = onView(
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
        appCompatEditText9.perform(scrollTo(), replaceText("23"))

        val appCompatEditText10 = onView(
            allOf(
                withId(R.id.editText_cooking_time_create_recipe_fragment), withText("23"),
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
        appCompatEditText10.perform(closeSoftKeyboard())

        val appCompatCheckBox = onView(
            allOf(
                withId(R.id.checkBox_publish_create_recipe_fragment), withText("veröffentlichen"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        9
                    ),
                    0
                )
            )
        )
        appCompatCheckBox.perform(scrollTo(), click())

        Espresso.pressBack()

        val appCompatImageButton3 = onView(
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
        appCompatImageButton3.perform(click())

        val navigationMenuItemView3 = onView(
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
        navigationMenuItemView3.perform(click())

        val appCompatEditText11 = onView(
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
        appCompatEditText11.perform(replaceText("Superrezept"), closeSoftKeyboard())

        val appCompatButton3 = onView(
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
        appCompatButton3.perform(click())

        Thread.sleep(3000)

        val linearLayout = onView(
            allOf(
                withId(R.id.display_searchlist_layout_Item),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerView_searchlist_fragment),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout.perform(click())

        Thread.sleep(3000)

        val textView = onView(
            allOf(
                withId(R.id.textView_recipe_title), withText("Superrezept"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout4),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Superrezept")))

        val textView2 = onView(
            allOf(
                withId(R.id.textView_recipe_prep_time), withText("40"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout4),
                        3
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("40")))

        val textView3 = onView(
            allOf(
                withId(R.id.textView_recipe_cook_time), withText("23"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout4),
                        3
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("23")))
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
private fun <T> LiveData<T>.blockingObserve(): T? {
    var value: T? = null
    val latch = CountDownLatch(1)

    observeForever{
        value = it
        latch.countDown()
    }

    latch.await()
    return value
}