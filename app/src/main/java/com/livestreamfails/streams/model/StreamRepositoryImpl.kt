package com.livestreamfails.streams.model

class StreamRepositoryImpl(private val streamRemoteDataSource: StreamDataSource) :
    StreamRepository {

    override suspend fun getStreamById(id: Int): StreamResult {
        return streamRemoteDataSource.getStreamById(id)
    }

    companion object {

        private var INSTANCE: StreamRepositoryImpl? = null

        /**
         * Returns the single instance of this class, creating it if necessary.
         * @param streamRemoteDataSource the backend data source
         * *
         * @return the [StreamRepository] instance
         */
        @JvmStatic
        fun getInstance(streamRemoteDataSource: StreamDataSource): StreamRepository {
            return INSTANCE ?: StreamRepositoryImpl(streamRemoteDataSource)
                .apply { INSTANCE = this }
        }

    }
}