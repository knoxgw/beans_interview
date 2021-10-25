package com.example.sbtechincaltest.data

import com.example.sbtechincaltest.data.remote.OperationCallback
import com.example.sbtechincaltest.photos.Photo

class PhotosRepository(private val photosDataSource: PhotosDataSource) {

    fun fetchPhotos(callback: OperationCallback<Photo>) {
        photosDataSource.fetchPhotos(callback)
    }

    fun cancel() {
        photosDataSource.cancel()
    }
}