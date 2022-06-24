package com.example.remoteconfigfireebase

import android.content.ContentValues.TAG
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class MainActivity : AppCompatActivity() {

    private val TAG = "RemoteConfig"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myMessage = findViewById<TextView>(R.id.my_message)

        val defaults = mapOf(
            "font_size" to 18,
            "font_color" to "#ff0000"
        )

        val remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig.setDefaultsAsync(defaults)

        val fontSize = remoteConfig.getDouble("font_size")
        val fontColor = remoteConfig.getString("font_color")

        myMessage.textSize = fontSize.toFloat()
        myMessage.setTextColor(Color.parseColor(fontColor))

        remoteConfig.fetchAndActivate()
        remoteConfig.fetch(0).addOnSuccessListener {
            Log.i(TAG, "Fetched values successfully")
        }
    }
}