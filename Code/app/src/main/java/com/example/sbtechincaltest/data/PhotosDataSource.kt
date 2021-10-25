package com.example.sbtechincaltest.data

import com.example.sbtechincaltest.data.remote.OperationCallback
import com.example.sbtechincaltest.photos.Photo

interface PhotosDataSource {
    fun fetchPhotos(callback: OperationCallback<Photo>)
    fun cancel()
}