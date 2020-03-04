package de.psekochbuch.exzellenzkoch

import android.util.EventLogTags
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import java.sql.Statement


class OkHttpIdlingResourceRule //: TestRule {
/*
    private val resource : IdlingResource = OkHttp3IdlingResource.create("okhttp", OkHttpProvider.instance)

    override fun apply(base: Statement, description: EventLogTags.Description): Statement {
        return object: Statement() {
            override fun evaluate() {
                IdlingRegistry.getInstance().register(resource)
                base.evaluate()
                IdlingRegistry.getInstance().unregister(resource)
            }
        }
    }

 */
//}