package de.psekochbuch.exzellenzkoch.testcases.t11


import android.app.Application
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
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp.FavouriteRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp.PrivateRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.FeedViewModel
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.LoginViewModel
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeDisplayViewmodel
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
class t_11_1_create_update_public_recipe_test {

    fun getRandomString(length: Int) : String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    var firstRecipe = getRandomString(7)
    var secondRecipe = getRandomString(8)


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)


    @Before
    fun setup(){
        AuthentificationImpl.logout()
        val repo = PrivateRecipeRepositoryImp(ApplicationProvider.getApplicationContext())
        repo.deleteAll()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }
    @After
    fun tearDown(){
        AuthentificationImpl.logout()
        val repo = PrivateRecipeRepositoryImp(ApplicationProvider.getApplicationContext())
        repo.deleteAll()
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun t_11_1_create_update_public_recipe_test() {

        val listVm = RecipeListViewmodel(PrivateRecipeRepositoryImp(Application()), PublicRecipeRepositoryImp())
        listVm.recipes.blockingObserve()
        val feedVm = FeedViewModel(PublicRecipeRepositoryImp())
        feedVm.recipes.blockingObserve()


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
        appCompatEditText.perform(replaceText("max.musterman@muster.de"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
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
        appCompatEditText2.perform(replaceText("123456"), closeSoftKeyboard())


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

        Thread.sleep(EspressoIdlingResource.Sleep)

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
        appCompatEditText6.perform(scrollTo(), replaceText(firstRecipe), closeSoftKeyboard())

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
        appCompatEditText7.perform(scrollTo(), replaceText("10"))

        val appCompatEditText8 = onView(
            allOf(
                withId(R.id.editText_preparing_time_create_recipe_fragment), withText("10"),
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
        appCompatEditText9.perform(scrollTo(), replaceText("10"))

        val appCompatEditText10 = onView(
            allOf(
                withId(R.id.editText_cooking_time_create_recipe_fragment), withText("10"),
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

        val appCompatEditText11 = onView(
            allOf(
                withId(R.id.editText_portions_input),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        5
                    ),
                    1
                )
            )
        )
        appCompatEditText11.perform(scrollTo(), replaceText("4"), closeSoftKeyboard())

        val appCompatEditText12 = onView(
            allOf(
                withId(R.id.editText_ingredients_create_recipe_fragment),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    7
                )
            )
        )
        appCompatEditText12.perform(scrollTo(), replaceText("100 gramm mehl"), closeSoftKeyboard())

        val appCompatEditText13 = onView(
            allOf(
                withId(R.id.editText_preparation_description_create_recipe_fragment),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    8
                )
            )
        )
        appCompatEditText13.perform(scrollTo(), replaceText("Beschr"), closeSoftKeyboard())

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

        Thread.sleep(EspressoIdlingResource.Sleep)

        val appCompatImageButton6 = onView(
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
        appCompatImageButton6.perform(click())

        val navigationMenuItemView5 = onView(
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
        navigationMenuItemView5.perform(click())



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
        linearLayout.perform(click())

        val appCompatEditText14 = onView(
            allOf(
                withId(R.id.editText_recipe_title_create_recipe_fragment), withText(firstRecipe),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    1
                )
            )
        )
        appCompatEditText14.perform(scrollTo(), replaceText(this.secondRecipe))

        val appCompatEditText15 = onView(
            allOf(
                withId(R.id.editText_recipe_title_create_recipe_fragment), withText(secondRecipe),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText15.perform(closeSoftKeyboard())

        val appCompatCheckBox2 = onView(
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
        appCompatCheckBox2.perform(scrollTo(), click())

        Espresso.pressBack()

        Thread.sleep(EspressoIdlingResource.Sleep)
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

        val appCompatEditText16 = onView(
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
        appCompatEditText16.perform(replaceText(secondRecipe), closeSoftKeyboard())


        val appCompatButton5 = onView(
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
        appCompatButton5.perform(click())


        Thread.sleep(EspressoIdlingResource.Sleep)
        val linearLayout2 = onView(
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
        linearLayout2.perform(click())

        var vmd = RecipeDisplayViewmodel(PublicRecipeRepositoryImp(), FavouriteRecipeRepositoryImp(ApplicationProvider.getApplicationContext()))
        vmd.recipe.blockingObserve()

        val textView = onView(
            allOf(
                withId(R.id.textView_recipe_title), 
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
        textView.check(matches(isDisplayed()))


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
