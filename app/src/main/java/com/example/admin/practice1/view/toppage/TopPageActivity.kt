package com.example.admin.practice1.view.toppage

import android.content.Intent
import android.widget.Toast
import com.example.admin.practice1.R
import com.example.admin.practice1.base.BaseActivity
import com.example.admin.practice1.data.request.RegisterUserRequest
import com.example.admin.practice1.data.response.PostsResponse
import com.example.admin.practice1.mvpview.toppage.TopPageView
import com.example.admin.practice1.presenter.toppage.TopPagePresenter
import com.example.admin.practice1.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_top_page.*
import javax.inject.Inject

class TopPageActivity: BaseActivity(), TopPageView {
    override fun renderRegisterResult(success: Boolean) {
        if (success) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    @Inject
    lateinit var mPresenter: TopPagePresenter
    override fun getViewId(): Int {
        return R.layout.activity_top_page
    }

    override fun initData() {
        mPresenter.attachView(this)
    }

    override fun injectInjector() {
        getInjector().inject(this)
    }


    override fun initView() {
        btnRegister.setOnClickListener {
            mPresenter.register(RegisterUserRequest(edtUserName.text.toString(),edtPassword.text.toString(),edtGender.text.toString(),"1995-11-11",""))
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showError() {

    }


}