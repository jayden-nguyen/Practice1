package com.example.admin.practice1.presenter.main

import com.example.admin.practice1.base.BaseObserver
import com.example.admin.practice1.base.BasePresenter
import com.example.admin.practice1.data.DataManager
import com.example.admin.practice1.data.response.PostsResponse
import com.example.admin.practice1.mvpview.main.ProfileView
import com.example.admin.practice1.mvpview.main.TimelineView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfilePresenter @Inject constructor(private val mDataManager: DataManager): BasePresenter<ProfileView>() {
    fun getProfileImage(){
        getView().showLoading()
        mDataManager.getTimelineList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<PostsResponse, ProfileView>(getView()){
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: PostsResponse) {
                        getView().renderProfileImage(t.data)
                    }

                    override fun onError(e: Throwable) {

                    }
                })

    }


}