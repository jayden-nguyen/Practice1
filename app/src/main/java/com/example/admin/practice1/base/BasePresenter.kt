package com.example.admin.practice1.base

import android.view.View
import com.example.admin.practice1.util.NetworkUtil
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BasePresenter<V: BaseView> {
    private var mView: BaseView? = null
    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var mNetworkUtil: NetworkUtil

    fun attachView(V: BaseView) {
        mView = V
    }

    fun detachView(){
        mView = null
        mCompositeDisposable.clear()
    }

    fun getView(): V = mView as V

    fun addToCompositDiposable(d: Disposable) {
        mCompositeDisposable.add(d)
    }

    fun checkNetworkConnection(): Boolean {
        return mNetworkUtil.isNetworkConnected()
    }
}