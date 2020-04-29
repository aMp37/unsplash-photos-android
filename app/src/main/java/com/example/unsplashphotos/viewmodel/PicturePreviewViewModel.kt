package com.example.unsplashphotos.viewmodel

import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.unsplashphotos.model.UnsplashPhoto
import com.example.unsplashphotos.util.SingleLiveEvent
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PicturePreviewViewModel : ViewModel() {

    sealed class UiCommand {
        data class ShowPictureDetails(val photo: UnsplashPhoto): UiCommand()
    }

    sealed class NavigationCommand {
        data class To(val directions: NavDirections): NavigationCommand()
        object Back: NavigationCommand()
        data class BackTo(val destinationId: Int): NavigationCommand()
        object ToRoot: NavigationCommand()
    }

    val clickCommand = SingleLiveEvent<UiCommand>()

    private val mCallbacks = mutableMapOf<Int,Runnable>()
    private val mButtons = mutableMapOf<Int,FloatingActionButton>()

    fun onInfoButtonClick(photo: UnsplashPhoto) = clickCommand.postValue(
        UiCommand.ShowPictureDetails(photo)
    )

    fun showButtons(vararg buttons: FloatingActionButton){


        for(btn in buttons){
            mButtons[btn.id] = btn
            btn.removeCallbacks(mCallbacks[btn.id])
            btn.visibility = View.VISIBLE
            mCallbacks[btn.id] = Runnable { btn.visibility = View.GONE }
            btn.postDelayed(mCallbacks[btn.id],2000L)
        }
    }

    override fun onCleared() {
        super.onCleared()

        for(btn in mButtons){
            btn.value.removeCallbacks(mCallbacks[btn.value.id])
        }
    }
}
