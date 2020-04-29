package com.example.unsplashphotos.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.unsplashphotos.di.UnsplashPhotos
import com.example.unsplashphotos.model.UnsplashPhoto
import com.example.unsplashphotos.service.PhotosService
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class UnsplashPhotoRepositoryImpl(private val mUnsplashPhotosService: PhotosService):UnsplashPhotoRepository, KodeinAware {
    private val mACCESS_KEY = "qm7xuNFuevvyncNnehBLyRvCX4UpaijlyOJxOnIIU8E"
    private val mPER_PAGE = 20
    private var mUnsplashPhotoDataSourceFactory: UnsplashPhotoDataSource.Factory
    val mPhotosPagedList: LiveData<PagedList<UnsplashPhoto>>

    override val kodein: Kodein by instance()

    init{
        val pagedListConfig =PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(mPER_PAGE)
            .build()

        mUnsplashPhotoDataSourceFactory = UnsplashPhotoDataSource.Factory(this)

        mPhotosPagedList = LivePagedListBuilder<Int,UnsplashPhoto>(mUnsplashPhotoDataSourceFactory,pagedListConfig).build()
    }

    override suspend fun getPhotosPage(page: Int): List<UnsplashPhoto> = mUnsplashPhotosService.retrofitService.getPhotosPage(mACCESS_KEY,page)

    override fun getPhotosPagedList(): LiveData<PagedList<UnsplashPhoto>> {
      return mPhotosPagedList
    }

    override fun getPhotosPagedListLoadingState(): LiveData<UnsplashPhotoRepository.LoadingState> {
        return Transformations.switchMap(mUnsplashPhotoDataSourceFactory.imagesLiveDataSource,
            UnsplashPhotoDataSource::loadingState)
    }
}