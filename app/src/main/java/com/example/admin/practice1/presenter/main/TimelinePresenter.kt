package com.example.admin.practice1.presenter.main

import com.example.admin.practice1.base.BaseObserver
import com.example.admin.practice1.base.BasePresenter
import com.example.admin.practice1.data.ApiService
import com.example.admin.practice1.data.DataManager
import com.example.admin.practice1.data.response.PostsResponse
import com.example.admin.practice1.mvpview.main.TimelineView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.sql.Time
import javax.inject.Inject

class TimelinePresenter @Inject constructor(private val mDataManager: DataManager): BasePresenter<TimelineView>() {
    fun getTimelineList(){
        getView().showLoading()
        mDataManager.getTimelineList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<PostsResponse, TimelineView>(getView()){
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: PostsResponse) {
                        getView().renderTimelineList(t.data)
                    }

                    override fun onError(e: Throwable) {

                    }
                })

    }
}