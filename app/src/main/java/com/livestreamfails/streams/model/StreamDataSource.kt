package com.livestreamfails.streams.model

const val BASE_URL = "https://livestreamfails.com"

interface StreamDataSource {
    suspend fun getStreamById(id: Int): StreamResult
}