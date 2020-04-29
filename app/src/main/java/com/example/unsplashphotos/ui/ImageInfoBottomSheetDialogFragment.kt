package com.example.unsplashphotos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.unsplashphotos.R
import com.example.unsplashphotos.databinding.ImageInfoBottomSheetBinding
import com.example.unsplashphotos.model.UnsplashPhoto
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ImageInfoBottomSheetDialogFragment: BottomSheetDialogFragment() {

    companion object {
        fun newInstance(photo: UnsplashPhoto) = ImageInfoBottomSheetDialogFragment().apply {
            arguments = Bundle().apply {
                putParcelable("PHOTO",photo)
            }
        }
    }

    private lateinit var mBinding: ImageInfoBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.image_info_bottom_sheet,container,false)

        mBinding.image = arguments?.getParcelable("PHOTO")

        mBinding.executePendingBindings()

        return mBinding.root
    }

}