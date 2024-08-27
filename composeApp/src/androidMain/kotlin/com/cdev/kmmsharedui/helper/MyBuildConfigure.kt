package com.cdev.kmmsharedui.helper

import com.cdev.kmmsharedui.BuildConfig

class MyBuildConfigure {

    fun getBaseUrl(): String {
        return when (BuildConfig.FLAVOR) {
            FLAVOR_DEV -> BuildConfig.BASE_URL_DEV

            FLAVOR_PROD -> {
                if (BuildConfig.DEBUG) {
                    BuildConfig.BASE_URL_DEV
                }  else {
                    BuildConfig.BASE_URL_PROD
                }
            }

            else -> BuildConfig.BASE_URL_DEV
        }
    }

    companion object {
        const val FLAVOR_DEV = "dev"
        const val FLAVOR_PROD = "prod"
    }
}