package com.example.unsplashphotos.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.unsplashphotos.model.UnsplashPhoto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class UnsplashPhotoDataSource: PageKeyedDataSource<Int,UnsplashPhoto>() {

    private val FIRST_PAGE = 1
    private val mPhotosRepository = UnsplashPhotoRepositoryImpl()


    private val mLoadingState = MutableLiveData<UnsplashPhotoRepository.LoadingState>()

    val loadingState: LiveData<UnsplashPhotoRepository.LoadingState>
    get() = mLoadingState

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, UnsplashPhoto>
    ) {

        CoroutineScope(IO).launch{
            mLoadingState.postValue(UnsplashPhotoRepository.LoadingState.LOADING)
            try{
                var result = mPhotosRepository.getPhotosPage(FIRST_PAGE)
                withContext(Main){
                    callback.onResult(result,null,FIRST_PAGE+1)
                    mLoadingState.postValue(UnsplashPhotoRepository.LoadingState.SUCCESS)
                }
            }catch (e: Exception){
                e.printStackTrace()
                withContext(Main){
                    mLoadingState.postValue(UnsplashPhotoRepository.LoadingState.ERROR)
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, UnsplashPhoto>) {
        mLoadingState.postValue(UnsplashPhotoRepository.LoadingState.LOADING)
        CoroutineScope(IO).launch {
            try{
                val result = mPhotosRepository.getPhotosPage(params.key)
                withContext(Main){
                    callback.onResult(result,params.key+1)
                    mLoadingState.postValue(UnsplashPhotoRepository.LoadingState.SUCCESS)
                }
            }catch (e: Exception){
                e.printStackTrace()
                withContext(Main){
                    mLoadingState.postValue(UnsplashPhotoRepository.LoadingState.ERROR)
                }
            }

        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, UnsplashPhoto>) {
    }

    class Factory(): DataSource.Factory<Int,UnsplashPhoto>() {

        val imagesLiveDataSource: MutableLiveData<UnsplashPhotoDataSource> = MutableLiveData()

        override fun create(): DataSource<Int, UnsplashPhoto> {
            return UnsplashPhotoDataSource().also {
                imagesLiveDataSource.postValue(it)
            }
        }
    }
}