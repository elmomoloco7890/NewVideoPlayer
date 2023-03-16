package prime.projects.newvideoplayer.exofragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.util.MimeTypes
import prime.projects.newvideoplayer.R
import prime.projects.newvideoplayer.databinding.FragmentExoPlayerBinding
import prime.projects.newvideoplayer.model.ExoVideoModel


class ExoPlayerFragment : Fragment() {

    private var binding: FragmentExoPlayerBinding?= null

    private val sharedViewModel: ExoVideoModel by activityViewModels()


    private var player: ExoPlayer ?= null
    private val isPlaying get() = player?.isPlaying?: false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val exoPlayerBinding = FragmentExoPlayerBinding.inflate(inflater, container, false)
        binding = exoPlayerBinding
        return exoPlayerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            exoPlayerFragment = this@ExoPlayerFragment
        }

        initializePlayer()

    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(requireActivity()).build()

        //create the media item
        val mediaItem = MediaItem.Builder()
            .setUri("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4")
            .setMimeType(MimeTypes.APPLICATION_MP4)
            .build()

        val mediaSource = ProgressiveMediaSource.Factory(DefaultDataSource.Factory(requireActivity()))
            .createMediaSource(mediaItem)

        player!!.apply {
            setMediaSource(mediaSource)
            playWhenReady = true
            seekTo(0, 0L)
            prepare()
        }.also {
            binding?.exoPlayerView?.player = it
        }
    }

    fun goBack(){
        findNavController().navigate(R.id.action_exoPlayerFragment_to_playVideoFragment)
        sharedViewModel.makingToasts(requireActivity(), "go home", Toast.LENGTH_SHORT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}