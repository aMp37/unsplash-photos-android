package com.example.unsplashphotos.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.unsplashphotos.model.UnsplashPhoto

interface UnsplashPhotoRepository {

    enum class LoadingState{
        LOADING,
        ERROR,
        SUCCESS
    }

    suspend fun getPhotosPage(page: Int): List<UnsplashPhoto>
    fun getPhotosPagedList():LiveData<PagedList<UnsplashPhoto>>
    fun getPhotosPagedListLoadingState():LiveData<LoadingState>
}