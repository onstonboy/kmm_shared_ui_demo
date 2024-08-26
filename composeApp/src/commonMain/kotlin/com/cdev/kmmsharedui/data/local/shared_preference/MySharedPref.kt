package com.cdev.kmmsharedui.data.local.shared_preference

import com.cdev.kmmsharedui.SPref
import com.cdev.kmmsharedui.getBoolean
import com.cdev.kmmsharedui.getFloat
import com.cdev.kmmsharedui.getInt
import com.cdev.kmmsharedui.getLong
import com.cdev.kmmsharedui.getString
import com.cdev.kmmsharedui.setBoolean
import com.cdev.kmmsharedui.setFloat
import com.cdev.kmmsharedui.setInt
import com.cdev.kmmsharedui.setLong
import com.cdev.kmmsharedui.setString

class MySharedPref(private val context: SPref) {

    fun getInt(key: String): Int {
        return context.getInt(key)
    }

    fun putInt(key: String, value: Int) {
        context.setInt(key, value)
    }

    fun getString(key: String): String {
        return context.getString(key)
    }

    fun putString(key: String, value: String) {
        context.setString(key, value)
    }

    fun getBoolean(key: String): Boolean {
        return context.getBoolean(key)
    }

    fun putBoolean(key: String, value: Boolean) {
        context.setBoolean(key, value)
    }

    fun getLong(key: String): Long {
        return context.getLong(key)
    }

    fun putLong(key: String, value: Long) {
        context.setLong(key, value)
    }

    fun getFloat(key: String): Float {
        return context.getFloat(key)
    }

    fun putFloat(key: String, value: Float) {
        context.setFloat(key, value)
    }
}