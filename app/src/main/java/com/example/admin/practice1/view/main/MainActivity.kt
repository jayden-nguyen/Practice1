package com.example.admin.practice1.view.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.widget.ImageView
import com.example.admin.practice1.R
import com.example.admin.practice1.base.BaseActivity
import com.example.admin.practice1.view.main.fragment.MyProfileFragment
import com.example.admin.practice1.view.main.fragment.TimelineFragment
import com.example.admin.practice1.view.postimage.PostImageActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getViewId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {

    }

    override fun initView() {
        initMainTabs()

    }

    private fun initMainTabs() {
        openTimelineFragment()
        mainTabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (tab?.position == 1) {
                    setCurrentTabSelected(tab.position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    setCurrentTabSelected(tab.position)
                    val imgIcon = tab?.customView?.findViewById<ImageView>(R.id.imgIcon)
                    when(tab.position) {
                        0 -> {
                            imgIcon?.setImageResource(R.drawable.homeicon)
                            mainTabs.getTabAt(2)?.customView?.findViewById<ImageView>(R.id.imgIcon)?.setImageResource(R.drawable.myprofileunactive)
                        }

                        2 -> {
                            imgIcon?.setImageResource(R.drawable.myprofileactive)
                            mainTabs.getTabAt(0)?.customView?.findViewById<ImageView>(R.id.imgIcon)?.setImageResource(R.drawable.homeiconunactive)
                        }
                    }
                }
            }
        })
        mainTabs.addTab(mainTabs.newTab().setCustomView(R.layout.layout_home_tab))
        mainTabs.addTab(mainTabs.newTab().setCustomView(R.layout.layout_pictures_tab))
        mainTabs.addTab(mainTabs.newTab().setCustomView(R.layout.layout_my_profile_tab))
    }

    private fun setCurrentTabSelected(position: Int) {
        when(position) {
            0 -> openTimelineFragment()
            1 -> moveToPostImage()
            2 -> openMyProfileFragment()
        }
    }

    private fun moveToPostImage() {
        startActivity(Intent(this, PostImageActivity::class.java))
    }

    private fun openTimelineFragment(){
        var timelineFragment = supportFragmentManager.findFragmentByTag(TimelineFragment::class.simpleName)
        if (timelineFragment == null) {
            timelineFragment = TimelineFragment.newInstance()
        }

        replaceFragment(timelineFragment, TimelineFragment::class.simpleName)
    }

    private fun openMyProfileFragment(){
        var myProfileFragment = supportFragmentManager.findFragmentByTag(MyProfileFragment::class.simpleName)
        if (myProfileFragment == null) {
            myProfileFragment = MyProfileFragment.newInstance()
        }

        replaceFragment(myProfileFragment, MyProfileFragment::class.simpleName)
    }

    private fun replaceFragment(fragment: Fragment, tag: String?) {
        val fragmentInStack = supportFragmentManager.findFragmentByTag(tag)
        if (fragmentInStack != null) {
            supportFragmentManager.beginTransaction().replace(R.id.mainContainer, fragmentInStack).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.mainContainer, fragment).addToBackStack(tag).commit()
        }
    }
}
