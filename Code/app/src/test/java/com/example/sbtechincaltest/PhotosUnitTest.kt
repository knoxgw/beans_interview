package com.example.sbtechincaltest

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.sbtechincaltest.data.PhotosDataSource
import com.example.sbtechincaltest.data.PhotosRepository
import com.example.sbtechincaltest.data.internal.Photo
import com.example.sbtechincaltest.data.internal.RequestState
import com.example.sbtechincaltest.data.remote.OperationCallback
import com.example.sbtechincaltest.photos.PhotosViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.internal.verification.VerificationModeFactory.times

class PhotosUnitTest {

    @Mock
    private lateinit var photosDataSource: PhotosDataSource

    @Mock
    private lateinit var context: Application

    @Captor
    private lateinit var operationCallbackCaptor: ArgumentCaptor<OperationCallback<Photo>>

    private lateinit var viewModel: PhotosViewModel
    private lateinit var repository: PhotosRepository

    private lateinit var requestStateObserver: Observer<RequestState<List<Photo>>>

    private lateinit var photosEmptyList: List<Photo>
    private lateinit var photosList: List<Photo>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(context.applicationContext).thenReturn(context)

        repository = PhotosRepository(photosDataSource)
        viewModel = PhotosViewModel(repository)

        mockData()
        setupObservers()
    }

    @Test
    fun repositoryReturnsEmptyList() {
        with(viewModel) {
            fetchPhotos()
            requestState.observeForever(requestStateObserver)
        }

        verify(photosDataSource, times(1)).fetchPhotos(capture(operationCallbackCaptor))
        operationCallbackCaptor.value.onSuccess(photosEmptyList)

        Assert.assertTrue(viewModel.requestState.value is RequestState.Error)
    }

    @Test
    fun repositoryReturnsData() {
        with(viewModel) {
            fetchPhotos()
            requestState.observeForever(requestStateObserver)
        }

        verify(photosDataSource, times(1)).fetchPhotos(capture(operationCallbackCaptor))
        operationCallbackCaptor.value.onSuccess(photosList)

        Assert.assertTrue(viewModel.requestState.value is RequestState.Success<*>)
        Assert.assertTrue((viewModel.requestState.value as RequestState.Success<List<Photo>>).data.count() == 3)
    }

    @Test
    fun repositoryReturnsError() {
        with(viewModel) {
            fetchPhotos()
            requestState.observeForever(requestStateObserver)
        }

        verify(photosDataSource, times(1)).fetchPhotos(capture(operationCallbackCaptor))
        operationCallbackCaptor.value.onError("Error")

        Assert.assertTrue(viewModel.requestState.value is RequestState.Error)
    }

    private fun mockData() {
        photosEmptyList = emptyList()
        photosList = listOf(
            Photo(
                "accusamus beatae ad facilis cum similique qui sunt",
                "https://via.placeholder.com/150/92c952"
            ),
            Photo(
                "reprehenderit est deserunt velit ipsam",
                "https://via.placeholder.com/150/771796"
            ),
            Photo(
                "officia porro iure quia iusto qui ipsa ut modi",
                "https://via.placeholder.com/150/24f355"
            )
        )
    }

    private fun setupObservers() {
        requestStateObserver = mock(Observer::class.java) as Observer<RequestState<List<Photo>>>
    }

}