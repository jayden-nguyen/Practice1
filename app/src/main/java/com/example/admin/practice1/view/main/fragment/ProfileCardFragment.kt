package com.example.admin.practice1.view.main.fragment

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.example.admin.practice1.R
import com.example.admin.practice1.base.BaseActivity
import com.example.admin.practice1.base.BaseFragment
import com.example.admin.practice1.data.response.PostItem
import com.example.admin.practice1.mvpview.main.ProfileView
import com.example.admin.practice1.presenter.main.ProfilePresenter
import com.example.admin.practice1.view.main.adapter.ProfileAdapter
import kotlinx.android.synthetic.main.fragment_profile_card.*
import kotlinx.android.synthetic.main.fragment_profile_grid.*
import javax.inject.Inject

class ProfileCardFragment: BaseFragment(), ProfileView {
    @Inject
    lateinit var mPresenter: ProfilePresenter

    private var mAdapter = ProfileAdapter()
    override fun initData() {
        mPresenter.getProfileImage()
    }

    override fun initView() {
        rcvProfileCard.apply {
            mAdapter.setType(2)
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    override fun getViewId(): Int {
        return R.layout.fragment_profile_card
    }

    override fun injectInjector() {
        (activity as BaseActivity).getInjector().inject(this)
        mPresenter.attachView(this)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileCardFragment()
    }

    override fun renderProfileImage(list: ArrayList<PostItem>?) {
        mAdapter.addDataList(list)
    }

    override fun showLoading() {
        (activity as BaseActivity).showLoadingView()
    }

    override fun hideLoading() {
        (activity as BaseActivity).hideLoadingView()
    }

    override fun showError() {

    }

}