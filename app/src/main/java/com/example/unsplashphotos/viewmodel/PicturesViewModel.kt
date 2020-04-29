package com.example.unsplashphotos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.navigation.NavDirections
import androidx.paging.PagedList
import com.example.unsplashphotos.model.UnsplashPhoto
import com.example.unsplashphotos.repository.UnsplashPhotoRepository
import com.example.unsplashphotos.repository.UnsplashPhotoRepositoryImpl
import com.example.unsplashphotos.ui.PicturesFragmentDirections
import com.example.unsplashphotos.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers.IO

class PicturesViewModel(private val mPhotosRepository: UnsplashPhotoRepository) : ViewModel() {
    sealed class NavigationCommand {
        data class To(val directions: NavDirections): NavigationCommand()
        object Back: NavigationCommand()
        data class BackTo(val destinationId: Int): NavigationCommand()
        object ToRoot: NavigationCommand()
    }

    val navigationCommand = SingleLiveEvent<NavigationCommand>()

    val photosList: LiveData<PagedList<UnsplashPhoto>>
    get() = mPhotosRepository.getPhotosPagedList()

    val loadingState: LiveData<UnsplashPhotoRepository.LoadingState>
    get() = mPhotosRepository.getPhotosPagedListLoadingState()

    fun onItemClick(item: UnsplashPhoto) = navigationCommand.postValue(
        NavigationCommand.To(PicturesFragmentDirections.actionPicturesFragmentToPicturePreviewFragment(item))
    )

    val mPictures = liveData(IO) {
        val retPhotos = mPhotosRepository.getPhotosPage(1)
        emit(retPhotos)
    }


    class Factory(private val mPhotosRepository: UnsplashPhotoRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = PicturesViewModel(mPhotosRepository) as T

    }
}
