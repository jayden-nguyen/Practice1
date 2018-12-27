package com.example.admin.practice1.view.postimage.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Camera
import android.media.MediaScannerConnection
import android.os.Environment
import android.view.WindowManager
import com.example.admin.practice1.R
import com.example.admin.practice1.base.BaseFragment
import com.example.admin.practice1.customview.pickimage.CameraPreview
import kotlinx.android.synthetic.main.fragment_take_image.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class TakeImageFragment: BaseFragment(){
    private var mCamera = Camera.open()
    lateinit var mCameraPreview: CameraPreview
    lateinit var mPictureCallback : Camera.PictureCallback
    private var cameraFront = false
    override fun initData() {

    }

    override fun initView() {
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        if (mCamera != null) {
            mCamera.setDisplayOrientation(90)
            mCameraPreview = context?.let { CameraPreview(it, mCamera) }!!
        }

        mPictureCallback = getPictureCallback()

        layoutPreviewCamera.addView(mCameraPreview)

        //Take picture
        btnTakePicture.setOnClickListener {
            mCamera.takePicture(null, null, mPictureCallback)
        }

        //switch camera
        btnReverseCamera.setOnClickListener {
            val cameraNumber = Camera.getNumberOfCameras()
            if (cameraNumber > 1) {
                releaseCamera()
                chooseCamera()
            }
        }

        mCamera.startPreview()
    }

    private fun chooseCamera() {
        if (cameraFront) {
            val cameraId = findBackCamera()
            if (cameraId >= 0) {
                mCamera = Camera.open(cameraId)
                mCamera.setDisplayOrientation(90)
                mPictureCallback = getPictureCallback()
                mCameraPreview.refreshCamera(mCamera)
            }
        } else {
            val cameraId = findFrontCamera()
            if (cameraId >= 0) {
                mCamera = Camera.open(cameraId)
                mCamera.setDisplayOrientation(90)
                mPictureCallback = getPictureCallback()
                mCameraPreview.refreshCamera(mCamera)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        releaseCamera()
    }

    override fun onResume() {
        super.onResume()
        if (mCamera == null) {
            mCamera = Camera.open()
            mCamera.setDisplayOrientation(90)
            mPictureCallback = getPictureCallback()
            mCameraPreview.refreshCamera(mCamera)
        }
    }

    private fun findBackCamera(): Int {
        var cameraId = -1
        val numberOfCamera = Camera.getNumberOfCameras()
        for (i in 0 until numberOfCamera) {
            val info = Camera.CameraInfo()
            Camera.getCameraInfo(i, info)
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i
                cameraFront = false
                break
            }
        }

        return cameraId
    }

    private fun findFrontCamera(): Int {
        var cameraId = -1
        val numberOfCamera = Camera.getNumberOfCameras()
        for (i in 0 until numberOfCamera) {
            val info = Camera.CameraInfo()
            Camera.getCameraInfo(i, info)
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i
                cameraFront = true
                break
            }
        }

        return cameraId
    }

    override fun getViewId(): Int {
       return R.layout.fragment_take_image
    }

    override fun injectInjector() {

    }

    companion object {
        @JvmStatic
        fun newInstance() = TakeImageFragment()
    }

    private fun getPictureCallback(): Camera.PictureCallback {
        val picture = object :Camera.PictureCallback{
            override fun onPictureTaken(p0: ByteArray?, p1: Camera?) {
                if (p0 != null) {
                   //Get Picture call back
                    val thread = Thread{
                        val bitmap = BitmapFactory.decodeByteArray(p0, 0, p0.size)
                        val byteArrayOutputStream = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream)
                        val dir = File("${Environment.getExternalStorageDirectory()}/Practice1")
                        if (!dir.exists()) {
                            dir.mkdirs()
                        }

                        try {
                            val file = File(dir,"${Calendar.getInstance().timeInMillis}.jpg")
                            file.createNewFile()
                            val fileOutputStream = FileOutputStream(file)
                            fileOutputStream.write(byteArrayOutputStream.toByteArray())
                            MediaScannerConnection.scanFile(context,Array<String>(1){file.path},Array<String>(1){"image/jpeg"}, null)
                            fileOutputStream.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }.start()
                    releaseCamera()
                    mCamera = Camera.open()
                    mCamera.setDisplayOrientation(90)
                    mPictureCallback = getPictureCallback()
                    mCameraPreview.refreshCamera(mCamera)
                }
            }
        }

        return picture
    }

    private fun releaseCamera() {
        if (mCamera != null) {
            mCamera?.stopPreview()
            mCameraPreview.holder.removeCallback(mCameraPreview)
            mCamera.release()
            mCamera = null
        }
    }

    fun saveImage(byteArray: ByteArray) {
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream)
        val dir = File("${Environment.getExternalStorageDirectory()}/Practice1")
        if (!dir.exists()) {
            dir.mkdirs()
        }

        try {
            val file = File(dir,"${Calendar.getInstance().timeInMillis}.jpg")
            file.createNewFile()
            val fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(byteArrayOutputStream.toByteArray())
            MediaScannerConnection.scanFile(context,Array<String>(1){file.path},Array<String>(1){"image/jpeg"}, null)
            fileOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}