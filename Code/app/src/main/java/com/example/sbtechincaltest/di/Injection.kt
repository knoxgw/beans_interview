package com.example.sbtechincaltest.di

import com.example.sbtechincaltest.data.PhotosDataSource
import com.example.sbtechincaltest.data.PhotosRepository
import com.example.sbtechincaltest.data.remote.ApiClient
import com.example.sbtechincaltest.data.remote.PhotosRemoteDataSource
import com.example.sbtechincaltest.photos.PhotosViewModelFactory

object Injection {

    private var photosDataSource: PhotosDataSource? = null
    private var photosRepository: PhotosRepository? = null
    private var photosViewModelFactory: PhotosViewModelFactory? = null

    private fun createPhotosDataSource(): PhotosDataSource =
        PhotosRemoteDataSource(ApiClient).also { photosDataSource = it }

    private fun createPhotosRepository(): PhotosRepository =
        PhotosRepository(provideDataSource()).also { photosRepository = it }

    private fun createFactory(): PhotosViewModelFactory =
        PhotosViewModelFactory(provideRepository()).also { photosViewModelFactory = it }

    private fun provideDataSource() = photosDataSource ?: createPhotosDataSource()
    private fun provideRepository() = photosRepository ?: createPhotosRepository()

    fun provideViewModelFactory() = photosViewModelFactory ?: createFactory()

    fun destroy() {
        photosDataSource = null
        photosRepository = null
        photosViewModelFactory = null
    }
}