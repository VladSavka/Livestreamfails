package com.livestreamfails.streams.model

import org.jsoup.Jsoup

private const val POSTS_PATH = "/post"

object StreamRemoteDataSource : StreamDataSource {

    override suspend fun getStreamById(id: Int): StreamResult {
        return try {
            val requestUrl = "$BASE_URL$POSTS_PATH/$id"
            val doc = Jsoup.connect(requestUrl).get()

            val title = doc.getElementsByTag("title").first().text()

            val videoUrl = doc.getElementsByTag("meta")
                .first { it.attr("property") == "og:video" }
                .attr("content")

            StreamResult.Success(title, videoUrl)
        } catch (e: Exception) {
            StreamResult.Error(e.message ?: "")
        }
    }
}