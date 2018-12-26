package com.example.admin.practice1.di.modules

import android.content.Context
import android.os.Build.VERSION_CODES.O
import android.text.format.Time
import com.example.admin.practice1.base.BaseView
import com.example.admin.practice1.data.ApiService
import com.example.admin.practice1.di.PerActivity
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule(private val mContext: Context) {
    private var BASE_URL = "http://flinkgo.com:8080"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
                .addInterceptor {
                    val request = it.request().newBuilder().addHeader("Content-Type","application/json").build()
                    it.proceed(request)
                }
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()
    }

    @Singleton
    @Provides
    fun provideTestRetrofit(client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    }

    @Singleton
    @Provides
    fun provideContext(): Context {
        return mContext
    }


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}