package com.livestreamfails

import com.livestreamfails.streams.model.StreamRemoteDataSource
import com.livestreamfails.streams.model.StreamRepositoryImpl
import com.livestreamfails.streams.presentation.StreamDataToUiMapper

/**
 * Enables injection of mock implementations for
 * [StreamDataSource] at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
object Injection {
    fun provideTasksRepository() = StreamRepositoryImpl.getInstance(StreamRemoteDataSource)

    fun provideStreamDataToUiMapper() = StreamDataToUiMapper()
}
