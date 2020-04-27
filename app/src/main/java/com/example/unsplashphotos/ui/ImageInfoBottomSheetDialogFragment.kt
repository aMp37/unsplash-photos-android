package com.example.unsplashphotos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.unsplashphotos.R
import com.example.unsplashphotos.databinding.ImageInfoBottomSheetBinding
import com.example.unsplashphotos.model.UnsplashPhoto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ImageInfoBottomSheetDialogFragment(private val mPhoto :UnsplashPhoto): BottomSheetDialogFragment() {

    private lateinit var mBinding: ImageInfoBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.image_info_bottom_sheet,container,false)

        mBinding.image = mPhoto
        mBinding.executePendingBindings()

        return mBinding.root
    }
}