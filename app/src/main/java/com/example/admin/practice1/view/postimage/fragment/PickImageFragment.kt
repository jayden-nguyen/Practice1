package com.example.admin.practice1.view.postimage.fragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.media.ExifInterface
import android.net.Uri
import android.nfc.Tag
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImage
import com.bumptech.glide.request.RequestOptions
import com.example.admin.practice1.R
import com.example.admin.practice1.base.BaseActivity
import com.example.admin.practice1.base.BaseFragment
import com.example.admin.practice1.mvpview.postimage.PickImageView
import com.example.admin.practice1.presenter.postimage.PickImagePresenter
import com.example.admin.practice1.view.postimage.adapter.PickImageRecyclerViewAdapter
import io.togoto.imagezoomcrop.photoview.IGetImageBounds
import io.togoto.imagezoomcrop.photoview.PhotoView
import kotlinx.android.synthetic.main.fragment_pick_image.*
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

class PickImageFragment: BaseFragment(), PickImageView, PickImageRecyclerViewAdapter.OnItemClick {
    private var TAG = PickImageFragment::class.simpleName
    private var mAdapter = PickImageRecyclerViewAdapter()

    private var mImgView: View? = null

    @Inject
    lateinit var mPresenter: PickImagePresenter

    override fun initData() {
        mPresenter.getImageFromGallery()
        mAdapter.setItemClickListener(this)
    }

    override fun initView() {
        rcvPickImage.apply {
            layoutManager = GridLayoutManager(context,3)
            adapter = mAdapter
        }
    }

    override fun getViewId(): Int {
       return R.layout.fragment_pick_image
    }

    override fun injectInjector() {
        (activity as BaseActivity).getInjector().inject(this)
        mPresenter.attachView(this)
    }

    override fun renderPhoto(photoPathList: ArrayList<String>) {
        mAdapter.addPhotopathList(photoPathList)
        showImageSelected(photoPathList[0])
    }

    override fun showLoading() {
        (activity as BaseActivity).showLoadingView()
    }

    override fun hideLoading() {
        (activity as BaseActivity).hideLoadingView()
    }

    override fun showError() {

    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }

    override fun onItemClick(photoPath: String) {
        showImageSelected(photoPath)
    }

    private fun showImageSelected(photoPath: String) {
        mImgView = previewContainer.getChildAt(0)
        if (mImgView !is PhotoView) {
            previewContainer.removeAllViews()
            mImgView = PhotoView(context)
            (mImgView as PhotoView).apply {
                layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
                scaleType = ImageView.ScaleType.CENTER
                setImageBoundsListener (object : IGetImageBounds{
                    override fun getImageBounds(): Rect {
                        return cropImageView.imageBounds
                    }

                })
            }

            previewContainer.addView(mImgView)
        }
        val bitmap = getBitmap(Uri.fromFile(File(photoPath)))
        val drawable = BitmapDrawable(resources, bitmap)
        val minScale = (mImgView as PhotoView).setMinimumScaleToFit(drawable) * 2
        (mImgView as PhotoView).apply {
            maximumScale = minScale * 3
            mediumScale = minScale * 2
            scale = minScale
            setImageDrawable(drawable)
        }
    }

    private val IMAGE_MAX_SIZE: Int = 1024

    private fun getBitmap(uri: Uri): Bitmap? {
        var inputStream: InputStream? = null
        var returnedBitmap: Bitmap? = null
        try {
            inputStream = context?.contentResolver?.openInputStream(uri)
            //Decode image size
            val o = BitmapFactory.Options()
            o.inJustDecodeBounds = true
            BitmapFactory.decodeStream(inputStream, null, o)
            inputStream!!.close()
            var scale = 1
            if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
                scale = Math.pow(2.0, Math.round(Math.log(IMAGE_MAX_SIZE / Math.max(o.outHeight, o.outWidth).toDouble()) / Math.log(0.5)).toInt().toDouble()).toInt()
            }

            val o2 = BitmapFactory.Options()
            o2.inSampleSize = scale
            inputStream = context?.contentResolver?.openInputStream(uri)
            var bitmap: Bitmap? = BitmapFactory.decodeStream(inputStream, null, o2)
            inputStream!!.close()

            //First check
            val ei = ExifInterface(uri.path)
            val orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)

            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> {
                    returnedBitmap = rotateImage(bitmap!!, 90f)
                    //Free up the memory
                    bitmap.recycle()
                }
                ExifInterface.ORIENTATION_ROTATE_180 -> {
                    returnedBitmap = rotateImage(bitmap!!, 180f)
                    //Free up the memory
                    bitmap.recycle()
                }
                ExifInterface.ORIENTATION_ROTATE_270 -> {
                    returnedBitmap = rotateImage(bitmap!!, 270f)
                    //Free up the memory
                    bitmap.recycle()
                }
                else -> returnedBitmap = bitmap
            }
            return returnedBitmap
        } catch (e: FileNotFoundException) {

        } catch (e: IOException) {

        }

        return null
    }

    private fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PickImageFragment()
    }

}