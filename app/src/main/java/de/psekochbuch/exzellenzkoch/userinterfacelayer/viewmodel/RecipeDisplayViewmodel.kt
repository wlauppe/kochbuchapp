package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.media.Image
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter
import java.security.Timestamp
import java.sql.Time

class RecipeDisplayViewmodel : ViewModel() {

    private lateinit var image: LiveData<Image>
    private lateinit var title: LiveData<String>
    private lateinit var preparationDescription: LiveData<String>
    private lateinit var ingredientChapter: LiveData<IngredientChapter>
    private lateinit var tagsList: LiveData<List<String>>
    private lateinit var recipeCookTime: LiveData<Int>
    private lateinit var recipePrepTime: LiveData<Int>
    private lateinit var creationTime: LiveData<Timestamp>
    private lateinit var rating: LiveData<Double>


    fun addToFavourites() {
        // TODO
    }

    fun ingredientChapterToString() {
        // TODO
    }

}