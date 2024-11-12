package com.youtubeplaylist.ui.activity

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.DefaultPlayerUiController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.youtubeplaylist.databinding.ActivityVideoPlayerBinding


class VideoPlayerActivity : AppCompatActivity() {
    lateinit var binding: ActivityVideoPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        lifecycle.addObserver(binding.youtubePlayerView)
        val videoId = intent.getStringExtra("VIDEO_ID")
        Toast.makeText(this@VideoPlayerActivity, "Video Id ${videoId}", Toast.LENGTH_SHORT).show()
        binding.youtubePlayerView.enableAutomaticInitialization=false

        if (videoId != null) {
            initYouTubePlayerView(videoId)
        }

      /*  val customPlayerUi: View =
            binding.youtubePlayerView.inflateCustomPlayerUi(com.youtubeplaylist.R.layout.activity_custom_uiactivity)
        val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                val customPlayerUiController: CustomPlayerUiController = CustomPlayerUiController(
                    this@VideoPlayerActivity,
                    customPlayerUi,
                    youTubePlayer,
                    binding.youtubePlayerView
                )
                youTubePlayer.addListener(customPlayerUiController)
                if (videoId != null) {
                    if (videoId.isEmpty()) {
                        Toast.makeText(this@VideoPlayerActivity, "Video id Empty", Toast.LENGTH_SHORT)
                            .show()

                    } else {
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                }
            }
        }*/
/*
// Listener for initializing the YouTube Player
        val listener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val defaultPlayerUiController = DefaultPlayerUiController(binding.youtubePlayerView, youTubePlayer)
                binding.youtubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView)
                // If videoId is null or empty, show a message or handle it accordingly
                if (videoId != null) {
                    if (videoId.isEmpty()) {
                        Toast.makeText(
                            this@VideoPlayerActivity,
                            "Video id Empty",
                            Toast.LENGTH_SHORT
                        )
                            .show()

                    } else {
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                }

            }

            override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                super.onError(youTubePlayer, error)
                Toast.makeText(
                    this@VideoPlayerActivity,
                    "Error loading video: ${error.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

       // Set up IFramePlayerOptions
        val options = IFramePlayerOptions.Builder().controls(0).build()

       // Initialize the YouTubePlayerView with the listener and options
        binding.youtubePlayerView.initialize(listener, options)*/


       /* //        If you want more control, everything can be done programmatically by getting a reference to your
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

                    override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                        super.onError(youTubePlayer, error)
                        Toast.makeText(this@VideoPlayerActivity, "Error loading video: ${error.name}", Toast.LENGTH_SHORT).show()
                    }

                })*/

    }
    private fun initYouTubePlayerView(videoId :String) {
        //lifecycle.addObserver(binding.youtubePlayerView)

        val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                // using pre-made custom ui

                val defaultPlayerUiController = DefaultPlayerUiController(binding.youtubePlayerView, youTubePlayer)
                binding.youtubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView)

                if (videoId.isEmpty()) {
                    Toast.makeText(
                        this@VideoPlayerActivity,
                        "Video id Empty",
                        Toast.LENGTH_SHORT
                    )
                        .show()

                } else {
                    youTubePlayer.loadVideo(videoId, 0f)
                }

            }
        }
        // disable web ui
        val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()
        binding.youtubePlayerView.initialize(listener, options)
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Checks the orientation of the screen
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            binding.youtubePlayerView.matchParent()
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            binding.youtubePlayerView.wrapContent()
        }
    }
}

