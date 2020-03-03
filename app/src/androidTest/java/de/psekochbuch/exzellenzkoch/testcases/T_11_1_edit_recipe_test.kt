package de.psekochbuch.exzellenzkoch.testcases

import android.app.Application
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import de.psekochbuch.exzellenzkoch.EspressoIdlingResource
import de.psekochbuch.exzellenzkoch.MainActivity
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp.PrivateRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class T_11_1_edit_recipe_test {


    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        AuthentificationImpl.logout()
    }

    @After
    fun unregister(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        var repo = PrivateRecipeRepositoryImp(Application())
        repo.deleteAll()
        AuthentificationImpl.logout()
    }

    @Test
    fun t_9_3_create_private_recipe_test() {

        //Einloggen
        val toolbar = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withContentDescription("Navigationsleiste öffnen"),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.toolbar),
                        childAtPosition(
                            ViewMatchers.withClassName(Matchers.`is`("com.google.android.material.appbar.AppBarLayout")),
                            0
                        )
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        toolbar.perform(ViewActions.click())

        val navigation = Espresso.onView(
            Matchers.allOf(
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.design_navigation_view),
                        childAtPosition(
                            ViewMatchers.withId(R.id.nav_view),
                            0
                        )
                    ),
                    3
                ),
                ViewMatchers.isDisplayed()
            )
        )
        navigation.perform(ViewActions.click())

        val loginfragment = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.editText_login_fragment_email),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.constraintLayout),
                        childAtPosition(
                            ViewMatchers.withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        loginfragment.perform(
            ViewActions.replaceText("max.musterman@muster.de"),
            ViewActions.closeSoftKeyboard()
        )

        val password = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.editText_login_fragment_password),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.constraintLayout),
                        childAtPosition(
                            ViewMatchers.withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    3
                ),
                ViewMatchers.isDisplayed()
            )
        )
        password.perform(
            ViewActions.replaceText("123456"),
            ViewActions.closeSoftKeyboard()
        )

        val button = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.button_login_fragment_login),
                ViewMatchers.withText("Einloggen"),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.constraintLayout),
                        childAtPosition(
                            ViewMatchers.withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    4
                ),
                ViewMatchers.isDisplayed()
            )
        )
        button.perform(ViewActions.click())

        //
        val appCompatImageButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withContentDescription("Navigationsleiste öffnen"),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.toolbar),
                        childAtPosition(
                            ViewMatchers.withClassName(Matchers.`is`("com.google.android.material.appbar.AppBarLayout")),
                            0
                        )
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatImageButton.perform(ViewActions.click())

        //Transistion
        Thread.sleep(300)
        val navigationMenuItemView = Espresso.onView(
            Matchers.allOf(
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.design_navigation_view),
                        childAtPosition(
                            ViewMatchers.withId(R.id.nav_view),
                            0
                        )
                    ),
                    4
                ),
                ViewMatchers.isDisplayed()
            )
        )
        navigationMenuItemView.perform(ViewActions.click())

        val appCompatButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.button_create_recipe),
                ViewMatchers.withText("Neues Rezept erstellen"),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.constraintLayout),
                        childAtPosition(
                            ViewMatchers.withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatButton.perform(ViewActions.click())

        val appCompatEditText = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.editText_recipe_title_create_recipe_fragment),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.ScrollView")),
                        0
                    ),
                    1
                )
            )
        )
        appCompatEditText.perform(
            ViewActions.scrollTo(),
            ViewActions.replaceText("NeuesRezept"),
            ViewActions.closeSoftKeyboard()
        )

        val appCompatEditText2 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.editText_preparing_time_create_recipe_fragment),
                ViewMatchers.withText("0"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.LinearLayout")),
                        3
                    ),
                    1
                )
            )
        )
        appCompatEditText2.perform(ViewActions.scrollTo(), ViewActions.replaceText("10"))

        val appCompatEditText3 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.editText_preparing_time_create_recipe_fragment),
                ViewMatchers.withText("10"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.LinearLayout")),
                        3
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatEditText3.perform(ViewActions.closeSoftKeyboard())

        val appCompatEditText4 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.editText_cooking_time_create_recipe_fragment),
                ViewMatchers.withText("0"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.LinearLayout")),
                        4
                    ),
                    1
                )
            )
        )
        appCompatEditText4.perform(ViewActions.scrollTo(), ViewActions.replaceText("20"))

        val appCompatEditText5 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.editText_cooking_time_create_recipe_fragment),
                ViewMatchers.withText("20"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.LinearLayout")),
                        4
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatEditText5.perform(ViewActions.closeSoftKeyboard())

        val appCompatEditText6 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.editText_portions_input),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.LinearLayout")),
                        5
                    ),
                    1
                )
            )
        )
        appCompatEditText6.perform(
            ViewActions.scrollTo(),
            ViewActions.replaceText("4"),
            ViewActions.closeSoftKeyboard()
        )

        val appCompatEditText7 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.editText_ingredients_create_recipe_fragment),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.ScrollView")),
                        0
                    ),
                    7
                )
            )
        )
        appCompatEditText7.perform(
            ViewActions.scrollTo(),
            ViewActions.replaceText("Zwiebeln "),
            ViewActions.closeSoftKeyboard()
        )

        val appCompatEditText8 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.editText_preparation_description_create_recipe_fragment),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.ScrollView")),
                        0
                    ),
                    8
                )
            )
        )
        appCompatEditText8.perform(
            ViewActions.scrollTo(),
            ViewActions.replaceText("Nur ein paar worte"),
            ViewActions.closeSoftKeyboard()
        )

        val appCompatButton2 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.button_create_recipe_and_goto_RecipeList),
                ViewMatchers.withText("Speichern"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.ScrollView")),
                        0
                    ),
                    10
                )
            )
        )
        appCompatButton2.perform(ViewActions.scrollTo(), ViewActions.click())

        val appCompatImageButton2 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withContentDescription("Nach oben"),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.toolbar),
                        childAtPosition(
                            ViewMatchers.withClassName(Matchers.`is`("com.google.android.material.appbar.AppBarLayout")),
                            0
                        )
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatImageButton2.perform(ViewActions.click())

        val linearLayout = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.recipe_list_layout_item),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.recyclerView_recipe_list_fragment),
                        childAtPosition(
                            ViewMatchers.withId(R.id.constraintLayout),
                            0
                        )
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        linearLayout.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        val textView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.textView_recipe_title_item),
                ViewMatchers.withText("NeuesRezept"),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.recipe_list_layout_item),
                        childAtPosition(
                            ViewMatchers.withId(R.id.recyclerView_recipe_list_fragment),
                            0
                        )
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        textView.check(ViewAssertions.matches(ViewMatchers.withText("NeuesRezept")))
 //TODO check ob rezept serverseitig existiert und aktualisiert wurde
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