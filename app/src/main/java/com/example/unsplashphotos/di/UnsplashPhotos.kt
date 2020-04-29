package com.example.unsplashphotos.di

import android.app.Application
import com.example.unsplashphotos.repository.UnsplashPhotoDataSource
import com.example.unsplashphotos.repository.UnsplashPhotoRepository
import com.example.unsplashphotos.repository.UnsplashPhotoRepositoryImpl
import com.example.unsplashphotos.service.PhotosService
import com.example.unsplashphotos.viewmodel.PicturesViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class UnsplashPhotos() : Application(), KodeinAware{
    override val kodein = Kodein.lazy{
        bind<UnsplashPhotoRepository>() with provider { UnsplashPhotoRepositoryImpl(PhotosService) }
        bind<PicturesViewModel.Factory>() with singleton { PicturesViewModel.Factory(instance()) }
    }
    override fun onCreate() {
        super.onCreate()
    }
}