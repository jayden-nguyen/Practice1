package com.example.admin.practice1

import android.app.Application
import com.example.admin.practice1.di.components.ApplicationComponent
import com.example.admin.practice1.di.components.DaggerApplicationComponent
import com.example.admin.practice1.di.modules.ApplicationModule

class App: Application() {
    private var mApplicationComponent: ApplicationComponent? = null
    override fun onCreate() {
        super.onCreate()
        initApplicationComponent()
    }
    private fun initApplicationComponent(){
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }

    fun getApplicationComponent(): ApplicationComponent? {
        return mApplicationComponent
    }
}