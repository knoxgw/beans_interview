package com.example.sbtechincaltest.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sbtechincaltest.data.PhotosRepository

class PhotosViewModelFactory(private val repository: PhotosRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotosViewModel(repository) as T
    }
}