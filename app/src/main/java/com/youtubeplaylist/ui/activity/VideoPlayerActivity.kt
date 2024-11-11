package com.youtubeplaylist.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

import com.youtubeplaylist.databinding.ActivityVideoPlayerBinding

class VideoPlayerActivity : AppCompatActivity() {
    lateinit var binding: ActivityVideoPlayerBinding
    //val videoId = "RazWA1DN0Dw"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycle.addObserver(binding.youtubePlayerView)
        val videoId = intent.getStringExtra("VIDEO_ID")
        Toast.makeText(this@VideoPlayerActivity, "Video Id ${videoId}", Toast.LENGTH_SHORT).show()


//        If you want more control, everything can be done programmatically by getting a reference to your
        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                // If videoId is null or empty, show a message or handle it accordingly
                if (videoId != null) {
                    if (videoId.isEmpty()) {
                        Toast.makeText(this@VideoPlayerActivity, "Video id Empty", Toast.LENGTH_SHORT)
                            .show()

                    } else {
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                }

            }
        })
    }


}

