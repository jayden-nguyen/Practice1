package com.example.admin.practice1.presenter.postimage

import com.example.admin.practice1.base.BaseObserver
import com.example.admin.practice1.base.BasePresenter
import com.example.admin.practice1.data.DataManager
import com.example.admin.practice1.data.response.pickimage.PhotoInfoResponse
import com.example.admin.practice1.mvpview.postimage.PickImageView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PickImagePresenter @Inject constructor(private val mDataManager: DataManager): BasePresenter<PickImageView>(){
    fun getImageFromGallery(){
        mDataManager.getImagesFromGallery().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<PhotoInfoResponse, PickImageView>(getView()){
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: PhotoInfoResponse) {
                        getView().renderPhoto(t.photoPaths)
                    }

                    override fun onError(e: Throwable) {

                    }

                })
    }
}