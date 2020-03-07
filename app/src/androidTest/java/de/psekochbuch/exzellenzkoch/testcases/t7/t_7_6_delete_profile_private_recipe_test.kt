package de.psekochbuch.exzellenzkoch.testcases.t7


import android.app.Application
import android.view.View
import android.view.ViewGroup
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
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

@LargeTest
@RunWith(AndroidJUnit4::class)
class t_7_6_delete_profile_private_recipe_test {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)




    @Before
    fun registerIdlingResource(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        AuthentificationImpl.logout()


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

        val appCompatButton = onView(
            allOf(
                withId(R.id.button_login_fragment_register), withText("Registrieren"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.editText_register_email_input),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("tempoox@muster.de"), closeSoftKeyboard())


        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.editText_register_password_input),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("123456"), closeSoftKeyboard())


        val appCompatButton2 = onView(
            allOf(
                withId(R.id.button_register_fragment_register), withText("Registrieren"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        appCompatButton2.perform(click())


        Thread.sleep(EspressoIdlingResource.Sleep.toLong())

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.button_save_profile_changes), withText("Speichern"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout),
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
        appCompatButton3.perform(click())


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

        val appCompatButton4 = onView(
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
        appCompatButton4.perform(click())

        val appCompatEditText3 = onView(
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
        appCompatEditText3.perform(scrollTo(), replaceText("Rezepttitel"), closeSoftKeyboard())

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.editText_recipe_title_create_recipe_fragment), withText("Rezepttitel"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    1
                )
            )
        )
        appCompatEditText4.perform(pressImeActionButton())

        val appCompatEditText5 = onView(
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
        appCompatEditText5.perform(scrollTo(), replaceText("10"))

        val appCompatEditText6 = onView(
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
        appCompatEditText6.perform(closeSoftKeyboard())

        val appCompatEditText7 = onView(
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
        appCompatEditText7.perform(scrollTo(), replaceText("10"))

        val appCompatEditText8 = onView(
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
        appCompatEditText8.perform(closeSoftKeyboard())

        val appCompatEditText9 = onView(
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
        appCompatEditText9.perform(scrollTo(), replaceText("4"), closeSoftKeyboard())


        val appCompatEditText10 = onView(
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
        appCompatEditText10.perform(
            scrollTo(),
            replaceText(""),
            closeSoftKeyboard()
        )


        Thread.sleep(EspressoIdlingResource.Sleep.toLong())
        Thread.sleep(EspressoIdlingResource.Sleep)

        val appCompatEditText13 = onView(
            allOf(
                withId(R.id.editText_preparation_description_create_recipe_fragment),
                withText( ""),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    8
                ),
                isDisplayed()
            )
        )
        appCompatEditText13.perform(closeSoftKeyboard())

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

        Thread.sleep(EspressoIdlingResource.Sleep.toLong())

        val appCompatButton5 = onView(
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
        appCompatButton5.perform(scrollTo(), click())


        //der Nutzer hat sich registriert und ein Rezept erstellt, welches veröffentlicht ist.

    }


    @Test
    fun t_7_6_delete_profile_private_recipe_test() {
        //Der Nutzer will nun sein Profil löschen jedoch
        // sollen die von ihm erstellten, öffentlichen Rezepte privat verfügbar bleiben
        AuthentificationImpl.userDelete()



        val privaterepo = PrivateRecipeRepositoryImp(ApplicationProvider.getApplicationContext())
        val publicrepo = PublicRecipeRepositoryImp()
        val vm = RecipeListViewmodel(privaterepo, publicrepo)

       vm.getPrivateRecipes()
        Thread.sleep(EspressoIdlingResource.Sleep.toLong())

        for(recipe in vm.recipes.value!!){
            if(recipe.publishedRecipeId != 0){
                assert(false)
            }
        }
        assert(true)


    }
    @After
    fun unregister(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        AuthentificationImpl.userDelete()
        AuthentificationImpl.logout()
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
