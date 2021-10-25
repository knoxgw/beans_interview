package com.example.sbtechincaltest.data.remote

import com.example.sbtechincaltest.data.PhotosDataSource
import com.example.sbtechincaltest.data.internal.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosRemoteDataSource(apiClient: ApiClient) : PhotosDataSource {

    private var call: Call<List<PhotosApiDto>>? = null
    private val service = apiClient.build()

    override fun fetchPhotos(callback: OperationCallback<Photo>) {
        call = service.photos()
        call?.enqueue(object : Callback<List<PhotosApiDto>> {
            override fun onResponse(
                call: Call<List<PhotosApiDto>>,
                response: Response<List<PhotosApiDto>>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(mapApiToInternalDto(it))
                    } else {
                        callback.onError(response.message()) // probably want better error handling here
                    }
                }
            }

            override fun onFailure(call: Call<List<PhotosApiDto>>, t: Throwable) {
                callback.onError(t.message)
            }
        })
    }

    override fun cancel() {
        call?.cancel()
    }

    private fun mapApiToInternalDto(apiPhotos: List<PhotosApiDto>): List<Photo> {
        return apiPhotos.map { Photo(it.title, it.thumbnailUrl) }
    }
}