package com.example.flickersearch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickersearch.domain.model.PhotoEntity
import com.example.flickersearch.domain.usecase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    private val _photos = MutableStateFlow<List<PhotoEntity>>(emptyList())
    val photos: StateFlow<List<PhotoEntity>> = _photos.asStateFlow()

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState.asStateFlow()

    fun fetchPhotos(query: String) {
        _loadingState.value = true
        viewModelScope.launch {
            getPhotosUseCase(query)
                .onEach { photos ->
                    _photos.value = photos
                    _loadingState.value = false
                }
                .catch { _loadingState.value = false }
                .collect()
        }
    }
}

