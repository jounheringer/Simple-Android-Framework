package com.reringuy.embeddedandroidsim

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlin.random.Random

class TemperatureService: Service() {
    private val binder = object: ITemperatureInterface.Stub() {
        override fun temperature(): Double {
            return Random.nextDouble(-30.0, 50.0)
        }

    }

    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }
}