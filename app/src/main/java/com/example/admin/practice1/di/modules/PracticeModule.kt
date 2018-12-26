package com.example.admin.practice1.di.modules

import android.app.Activity
import com.example.admin.practice1.base.BaseView
import com.example.admin.practice1.di.PerActivity
import dagger.Module
import dagger.Provides

@Module
class PracticeModule(private val mActivity: Activity) {

    @PerActivity
    @Provides
    fun provideView(): Activity {
        return mActivity
    }

}