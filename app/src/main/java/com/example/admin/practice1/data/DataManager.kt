package com.example.admin.practice1.data

import android.content.Context
import android.database.MergeCursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import com.example.admin.practice1.data.request.RegisterUserRequest
import com.example.admin.practice1.data.response.PostItem
import com.example.admin.practice1.data.response.PostsResponse
import com.example.admin.practice1.data.response.pickimage.PhotoInfoResponse
import com.example.admin.practice1.data.response.toppage.RegisterUserResponse
import io.reactivex.Observable
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(private val mApiService: ApiService, private var mContext: Context) {

    fun getTimelineList(): Observable<PostsResponse> {
        val mDataList = ArrayList<PostItem>()
        val imageUrl = "http://static.squarespace.com/static/51f18fe4e4b0c02c75f9847e/543af740e4b085a32c9ef571/543af740e4b085a32c9ef6bc/1375169000000/colerise-wallpaper-1.jpg?format=original"
        for (i in 0..10){
            mDataList.add(PostItem(i, i+10,"Jayden Nguyen","This is title", "Welcome to my Post",imageUrl,10,10))
        }
        val response = PostsResponse(mDataList)
        return Observable.just(response)
    }

    fun getImagesFromGallery(): Observable<PhotoInfoResponse> {
        var path = ""
        val listPhotoPath = ArrayList<String>()
        val uriExternal = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val uriInternal = MediaStore.Images.Media.INTERNAL_CONTENT_URI
        var projection = arrayOf(MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DATE_MODIFIED)
        var cursorExternal = mContext.contentResolver.query(uriExternal, projection,null, null, MediaStore.MediaColumns.DATE_MODIFIED + " DESC")
        var cursorInternal = mContext.contentResolver.query(uriInternal, projection, null, null, MediaStore.MediaColumns.DATE_MODIFIED + " DESC")
        var cursor = MergeCursor(arrayOf(cursorExternal,cursorInternal))
        while(cursor.moveToNext()) {
            path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA))
            listPhotoPath.add(path)
        }
        return Observable.just(PhotoInfoResponse(listPhotoPath))
    }

    fun register(registerUserRequest: RegisterUserRequest):Observable<RegisterUserResponse>{
        return mApiService.register(registerUserRequest).map {
            var body: RegisterUserResponse? = null
            Log.d("Response", it.response().toString())
            if (it.response()?.isSuccessful!!) {
                body = it.response()?.body()
            } else {
                body = RegisterUserResponse(null )
            }
            body!!
        }
    }
}