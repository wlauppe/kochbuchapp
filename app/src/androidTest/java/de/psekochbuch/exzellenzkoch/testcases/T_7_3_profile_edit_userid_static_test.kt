package de.psekochbuch.exzellenzkoch.testcases


import android.util.Log
import android.view.View
import android.view.ViewGroup
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
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ProfileEditViewmodel
import kotlinx.coroutines.runBlocking
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
class T_7_3_profile_edit_userid_static_test {

    var TAG = "profileedituseridstatictest"


    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)


    @Before
    fun registerIdlingResource(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregister(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }


    @Test
    fun t_7_2_profile_edit_static_test() {
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


        Log.w(TAG, "in das LOGINFRAGMENT")
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


            Log.w(TAG, "max.musterman@muster.de")
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


        //App muss warten, bis fragment geladen ist
        var name = ""
        var vm = ProfileEditViewmodel(UserRepositoryImp())
        runBlocking { name = vm.user.value?.userId.toString() }

      //  Thread.sleep(1000)


        val appCompatButton2 = onView(
            allOf(
                withId(R.id.button_profile_display_fragment_edit_profile),
                withText("Profil Bearbeiten"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        5
                    ),
                    0
                )
            )
        )
        appCompatButton2.perform(scrollTo(), click())

        // Thread.sleep(1000)



        val appCompatEditText8 = onView(
            allOf(
                withId(R.id.textView_enter_userID),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout2),
                        childAtPosition(
                            withClassName(`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText8.perform(closeSoftKeyboard())


        //Transision on target device could be turned on therefore little sleep time
        Thread.sleep(200)



        val appCompatButton5 = onView(
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
        appCompatButton5.perform(click())

        // Thread.sleep(1000)

        val textView = onView(
            allOf(
                withId(R.id.textView_profile_display_description),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.ScrollView::class.java),
                        0
                    ),
                    2
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
