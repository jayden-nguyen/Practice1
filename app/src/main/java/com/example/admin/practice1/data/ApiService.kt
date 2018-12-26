package com.example.admin.practice1.data

import com.example.admin.practice1.data.response.PostsResponse
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET

interface ApiService {
    fun login()
    fun register()

    @GET("posts/1")
    fun getPosts(): Observable<Result<PostsResponse>>
}