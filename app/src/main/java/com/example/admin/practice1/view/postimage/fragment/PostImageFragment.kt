package com.example.admin.practice1.view.postimage.fragment

import com.example.admin.practice1.R
import com.example.admin.practice1.base.BaseFragment
import com.example.admin.practice1.view.postimage.adapter.PostImagePagerAdapter
import kotlinx.android.synthetic.main.fragment_image_post.*
import kotlinx.android.synthetic.main.fragment_my_profile.*

class PostImageFragment: BaseFragment() {
    private lateinit var mAdapter: PostImagePagerAdapter
    override fun initData() {
        mAdapter = PostImagePagerAdapter(childFragmentManager)
        mAdapter.addFragment(PickImageFragment.newInstance(), "Pick Image")
        mAdapter.addFragment(TakeImageFragment.newInstance(), "Take Picture")
    }

    override fun initView() {
        setupViewPager()
        postImageTabs.setupWithViewPager(postImagePager)
    }

    private fun setupViewPager() {
        postImagePager.adapter = mAdapter
    }

    override fun getViewId(): Int {
        return R.layout.fragment_image_post
    }

    override fun injectInjector() {

    }

    companion object {
        @JvmStatic
        fun newInstance() = PostImageFragment()
    }

}