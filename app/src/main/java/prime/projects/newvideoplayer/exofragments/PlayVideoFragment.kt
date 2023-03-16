package prime.projects.newvideoplayer.exofragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import prime.projects.newvideoplayer.R
import prime.projects.newvideoplayer.databinding.FragmentPlayVideoBinding
import prime.projects.newvideoplayer.model.ExoVideoModel


class PlayVideoFragment : Fragment() {


    private var binding: FragmentPlayVideoBinding ?= null

    private val sharedViewModel: ExoVideoModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val playVideoBinding = FragmentPlayVideoBinding.inflate(inflater, container, false)
        binding = playVideoBinding
        return playVideoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            playVideoFragment = this@PlayVideoFragment
        }
    }

    private fun createMessage(){}

    fun launchToVideo(){
        findNavController().navigate(R.id.action_playVideoFragment_to_exoPlayerFragment)
        val short = Toast.LENGTH_SHORT
        val fragThis = requireActivity()
        sharedViewModel.makingToasts(fragThis, "Video has been launched", short)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}