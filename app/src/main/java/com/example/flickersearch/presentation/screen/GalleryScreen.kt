package com.example.flickersearch.presentation.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.flickersearch.presentation.viewmodel.GalleryViewModel

@Composable
fun GalleryScreen(viewModel: GalleryViewModel = hiltViewModel()) {
    val photos by viewModel.photos.collectAsState()
    val isLoading by viewModel.loadingState.collectAsState()

    var query by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = query,
            onValueChange = {
                query = it
                viewModel.fetchPhotos(query)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search photos") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            LazyColumn {
                items(photos) { photo ->
                    PhotoItem(photo)
                }
            }
        }
    }
}

@Composable
fun PhotoItem(photo: com.example.flickersearch.domain.model.PhotoEntity) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Image(
            painter = rememberImagePainter(photo.imageUrl),
            contentDescription = photo.title,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(photo.title, modifier = Modifier.align(Alignment.CenterVertically))
    }
}
