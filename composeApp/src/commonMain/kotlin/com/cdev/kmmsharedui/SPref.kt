package com.cdev.kmmsharedui

expect class SPref

expect fun SPref.getInt(key: String) : Int
expect fun SPref.setInt(key: String, value: Int)
expect fun SPref.getString(key: String) : String
expect fun SPref.setString(key: String, value: String)
expect fun SPref.getBoolean(key: String) : Boolean
expect fun SPref.setBoolean(key: String, value: Boolean)
expect fun SPref.getLong(key: String) : Long
expect fun SPref.setLong(key: String, value: Long)
expect fun SPref.getFloat(key: String) : Float
expect fun SPref.setFloat(key: String, value: Float)