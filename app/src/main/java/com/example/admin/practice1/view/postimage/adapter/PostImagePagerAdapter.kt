package com.example.admin.practice1.view.postimage.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PostImagePagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager){
    private var mFragmentList = ArrayList<Fragment>()
    private var mFragmentTitleList = ArrayList<String>()
    override fun getItem(p0: Int): Fragment {
        return mFragmentList[p0]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
        notifyDataSetChanged()
    }

}