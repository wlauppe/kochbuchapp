package com.example.simplekochbuchapp.domainlayer.interfaces.repository

import androidx.lifecycle.LiveData
import com.example.simplekochbuchapp.domainlayer.domainentities.TagList

interface TagRepository {
    fun getTags():TagList
}