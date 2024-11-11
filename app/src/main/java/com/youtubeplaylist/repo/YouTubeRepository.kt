package com.youtubeplaylist.repo

import com.youtubeplaylist.data.YouTubeResponse
import com.youtubeplaylist.retrofit.YouTubeApiService
import retrofit2.Call

class YouTubeRepository(private val apiService: YouTubeApiService) {

    fun getChannelVideos(channelId: String, apiKey: String): Call<YouTubeResponse> {
        return apiService.getChannelVideos(channelId = channelId, apiKey = apiKey)
    }
}
