package com.youtubeplaylist.retrofit

import com.youtubeplaylist.data.YouTubeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApiService {

    @GET("search")
    fun getChannelVideos(
        @Query("part") part: String = "snippet",
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: Int = 50,
        @Query("key") apiKey: String
    ): Call<YouTubeResponse>
}