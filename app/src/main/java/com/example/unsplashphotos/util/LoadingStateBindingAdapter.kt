package com.example.unsplashphotos.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.example.unsplashphotos.repository.UnsplashPhotoRepository

object LoadingStateBindingAdapter {
    @JvmStatic
    @BindingAdapter("android:visibility")
    fun bindLoadingErrorState(view: TextView, state: LiveData<UnsplashPhotoRepository.LoadingState>){
        state.value?.let {
            when(it){
                UnsplashPhotoRepository.LoadingState.SUCCESS ->view.visibility = View.GONE
                UnsplashPhotoRepository.LoadingState.LOADING -> view.visibility = View.GONE
                UnsplashPhotoRepository.LoadingState.ERROR -> view.visibility = View.VISIBLE
            }
        }
    }
}