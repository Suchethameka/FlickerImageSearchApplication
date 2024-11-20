package com.example.flickersearch.domain.usecase

import com.example.flickersearch.domain.repository.PhotoRepository
import com.example.flickersearch.domain.model.PhotoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetPhotosUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) {
    operator fun invoke(query: String): Flow<List<PhotoEntity>> {
        return photoRepository.getPhotos(query)
    }
}