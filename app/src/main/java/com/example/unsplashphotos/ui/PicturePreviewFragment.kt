package com.example.unsplashphotos.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.unsplashphotos.viewmodel.PicturePreviewViewModel
import com.example.unsplashphotos.R
import com.example.unsplashphotos.databinding.PicturePreviewFragmentBinding


class PicturePreviewFragment : Fragment() {

    companion object {
        fun newInstance() =
            PicturePreviewFragment()
    }

    private lateinit var viewModel: PicturePreviewViewModel

    private lateinit var binding: PicturePreviewFragmentBinding

    private lateinit var mInfoBottomSheetDialogFragment: ImageInfoBottomSheetDialogFragment

    val args: PicturePreviewFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.picture_preview_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PicturePreviewViewModel::class.java)

        binding.image = args.Photo
        binding.viewModel = viewModel

        viewModel.clickCommand.observe(this, Observer {
            when(it){
                is PicturePreviewViewModel.UiCommand.ShowPictureDetails -> {
                    mInfoBottomSheetDialogFragment = ImageInfoBottomSheetDialogFragment(it.photo)
                    mInfoBottomSheetDialogFragment.show(childFragmentManager,"INFO")
                }
            }
        })

    }
}
