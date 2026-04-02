package com.reringuy.framework_service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.reringuy.aidl.ITemperatureService

open class TemperatureService: Service() {

    private val binder = object : ITemperatureService.Stub() {
        override fun getTemperature(): Float {
            return 25.0f
        }
    }

    override fun onBind(p0: Intent?): IBinder {
        return binder
    }
}