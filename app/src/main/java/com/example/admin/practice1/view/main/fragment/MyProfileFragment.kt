package com.example.admin.practice1.view.main.fragment

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import com.example.admin.practice1.R
import com.example.admin.practice1.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_my_profile.*

class MyProfileFragment:BaseFragment() {
    override fun initView() {
        initTabLayout()
    }

    private fun initTabLayout() {
        profileTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    setCurrentTab(tab.position)
                }
            }
        })

        profileTabs.getTabAt(0)?.select()

        profileTabs.addTab(profileTabs.newTab().setCustomView(R.layout.layout_grid_tab))
        profileTabs.addTab(profileTabs.newTab().setCustomView(R.layout.layout_card_tab))
    }

    private fun setCurrentTab(position: Int) {
        when (position) {
            0 -> openProfileGridFragment()
            1 -> openProfileCardFragment()
        }
    }

    private fun openProfileCardFragment() {
        var profileCardFragment = childFragmentManager.findFragmentByTag(ProfileCardFragment::class.simpleName)
        if (profileCardFragment == null) {
            profileCardFragment = ProfileCardFragment.newInstance()
        }

        replaceFragment(profileCardFragment, ProfileCardFragment::class.simpleName)
    }

    private fun openProfileGridFragment() {
        var profileGridFragment = childFragmentManager.findFragmentByTag(ProfileGridFragment::class.simpleName)
        if (profileGridFragment == null) {
            profileGridFragment = ProfileGridFragment.newInstance()
        }

        replaceFragment(profileGridFragment, ProfileGridFragment::class.simpleName)
    }

    override fun getViewId(): Int {
        return R.layout.fragment_my_profile
    }

    override fun injectInjector() {
    }

    override fun initData() {

    }

    fun replaceFragment(fragment: Fragment, tag: String?) {
        val fragmentInStack = childFragmentManager.findFragmentByTag(tag)
        if (fragmentInStack != null) {
            childFragmentManager.beginTransaction().replace(R.id.myProfileContainer, fragmentInStack).commit()
        } else {
            childFragmentManager.beginTransaction().replace(R.id.myProfileContainer, fragment).addToBackStack(tag).commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MyProfileFragment()
    }

}