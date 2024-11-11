package com.youtubeplaylist.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.youtubeplaylist.R
import com.youtubeplaylist.databinding.ActivityMainBinding
import com.youtubeplaylist.ui.adapter.VideoAdapter
import com.youtubeplaylist.viewmodel.YouTubeViewModel
//https://github.com/Coding-Meet/Play-Youtube-Video
class MainActivity : AppCompatActivity() {
    private val youTubeViewModel: YouTubeViewModel by viewModels()
    private lateinit var videoAdapter: VideoAdapter

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        videoAdapter = VideoAdapter { videoId ->
            // On item click, open the VideoPlayerActivity
            val intent = Intent(this, VideoPlayerActivity::class.java)
            intent.putExtra("VIDEO_ID", videoId) // Pass the video ID to the activity
            startActivity(intent)
        }
        binding.recyclerView.adapter = videoAdapter

        // Observing videos LiveData
        youTubeViewModel.videos.observe(this) { videos ->
            videos?.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "No Records Found", Toast.LENGTH_SHORT).show()
                } else {
                    videoAdapter.submitList(it)
                }

            }
        }

        // Observing error LiveData
        youTubeViewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }

        // Fetch videos
        youTubeViewModel.fetchVideos()
    }
}