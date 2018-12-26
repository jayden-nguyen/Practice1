package com.example.admin.practice1.di.components

import com.example.admin.practice1.di.PerActivity
import com.example.admin.practice1.di.modules.ApplicationModule
import com.example.admin.practice1.di.modules.PracticeModule
import com.example.admin.practice1.view.main.fragment.ProfileCardFragment
import com.example.admin.practice1.view.main.fragment.ProfileGridFragment
import com.example.admin.practice1.view.main.fragment.TimelineFragment
import com.example.admin.practice1.view.postimage.fragment.PickImageFragment
import com.example.admin.practice1.view.toppage.TopPageActivity
import dagger.Subcomponent
import javax.inject.Singleton

@PerActivity
@Subcomponent(modules = [PracticeModule::class])
interface ActivityComponent {

    fun inject(topPageActivity: TopPageActivity)

    fun inject(timelineFragment: TimelineFragment)

    fun inject(profileGridFragment: ProfileGridFragment)

    fun inject(profileCardFragment: ProfileCardFragment)

    fun inject(pickImageFragment: PickImageFragment)
}