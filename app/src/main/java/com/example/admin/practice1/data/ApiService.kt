package com.example.admin.practice1.data

import com.example.admin.practice1.data.request.RegisterUserRequest
import com.example.admin.practice1.data.response.PostsResponse
import com.example.admin.practice1.data.response.toppage.RegisterUserResponse
import com.example.admin.practice1.util.ApiUrl
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    fun login()
    fun register()

    @POST(ApiUrl.USER_REGISTER)
    fun register(registerUserRequest: RegisterUserRequest): Observable<Result<RegisterUserResponse>>
}