package com.avion.projectskeletonwithhilt.utils

import android.os.Build

class Constants {
    companion object {
        // TODO: add your baseurl
        const val BASE_URL = "Base_url"
        const val USER_TYPE = "customer"
        val DEVICE_VERSION = Build.VERSION.SDK_INT
        val DEVICE_UUID = java.util.UUID.randomUUID().toString()
        val DEVICE_MODEL: String = Build.MODEL
        val DEVICE_MANUFACTURER: String = Build.MANUFACTURER
    }
}