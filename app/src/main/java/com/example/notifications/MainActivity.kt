package com.example.notifications

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notifications.ui.theme.NotificationsTheme

class MainActivity : ComponentActivity() {

    val CHANNEL_ID = "channelid"
    val CHANNEL_NAME = "channelName"
    val notificationId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        val notification = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("30 days of App dev challenge")
            .setContentText("Congrats for showing up today")
            .setSmallIcon(R.drawable.baseline_insert_emoticon_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManger = NotificationManagerCompat.from(this)
        val btn = findViewById<Button>(R.id.button2)

        btn.setOnClickListener {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
            ) {
            }
            notificationManger.notify(notificationId,notification)
        }
    }

    fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "This is my notification channel"
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}
