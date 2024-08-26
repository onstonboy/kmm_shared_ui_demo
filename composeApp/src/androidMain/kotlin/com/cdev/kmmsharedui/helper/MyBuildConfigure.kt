package com.cdev.kmmsharedui.helper

import com.cdev.kmmsharedui.helper.resource.AppConstant
import com.cdev.kmmsharedui.BuildConfig

class MyBuildConfigure {

    fun getBaseUrl(): String {
        return when (BuildConfig.FLAVOR) {
            "dev" -> {
                AppConstant.BASE_URL_DEV
            }

            "prod" -> {
                if (BuildConfig.DEBUG) {
                    AppConstant.BASE_URL_DEV
                }  else {
                    AppConstant.BASE_URL_PROD
                }
            }

            else -> AppConstant.BASE_URL_DEV
        }
    }
}