package com.livestreamfails.streams.model

interface StreamRepository {
    suspend fun getStreamById(id: Int): StreamResult
}