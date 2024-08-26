package com.cdev.kmmsharedui

import platform.Foundation.NSUserDefaults
import platform.darwin.NSObject

actual typealias SPref = NSObject

actual fun SPref.getInt(key: String): Int {
    return NSUserDefaults.standardUserDefaults.integerForKey(key).toInt()
}

actual fun SPref.setInt(key: String, value: Int) {
    NSUserDefaults.standardUserDefaults.setInteger(value.toLong(), key)
}

actual fun SPref.getString(key: String): String {
    return NSUserDefaults.standardUserDefaults.stringForKey(key).toString()
}

actual fun SPref.setString(key: String, value: String) {
    NSUserDefaults.standardUserDefaults.setString(value, key)
}

actual fun SPref.getBoolean(key: String): Boolean {
    return NSUserDefaults.standardUserDefaults.boolForKey(key)
}

actual fun SPref.setBoolean(key: String, value: Boolean) {
    NSUserDefaults.standardUserDefaults.setBool(value, key)
}

actual fun SPref.getLong(key: String): Long {
    return NSUserDefaults.standardUserDefaults.integerForKey(key)
}

actual fun SPref.setLong(key: String, value: Long) {
    NSUserDefaults.standardUserDefaults.setInteger(value, key)
}

actual fun SPref.getFloat(key: String): Float {
    return NSUserDefaults.standardUserDefaults.floatForKey(key)
}

actual fun SPref.setFloat(key: String, value: Float) {
    NSUserDefaults.standardUserDefaults.setFloat(value, key)
}