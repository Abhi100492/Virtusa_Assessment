package com.example.dictionaryapp.repository

sealed class Response<T>(val data: T? = null,val errorMsg: String? = null) {
    class Loading<T> : Response<T>()
    class Success<T>(data: T? = null) : Response<T>(data = data)
    class Error<T>(errorMsg: String? = null) : Response<T>(errorMsg = errorMsg)
}
