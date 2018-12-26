package com.example.admin.practice1.view.postimage

import android.support.v4.app.Fragment
import com.example.admin.practice1.R
import com.example.admin.practice1.base.BaseActivity
import com.example.admin.practice1.view.postimage.fragment.PostImageFragment

class PostImageActivity: BaseActivity() {
    override fun getViewId(): Int {
        return R.layout.activity_post_image
    }

    override fun initData() {

    }

    override fun initView() {
        openPostImageFragment()
    }

    private fun replaceFragment(fragment: Fragment, tag: String?){
       supportFragmentManager.beginTransaction().replace(R.id.postImageContainer, fragment, tag).addToBackStack(null).commit()
    }

    private fun openPostImageFragment(){
        var postImageFragment = supportFragmentManager.findFragmentByTag(PostImageFragment::class.simpleName)
        if (postImageFragment == null) {
            postImageFragment = PostImageFragment.newInstance()
        }

        replaceFragment(postImageFragment, PostImageFragment::class.simpleName)
    }

    override fun onBackPressed() {
        finish()
    }

}