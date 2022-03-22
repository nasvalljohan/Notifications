package com.jojjenator.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    // Initialize variables
    // Put outside onCreate so that they can be used in functions/methods also outside onCreate but in class
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var notificationManager: NotificationManager
    private lateinit var builder: Notification.Builder

    // Hardcoded values, has to be strings,
    private val channelId = "0"
    private val description = "Welcome to our app" // Description shows in app-info.



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notificationBtn = findViewById<Button>(R.id.notiButton)
        val firstName = findViewById<EditText>(R.id.editTextOne)
        val lastName = findViewById<EditText>(R.id.editTextTwo)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationBtn.setOnClickListener{
            var contentTitle = firstName.text
            var contentText = lastName.text

            // IF build is API 26 or higher, run this code.
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Prepare notification
                notificationChannel = NotificationChannel(
                    channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.RED
                notificationChannel.enableVibration(false)

                // Create Notification
                notificationManager.createNotificationChannel(notificationChannel)

                // Build Notification
                builder = Notification.Builder(this, channelId)
                    .setContentTitle(contentTitle)
                    .setContentText(contentText)
                    .setShowWhen(true)
                    .setColor(4)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_foreground))

                notificationManager.notify(0, builder.build())

            }
            else {
                // For appcompat.
                // https://developer.android.com/training/notify-user/build-notification#kotlin
                // TODO App compat
                }
        }


    }



}