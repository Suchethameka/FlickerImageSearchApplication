package com.example.flickersearch.data.model

data class FlickrResponse(
    val photos: Photos
)

data class Photos(
    val photo: List<PhotoItem>
)

data class PhotoItem(
    val title: String,
    val media: Media,
    val link: String
)

data class Media(
    val m: String
)

