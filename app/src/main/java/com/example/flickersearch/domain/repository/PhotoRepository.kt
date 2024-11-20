package com.example.flickersearch.domain.repository

import com.example.flickersearch.domain.model.PhotoEntity
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getPhotos(query: String): Flow<List<PhotoEntity>>
}