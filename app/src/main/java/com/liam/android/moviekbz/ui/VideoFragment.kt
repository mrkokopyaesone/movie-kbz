package com.liam.android.moviekbz.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.gson.Gson
import com.liam.android.moviekbz.databinding.FragmentVideoBinding
import com.liam.android.moviekbz.model.entity.MovieModel
import com.liam.android.moviekbz.ui.movies.MOVIE_DETAIL
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


private const val TAG = "VideoFragment"
class VideoFragment : Fragment() {

    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentVideoBinding.inflate(layoutInflater)
    }

    private val playbackStateListener: Player.Listener = playbackStateListener()
    private var player: ExoPlayer? = null

    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L
    private lateinit var movieKey : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieKey = it.getString("Video_key").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return viewBinding.root
    }

//    private fun initializePlayer() {
//        val trackSelector = DefaultTrackSelector().apply {
//            setParameters(buildUponParameters().setMaxVideoSizeSd())
//        }
//        player = ExoPlayer.Builder(viewBinding.root.context)
//            .setTrackSelector(trackSelector)
//            .build()
//            .also { exoPlayer ->
//               // viewBinding.pvVideoVideoFragment.player = exoPlayer
//                viewBinding.pvVideoVideoFragment.player = exoPlayer
//
//                val mediaItem = MediaItem.Builder()
//                    .setUri(getString(R.string.media_url_mp4))
//                    .setMimeType(MimeTypes.APPLICATION_MPD)
//                    .build()
//                exoPlayer.setMediaItem(mediaItem)
//                exoPlayer.playWhenReady = playWhenReady
//                exoPlayer.seekTo(currentItem, playbackPosition)
//                exoPlayer.addListener(playbackStateListener)
//                exoPlayer.prepare()
//            }
//    }
//    @SuppressLint("InlinedApi")
//    private fun hideSystemUi() {
//        activity?.let { WindowCompat.setDecorFitsSystemWindows(it.window, false) }
//        activity?.let {
//            WindowInsetsControllerCompat(it.window, viewBinding.pvVideoVideoFragment).let { controller ->
//                controller.hide(WindowInsetsCompat.Type.systemBars())
//                controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//            }
//        }
//    }
//    private fun releasePlayer() {
//        player?.let { exoPlayer ->
//            playbackPosition = exoPlayer.currentPosition
//            currentItem = exoPlayer.currentMediaItemIndex
//            playWhenReady = exoPlayer.playWhenReady
//            exoPlayer.release()
//        }
//        player = null
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val youtubeLink = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"

        viewBinding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                if (movieKey != null) {
                    youTubePlayer.loadVideo(movieKey, 0f)
                }
            }
        })
      //  val player = ExoPlayer.Builder(requireContext()).build()
    //    viewBinding.pvVideoVideoFragment.player = player
//        val mediaItem: MediaItem = MediaItem.fromUri(getString(R.string.media_url_dash))
//        player.addMediaItem(mediaItem)
//        player.prepare()
//        player.playWhenReady
        //initializePlayer()

//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                if (Util.SDK_INT > 23){
//                    initializePlayer()
//
//                }
//            }
//
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
//                hideSystemUi()
//                if (Util.SDK_INT <= 23 || player == null) {
//                    initializePlayer()
//                }
//            }
//
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.DESTROYED) {
//                releasePlayer()
//            }
//
//            }

    }
}

private fun playbackStateListener() = object : Player.Listener {
    override fun onPlaybackStateChanged(playbackState: Int) {
        val stateString: String = when (playbackState) {
            ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE      -"
            ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING -"
            ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY     -"
            ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED     -"
            else -> "UNKNOWN_STATE             -"
        }
        Log.d(TAG, "changed state to $stateString")
    }
}