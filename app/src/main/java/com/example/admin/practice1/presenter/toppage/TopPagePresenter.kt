package com.example.admin.practice1.presenter.toppage

import android.util.Log
import com.example.admin.practice1.base.BaseObserver
import com.example.admin.practice1.base.BasePresenter
import com.example.admin.practice1.data.ApiService
import com.example.admin.practice1.data.DataManager
import com.example.admin.practice1.data.response.PostsResponse
import com.example.admin.practice1.mvpview.toppage.TopPageView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopPagePresenter @Inject constructor(private val mDataManager: DataManager): BasePresenter<TopPageView>() {
    fun getPost() {
       mDataManager.getPost().subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(object : BaseObserver<PostsResponse, TopPageView>(getView()){
            override fun onSubscribe(d: Disposable) {
                addToCompositDiposable(d)
            }

            override fun onNext(t: PostsResponse) {
                getView().renderTestPost(t)
            }

            override fun onError(e: Throwable) {
                Log.d("ErrorGetpost", e.printStackTrace().toString())
            }
        })
    }

    fun login() {

    }

    fun register() {

    }
}