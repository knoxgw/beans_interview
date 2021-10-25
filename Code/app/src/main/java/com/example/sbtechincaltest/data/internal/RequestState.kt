package com.example.sbtechincaltest.data.internal

sealed class RequestState<T>(val message: String) {
    class Success<T>(val data: T) : RequestState<T>("Request successful")
    class Pending<T> : RequestState<T>("Request pending")
    class Error<T> : RequestState<T>("Request failed")
}