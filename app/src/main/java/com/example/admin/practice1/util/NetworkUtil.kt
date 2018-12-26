package com.example.admin.practice1.util

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkUtil @Inject constructor(private val context: Context) {
    fun isNetworkConnected(): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}