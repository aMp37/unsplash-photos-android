package com.example.unsplashphotos.service

import com.example.unsplashphotos.model.UnsplashPhoto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotosApi {
    @GET("/photos/")
    suspend fun getPhotosPage(@Query("client_id") accessKey: String, @Query("page") page: Int): List<UnsplashPhoto>
}