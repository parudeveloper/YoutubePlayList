package com.youtubeplaylist.ui.activity

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.youtubeplaylist.R

class CustomPlayerUiController(
    private val context: Context,
    customPlayerUi: View,
    private val youTubePlayer: YouTubePlayer,
    private val youTubePlayerView: YouTubePlayerView
) : AbstractYouTubePlayerListener() {

    private val playerTracker: YouTubePlayerTracker = YouTubePlayerTracker()
    private var panel: View = customPlayerUi.findViewById(R.id.panel)
    private var progressbar: View = customPlayerUi.findViewById(R.id.progressbar)
    private var videoCurrentTimeTextView: TextView = customPlayerUi.findViewById(R.id.video_current_time)
    private var videoDurationTextView: TextView = customPlayerUi.findViewById(R.id.video_duration)
    private var fullscreen: Boolean = false

    init {
        youTubePlayer.addListener(playerTracker)
        initViews(customPlayerUi)
    }

    private fun initViews(playerUi: View) {
        val playPauseButton: Button = playerUi.findViewById(R.id.play_pause_button)
        val enterExitFullscreenButton: Button = playerUi.findViewById(R.id.enter_exit_fullscreen_button)

        playPauseButton.setOnClickListener {
            if (playerTracker.state == PlayerConstants.PlayerState.PLAYING) {
                youTubePlayer.pause()
            } else {
                youTubePlayer.play()
            }
        }

        enterExitFullscreenButton.setOnClickListener {
            if (fullscreen) {
                youTubePlayerView.wrapContent()
            } else {
                youTubePlayerView.matchParent()
            }
            fullscreen = !fullscreen
        }
    }

    override fun onReady(youTubePlayer: YouTubePlayer) {
        progressbar.visibility = View.GONE
    }

    override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
        when (state) {
            PlayerConstants.PlayerState.PLAYING,
            PlayerConstants.PlayerState.PAUSED,
            PlayerConstants.PlayerState.VIDEO_CUED -> {
                panel.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            }
            PlayerConstants.PlayerState.BUFFERING -> {
                panel.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            }

            PlayerConstants.PlayerState.UNKNOWN -> {

            }
            PlayerConstants.PlayerState.UNSTARTED ->{

            }
            PlayerConstants.PlayerState.ENDED -> {

            }
        }
    }

    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
        videoCurrentTimeTextView.text = second.toString()
    }

    override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
        videoDurationTextView.text = duration.toString()
    }
}