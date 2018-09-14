package com.primesoft.solarcalculator.common

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.widget.Toast


fun Activity.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

@SuppressLint("ApplySharedPref")
 fun Context.saveInt(key: String, value: Int) {
    this.getSharedPreferences(Constant.LOCAL_STORAGE_NAME, Context.MODE_PRIVATE).edit()
            .putInt(key, value)
            .commit()
}

 fun Context.readInt(key: String, defValue: Int = 0): Int {
    val value = this.getSharedPreferences(Constant.LOCAL_STORAGE_NAME, Context.MODE_PRIVATE)
            .getInt(key, defValue)
    return value
}

inline fun Context.saveStringAsync(key: String, value: String) {
    this.getSharedPreferences(Constant.LOCAL_STORAGE_NAME, Context.MODE_PRIVATE).edit()
            .putString(key, value)
            .apply()
}


@SuppressLint("ApplySharedPref")
inline fun Context.deleteString(key: String) {
    this.getSharedPreferences(Constant.LOCAL_STORAGE_NAME, Context.MODE_PRIVATE).edit()
            .putString(key, "")
            .commit()
}

inline fun Context.deleteStringAsync(key: String) {
    this.getSharedPreferences(Constant.LOCAL_STORAGE_NAME, Context.MODE_PRIVATE).edit()
            .putString(key, "")
            .apply()
}