package com.example.admin.practice1.view.main.fragment

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.example.admin.practice1.R
import com.example.admin.practice1.base.BaseActivity
import com.example.admin.practice1.base.BaseFragment
import com.example.admin.practice1.data.response.PostItem
import com.example.admin.practice1.mvpview.main.TimelineView
import com.example.admin.practice1.presenter.main.TimelinePresenter
import com.example.admin.practice1.view.main.adapter.TimelineAdapter
import kotlinx.android.synthetic.main.fragment_time_line.*
import javax.inject.Inject

class TimelineFragment: BaseFragment(), TimelineView {

    @Inject
    lateinit var mPresenter: TimelinePresenter

    private var mAdapter = TimelineAdapter()
    override fun initData() {
        mPresenter.getTimelineList()
    }

    override fun initView() {
        rcvTimeline.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun getViewId(): Int {
        return R.layout.fragment_time_line
    }

    override fun injectInjector() {
        (activity as BaseActivity).getInjector().inject(this)
        mPresenter.attachView(this)
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

    override fun renderTimelineList(listTimeline: ArrayList<PostItem>?) {
        if (listTimeline != null) {
            mAdapter.clearData()
            mAdapter.addDataList(listTimeline)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance()  = TimelineFragment()
    }
}