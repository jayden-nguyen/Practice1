package com.example.admin.practice1.di.components

import android.app.Application
import com.example.admin.practice1.di.modules.ApplicationModule
import com.example.admin.practice1.di.modules.PracticeModule
import com.example.admin.practice1.view.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent{
    fun getActivityComponent(practiceModule: PracticeModule): ActivityComponent
}