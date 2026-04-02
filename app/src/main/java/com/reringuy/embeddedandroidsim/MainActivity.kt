package com.reringuy.embeddedandroidsim

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.reringuy.aidl.ITemperatureService
import com.reringuy.embeddedandroidsim.ui.theme.EmbeddedAndroidSimTheme

class MainActivity : ComponentActivity() {
    private var service: ITemperatureService? = null

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            service = ITemperatureService.Stub.asInterface(binder)

            val temp = service?.getTemperature()
            Log.d("CLIENT", "Temperatura: $temp")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            service = null
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent()

        intent.setClassName(
            "com.reringuy.framework_service",
            "com.reringuy.framework_service.TemperatureService"
        )

        bindService(intent, connection, BIND_AUTO_CREATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            EmbeddedAndroidSimTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EmbeddedAndroidSimTheme {
        Greeting("Android")
    }
}