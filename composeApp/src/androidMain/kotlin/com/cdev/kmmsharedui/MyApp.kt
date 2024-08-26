package com.cdev.kmmsharedui

import android.app.Application
import com.cdev.kmmsharedui.data.local.shared_preference.MySharedPref
import com.cdev.kmmsharedui.di.initKoin
import com.cdev.kmmsharedui.helper.MyBuildConfigure
import org.koin.dsl.module

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val tsConfig = MyBuildConfigure()
        initKoin(baseUrl = tsConfig.getBaseUrl(), enableNetworkLogs = BuildConfig.DEBUG, mySharedPref = MySharedPref(this)) {
            modules(
                listOf(module {
                    /**
                     * android specific modules
                     */
                })
            )
        }
    }
}
