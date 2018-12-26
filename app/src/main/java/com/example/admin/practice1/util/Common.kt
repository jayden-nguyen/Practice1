package com.example.admin.practice1.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun loadImageFromUrl(context: Context, url: String, imageView: ImageView) {
    Glide.with(context).load(url).apply(RequestOptions().override(800,1000)).into(imageView)
}