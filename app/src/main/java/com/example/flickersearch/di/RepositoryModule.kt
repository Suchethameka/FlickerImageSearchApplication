package com.example.flickersearch.di

import com.example.flickersearch.data.api.FlickrApiService
import com.example.flickersearch.domain.repository.PhotoRepository
import com.example.flickersearch.data.repository.PhotoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPhotoRepository(
        photoRepositoryImpl: PhotoRepositoryImpl
    ): PhotoRepository

    companion object {
        @Provides
        @Singleton
        fun provideFlickrApiService(): FlickrApiService {
            return Retrofit.Builder()
                .baseUrl("https://api.flickr.com/services/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FlickrApiService::class.java)
        }
    }
}
