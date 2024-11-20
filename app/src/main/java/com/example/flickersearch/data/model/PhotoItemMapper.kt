package com.example.flickersearch.data.model


import com.example.flickersearch.domain.model.PhotoEntity

fun PhotoItem.toPhotoEntity(): PhotoEntity {
    return PhotoEntity(
        title = this.title,
        imageUrl = this.media.m,
        link = this.link
    )
}