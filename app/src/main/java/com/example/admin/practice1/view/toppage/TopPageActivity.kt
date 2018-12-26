package com.example.admin.practice1.view.toppage

import android.widget.Toast
import com.example.admin.practice1.R
import com.example.admin.practice1.base.BaseActivity
import com.example.admin.practice1.data.response.PostsResponse
import com.example.admin.practice1.mvpview.toppage.TopPageView
import com.example.admin.practice1.presenter.toppage.TopPagePresenter
import kotlinx.android.synthetic.main.activity_top_page.*
import javax.inject.Inject

class TopPageActivity: BaseActivity(), TopPageView {
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
        btnTestApi.setOnClickListener {
            mPresenter.getPost()
        }
    }

    override fun renderTestPost(data: PostsResponse) {
//        Toast.makeText(this, "Success\n" + data.toString(), Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showError() {

    }


}