package de.psekochbuch.exzellenzkoch

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {

    private const val Resource = "GLOBAL"


    @JvmField val countingIdlingResource = CountingIdlingResource(Resource)




    fun increment(){
        countingIdlingResource.increment()

    }
    fun decrement(){
        if(!countingIdlingResource.isIdleNow){
            countingIdlingResource.decrement()
        }

    }

    val Sleep = 4000
}