package com.example.admin.practice1.mvpview.postimage

import android.net.Uri
import com.example.admin.practice1.base.BaseView

interface PickImageView: BaseView {
    fun renderPhoto(photoPathList: ArrayList<String>)
}