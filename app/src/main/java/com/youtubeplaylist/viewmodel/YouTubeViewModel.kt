package com.youtubeplaylist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.youtubeplaylist.data.VideoItem
import com.youtubeplaylist.data.YouTubeResponse
import com.youtubeplaylist.repo.YouTubeRepository
import com.youtubeplaylist.retrofit.YouTubeApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class YouTubeViewModel(application: Application) : AndroidViewModel(application) {

    private val apiKey = "AIzaSyBCZJ3dNw1nyegbtiCX_GhGlwF7MYw6Y-4"
    private val channelId = "UCCmFz2da-mOk86Rn1-cqkGw"

    private val _videos = MutableLiveData<List<VideoItem>>()
    val videos: LiveData<List<VideoItem>> get() = _videos

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    // Create an instance of Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/youtube/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Create an instance of YouTubeApiService
    private val apiService = retrofit.create(YouTubeApiService::class.java)

    // Create an instance of the Repository
    private val repository = YouTubeRepository(apiService)

    // Function to fetch videos
    fun fetchVideos() {
        // Call the repository method
        repository.getChannelVideos(channelId, apiKey).enqueue(object : Callback<YouTubeResponse> {
            override fun onResponse(call: Call<YouTubeResponse>, response: Response<YouTubeResponse>) {
                if (response.isSuccessful) {
                    _videos.postValue(response.body()?.items)
                } else {
                    _error.postValue("Error fetching data")
                }
            }

            override fun onFailure(call: Call<YouTubeResponse>, t: Throwable) {
                _error.postValue("Error: ${t.message}")
            }
        })
    }
}