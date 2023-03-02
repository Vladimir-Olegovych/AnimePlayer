package com.example.animeplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.Util
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.*
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource

class PlayerActivity : AppCompatActivity() , Player.Listener {

    private var mPlayer: SimpleExoPlayer? = null
    private lateinit var playerView: PlayerView

    private val videoURL = num1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        playerView = findViewById(R.id.playerView)

    }

    private fun initPlayer() {

        mPlayer = SimpleExoPlayer.Builder(this).build()

        playerView.player = mPlayer

        mPlayer!!.playWhenReady = true

        mPlayer!!.setMediaSource(buildMediaSource())

        mPlayer!!.prepare()

    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initPlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT < 24 || mPlayer == null) {
            initPlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }


    private fun releasePlayer() {
        if (mPlayer == null) {
            return
        }
        mPlayer!!.release()
        mPlayer = null
    }

    private fun buildMediaSource(): MediaSource {
        val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()

        val mediaSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(videoURL))

        return mediaSource
    }
}