package com.example.unsplashphotos.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.media.Image
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.unsplashphotos.R

object ImageBindingAdapter{

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(view: ImageView, imageUrl: String){

        Glide.with(view.context)
            .asBitmap()
            .apply(RequestOptions().placeholder(CircularProgressDrawable(view.context).apply {
                strokeWidth=10f
                centerRadius = 50f
                start()
            }

            ))
            .load(imageUrl)
            .into(view)
    }
}
