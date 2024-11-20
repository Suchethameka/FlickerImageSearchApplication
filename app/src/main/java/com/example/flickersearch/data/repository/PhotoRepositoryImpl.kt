package com.example.flickersearch.data.repository

import com.example.flickersearch.data.api.FlickrApiService
import com.example.flickersearch.data.model.toPhotoEntity
import com.example.flickersearch.domain.model.PhotoEntity
import com.example.flickersearch.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val apiService: FlickrApiService
) : PhotoRepository {

    override fun getPhotos(query: String): Flow<List<PhotoEntity>> = flow {
        try {
            // Define constants for page and API key (replace "YOUR_API_KEY" with your actual key)
            val page = 1
            val apiKey = "Y51885ef41dff54033e1de374307f110c"

            // Make the API call
            val response = apiService.searchImages(apiKey, query, page)

            // Map the response to PhotoEntity
            val photos = response.photos.photo.map { photoItem ->
                photoItem.toPhotoEntity() // Ensure the mapping function exists
            }

            // Emit the result
            emit(photos)
        } catch (exception: Exception) {
            emit(emptyList()) // In case of an error, emit an empty list
        }
    }.catch { exception ->
        emit(emptyList()) // Handle exceptions and emit an empty list
    }
}


