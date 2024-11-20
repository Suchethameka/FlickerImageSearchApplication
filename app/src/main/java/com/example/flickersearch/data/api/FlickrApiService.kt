package com.example.flickersearch.data.api

import com.example.flickersearch.data.model.FlickrResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1")
    suspend fun searchImages(
        @Query("api_key") apiKey: String,
        @Query("text") query: String,
        @Query("page") page: Int
    ): FlickrResponse
}