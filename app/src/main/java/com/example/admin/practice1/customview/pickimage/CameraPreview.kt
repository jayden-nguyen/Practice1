package com.example.admin.practice1.customview.pickimage

import android.content.Context
import android.hardware.Camera
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.io.IOException

class CameraPreview(context: Context, private var mCamera: Camera): SurfaceView(context), SurfaceHolder.Callback {
    private val TAG = CameraPreview::class.simpleName

    private val mHolder: SurfaceHolder = holder.apply {
        addCallback(this@CameraPreview)
        setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
    }
    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {

    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {

    }

    override fun surfaceCreated(p0: SurfaceHolder?) {
        try {
            //Create
            mCamera.setPreviewDisplay(holder)
            mCamera.startPreview()
        } catch (e: IOException) {
            Log.d(TAG, "Error ${e.message}")
        }
    }

    fun refreshCamera(camera: Camera) {
        if (mHolder.surface == null) {
            return
        }

        //stop Preview
        try {
            mCamera.stopPreview()
        }catch (e: Exception) {

        }

        setCamera(camera)
        try {
            //Create
            mCamera.setPreviewDisplay(holder)
            mCamera.startPreview()
        } catch (e: IOException) {
            Log.d(TAG, "Error ${e.message}")
        }

    }

    private fun setCamera(camera: Camera) {
        mCamera = camera
    }

}