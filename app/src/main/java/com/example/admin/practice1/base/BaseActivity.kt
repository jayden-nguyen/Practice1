package com.example.admin.practice1.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.afollestad.materialdialogs.MaterialDialog
import com.example.admin.practice1.App
import com.example.admin.practice1.R
import com.example.admin.practice1.di.components.ActivityComponent
import com.example.admin.practice1.di.modules.PracticeModule

abstract class BaseActivity: AppCompatActivity() {
    private lateinit var mActivityComponent: ActivityComponent
    private lateinit var mMaterialDialog: MaterialDialog
    private lateinit var mProgressDialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getViewId())
        initInjector()
        injectInjector()
        initData()
        setUpProgressDialog()
        initView()
    }

    open fun injectInjector() {

    }

    protected abstract fun getViewId(): Int
    abstract fun initData()
    abstract fun initView()

    fun showLoadingView(){
        mProgressDialog.show()
    }

    fun hideLoadingView(){
        mProgressDialog.dismiss()
    }

    private fun setUpProgressDialog() {
        mProgressDialog = Dialog(this)
        mProgressDialog.setContentView(R.layout.loading_layout)
        mProgressDialog.window.apply {
            setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
            clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    private fun initInjector() {
        mActivityComponent = (this.application as App).getApplicationComponent()!!.getActivityComponent(PracticeModule(this))
    }

    fun getInjector(): ActivityComponent {
        return mActivityComponent
    }
}