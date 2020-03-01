package de.psekochbuch.exzellenzkoch


import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.*
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class t_8_1_create_recipe_static_test {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)


    @Before
    fun setUp(){
        val appCompatImageButto5 = onView(
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
        appCompatImageButto5.perform(click())

        val navigationMenuItemView1 = onView(
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
        navigationMenuItemView1.perform(click())


        //Das Problem ist, dass der User token gesetzt ist und direkt angemeldet ist

        //Falls der User eingeloggt ist -> überspringe
        if(!AuthentificationImpl.isLogIn()) {
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
            //Thread.sleep(1000)


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

            // Thread.sleep(1000)
        }

        Thread.sleep(500)
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

        val appCompatButton = onView(
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
        appCompatButton.perform(click())

        val appCompatEditText = onView(
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
        appCompatEditText.perform(scrollTo(), replaceText("Testrezept"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.editText_recipe_title_create_recipe_fragment), withText("Testrezept"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    1
                )
            )
        )
        appCompatEditText2.perform(pressImeActionButton())

        val appCompatEditText3 = onView(
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
        appCompatEditText3.perform(scrollTo(), replaceText("20"))

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.editText_preparing_time_create_recipe_fragment), withText("20"),
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
        appCompatEditText4.perform(closeSoftKeyboard())

        val appCompatEditText5 = onView(
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
        appCompatEditText5.perform(scrollTo(), replaceText("20"))

        val appCompatEditText6 = onView(
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
        appCompatEditText6.perform(closeSoftKeyboard())

        val appCompatEditText7 = onView(
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
        appCompatEditText7.perform(scrollTo(), replaceText("4"), closeSoftKeyboard())

        val appCompatEditText8 = onView(
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
        appCompatEditText8.perform(
            scrollTo(),
            replaceText("200,karotten,gramm"),
            closeSoftKeyboard()
        )

        val appCompatEditText9 = onView(
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
        appCompatEditText9.perform(scrollTo(), replaceText("Karotten"), closeSoftKeyboard())

        /*
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


         */





    }
    @Test
    fun t_8_1_create_recipe_static_test() {

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.button_create_recipe_and_goto_RecipeList), withText("Speichern"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    10
                )
            )
        )
        appCompatButton2.perform(scrollTo(), click())


        pressBack()



        Thread.sleep(5000)


        @AfterClass
        fun tearDown(){
            //Rezepte löschen
        }
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
