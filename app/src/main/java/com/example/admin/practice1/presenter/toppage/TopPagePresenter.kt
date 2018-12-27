package com.example.admin.practice1.presenter.toppage

import android.util.Log
import com.example.admin.practice1.base.BaseObserver
import com.example.admin.practice1.base.BasePresenter
import com.example.admin.practice1.data.ApiService
import com.example.admin.practice1.data.DataManager
import com.example.admin.practice1.data.request.RegisterUserRequest
import com.example.admin.practice1.data.response.PostsResponse
import com.example.admin.practice1.data.response.toppage.RegisterUserResponse
import com.example.admin.practice1.mvpview.toppage.TopPageView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.net.HttpURLConnection
import javax.inject.Inject

class TopPagePresenter @Inject constructor(private val mDataManager: DataManager): BasePresenter<TopPageView>() {

    fun login() {

    }

    fun register(registerUserRequest: RegisterUserRequest) {
        mDataManager.register(registerUserRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<RegisterUserResponse, TopPageView>(getView()){
                    override fun onSubscribe(d: Disposable) {
                        addToCompositDiposable(d)
                    }

                    override fun onNext(t: RegisterUserResponse) {
                       if (t.status == 200) {
                           getView().renderRegisterResult(true)
                       }
                    }

                    override fun onError(e: Throwable) {

                    }

                })
    }
}