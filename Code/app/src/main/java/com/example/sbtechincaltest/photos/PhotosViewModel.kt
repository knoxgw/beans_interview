package com.example.sbtechincaltest.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sbtechincaltest.data.PhotosRepository
import com.example.sbtechincaltest.data.remote.ApiClient
import com.example.sbtechincaltest.data.remote.OperationCallback
import com.example.sbtechincaltest.data.remote.PhotosRemoteDataSource

class PhotosViewModel : ViewModel() {
    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> = _photos

    private val photosRepository = PhotosRepository(PhotosRemoteDataSource(ApiClient)) // should be inserted via DI

    fun fetchPhotos() {
        photosRepository.fetchPhotos(object : OperationCallback<Photo> {
            override fun onSuccess(data: List<Photo>?) {
                if (data.isNullOrEmpty()) {
                    // handle error state
                } else {
                    _photos.value = data
                }
            }

            override fun onError(error: String?) {
                // do something with error
            }
        })
    }
}