package com.example.admin.practice1.base

import android.view.View
import com.example.admin.practice1.data.response.BaseResponse
import io.reactivex.Observer

abstract class BaseObserver<T: BaseResponse, V: BaseView>(val view: BaseView): Observer<T> {
    override fun onComplete() {
        view.hideLoading()
    }
}