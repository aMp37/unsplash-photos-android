package com.example.unsplashphotos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.unsplashphotos.model.UnsplashPhoto
import com.example.unsplashphotos.util.SingleLiveEvent

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

    fun onInfoButtonClick(photo: UnsplashPhoto) = clickCommand.postValue(
        UiCommand.ShowPictureDetails(photo)
    )
}
