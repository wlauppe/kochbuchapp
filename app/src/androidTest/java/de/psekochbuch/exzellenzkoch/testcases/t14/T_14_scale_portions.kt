package de.psekochbuch.exzellenzkoch.testcases.t14


import android.view.View
import android.view.ViewGroup
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
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * In this thest a published recipe is opened and the portions are scaled and change
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class T_14_scale_portions {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun t_14_scale_portions() {
        Thread.sleep(3000)

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

        Thread.sleep(EspressoIdlingResource.Sleep)

        val textView = onView(
            allOf(
                withId(R.id.textView_ingredient_list),
                withText("#Für die Windbeutel\r\n250 ml Wasser \r\n1 Prise Salz  \r\n80 g Butter \r\n150 g Mehl \r\n4 Eier \r\n100 g Zartbitter Kuvertüre \r\n#Für die Schokofüllung\r\n100 g Zartbitter Schokolade \r\n100 g Vollmilch Schokolade \r\n400 ml Sahne"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout4),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.ScrollView::class.java),
                            0
                        )
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        var initText = "#Für die Windbeutel\r 250 ml Wasser \r 1 Prise Salz  \r 80 g Butter \r 150 g Mehl \r 4 Eier \r 100 g Zartbitter Kuvertüre \r #Für die Schokofüllung\r 100 g Zartbitter Schokolade \r 100 g Vollmilch Schokolade \r 400 ml Sahne"

        val appCompatButton = onView(
            allOf(
                withId(R.id.button_portion_plus), withText("+"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout4),
                        4
                    ),
                    2
                )
            )
        )
        appCompatButton.perform(scrollTo(), click())

        Thread.sleep(EspressoIdlingResource.Sleep)

        val textView2 = onView(
            allOf(
                withId(R.id.textView_ingredient_list),
                withText("Für die Windbeutel\n160.0 g Butter\n8.0 Stück Eier\n300.0 g Mehl\n2.0 Prise Salz\n500.0 ml Wasser\n200.0 g Zartbitter-Kuvertüre\nFür die Schokofüllung\n800.0 ml Sahne\n200.0 g Vollmilch-Schokolade\n200.0 g Zartbitter-Schokolade\n"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout4),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.ScrollView::class.java),
                            0
                        )
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        var oncePressed = "Für die Windbeutel 160.0 g Butter 8.0 Stück Eier 300.0 g Mehl 2.0 Prise Salz 500.0 ml Wasser 200.0 g Zartbitter-Kuvertüre Für die Schokofüllung 800.0 ml Sahne 200.0 g Vollmilch-Schokolade 200.0 g Zartbitter-Schokolade "

        Thread.sleep(EspressoIdlingResource.Sleep)

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.button_portion_minus), withText("-"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout4),
                        4
                    ),
                    3
                )
            )
        )
        appCompatButton2.perform(scrollTo(), click())

        Thread.sleep(EspressoIdlingResource.Sleep)

        val textView3 = onView(
            allOf(
                withId(R.id.textView_ingredient_list),
                withText("Für die Windbeutel\n80.0 g Butter\n4.0 Stück Eier\n150.0 g Mehl\n1.0 Prise Salz\n250.0 ml Wasser\n100.0 g Zartbitter-Kuvertüre\nFür die Schokofüllung\n400.0 ml Sahne\n100.0 g Vollmilch-Schokolade\n100.0 g Zartbitter-Schokolade\n"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout4),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.ScrollView::class.java),
                            0
                        )
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText(initText)))
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
