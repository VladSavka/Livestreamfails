package com.livestreamfails.streams.model

sealed class StreamResult {
    data class Success(val title: String, val url: String) : StreamResult()
    data class Error(val errorMassage: String) : StreamResult()
}