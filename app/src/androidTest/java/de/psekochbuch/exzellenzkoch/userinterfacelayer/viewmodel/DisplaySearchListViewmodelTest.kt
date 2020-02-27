package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.intent.IntentStubberRegistry
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientAmount
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.factories.DisplaySearchListViewModelFactory
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class DisplaySearchListViewmodelTest {
    val TAG = "DisplaySearchListViewModel"
    @get:Rule
    val rule = InstantTaskExecutorRule()

    //val repo  = mock(PublicRecipeRepositoryImp::class.java)
    val repo = PublicRecipeRepositoryImp()
    val vm = DisplaySearchListViewmodel(repo)

    @Test
    fun getRecipes() {

       runBlocking {   vm.getPublicRecipes("beutel", "", "") }
        Thread.sleep(1000)
        var size = 0
        vm.recipes.observeForever {
            t: List<PublicRecipe>? ->
            if (t != null) {
                size = t.size }}
        var controllRecipe = vm.recipes.value?.first()
        Log.w(TAG, ("testtesttest"))

        assert(size > 0)
        if (controllRecipe != null) {
            assert(controllRecipe.title.contains("beutel"))
        }

    }

    @Test
    fun getRecipesSortedDate() {
    }

    @Test
    fun getPublicRecipes() {
    }
}
private fun <T> LiveData<T>.blockingObserve(): T? {
    var value: T? = null
    val latch = CountDownLatch(1)

    observeForever{
        value = it
        latch.countDown()
    }

    latch.await(2, TimeUnit.SECONDS)
    return value
}