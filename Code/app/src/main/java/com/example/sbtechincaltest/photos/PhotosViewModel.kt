package com.example.sbtechincaltest.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sbtechincaltest.data.PhotosRepository
import com.example.sbtechincaltest.data.internal.Photo
import com.example.sbtechincaltest.data.internal.RequestState
import com.example.sbtechincaltest.data.remote.ApiClient
import com.example.sbtechincaltest.data.remote.OperationCallback
import com.example.sbtechincaltest.data.remote.PhotosRemoteDataSource

class PhotosViewModel : ViewModel() {

    private val _requestState = MutableLiveData<RequestState<List<Photo>>>()
    val requestState : LiveData<RequestState<List<Photo>>> = _requestState

    private val photosRepository = PhotosRepository(PhotosRemoteDataSource(ApiClient)) // should be inserted via DI

    fun fetchPhotos() {
        _requestState.value = RequestState.Pending()
        photosRepository.fetchPhotos(object : OperationCallback<Photo> {
            override fun onSuccess(data: List<Photo>?) {
                if (data.isNullOrEmpty()) {
                    _requestState.value = RequestState.Error()
                } else {
                    _requestState.value = RequestState.Success(data)
                }
            }

            override fun onError(error: String?) {
                _requestState.value = RequestState.Error()
            }
        })
    }
}