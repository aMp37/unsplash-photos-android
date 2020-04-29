package com.example.unsplashphotos.ui
import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.unsplashphotos.viewmodel.PicturePreviewViewModel
import com.example.unsplashphotos.R
import com.example.unsplashphotos.databinding.PicturePreviewFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PicturePreviewFragment : Fragment() {

    companion object {
        fun newInstance() =
            PicturePreviewFragment()
    }

    private val viewModel: PicturePreviewViewModel by viewModels()
    val args: PicturePreviewFragmentArgs by navArgs()

    private lateinit var binding: PicturePreviewFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.picture_preview_fragment,container,false)
        return binding.root
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.image = args.Photo
        binding.viewModel = viewModel

        if(args.Photo.width > args.Photo.height)
            requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        else
            requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT

        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR


        viewModel.clickCommand.observe(this, Observer {
            when(it){
                is PicturePreviewViewModel.UiCommand.ShowPictureDetails -> {
                    ImageInfoBottomSheetDialogFragment.newInstance(it.photo).show(parentFragmentManager,"INFO")
                }
            }
        })

    }

}
