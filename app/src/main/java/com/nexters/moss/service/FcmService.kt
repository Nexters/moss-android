package com.nexters.moss.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.nexters.moss.R
import com.nexters.moss.ui.main.MainActivity

class FcmService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val channelId = "channel_id"

        with(remoteMessage) {
            if (notification != null) {
                val messageTitle = remoteMessage.notification?.title ?: "none_title"
                val messageBody = remoteMessage.notification?.body ?: "none_body"

                Log.d("TAG", "타이틀 : $messageTitle")
                Log.d("TAG", "본문 : $messageBody")

                val intent = Intent(this@FcmService, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }

                val pendingIntent = PendingIntent.getActivity(this@FcmService, 0, intent, PendingIntent.FLAG_ONE_SHOT)
                val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val builder = NotificationCompat.Builder(this@FcmService, channelId)
                    .setSmallIcon(R.drawable.icon_splash)
                    .setContentTitle(messageTitle)
                    .setContentText(messageBody)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val channelName = "channel_name"
                    val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                    notificationManager.createNotificationChannel(channel)
                }
                notificationManager.notify(0, builder.build())
            }
        }
    }
}