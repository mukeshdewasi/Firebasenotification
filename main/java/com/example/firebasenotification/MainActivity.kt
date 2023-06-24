package com.example.firebasenotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.firebasenotification.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNotification.setOnClickListener {
            createnotifiation()
        }
    }

    private fun createnotifiation() {
        var channelid="livestream"
        var channelname="LIve Stream"

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){


            var channel =NotificationChannel(channelid,channelname,NotificationManager.IMPORTANCE_HIGH)
            channel.description="Live Stream Channel"

            // Register the channel with the system
            var manager:NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
                manager.createNotificationChannel(channel)
            }
            // Create an explicit intent for an Activity in your app

        val intent= Intent(this,HomeActivity:: class.java).apply {

        }

        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(intent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(0,
                 PendingIntent.FLAG_MUTABLE
            )
        }
     //   val pendingIntent:PendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_MUTABLE)


        var builder=NotificationCompat.Builder(this,channelid)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle("Jio cinema")
            .setContentText("Tata IPL 2023, Now Free Striming")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Tata IPL 2023, Now Free Striming"))
            .setContentIntent(resultPendingIntent)
            .setAutoCancel(true)

        var manager:NotificationManagerCompat= NotificationManagerCompat.from(this)
        var id = Random(0).nextInt(10000)
        manager.notify(id,builder.build())
        Toast.makeText(applicationContext, "$id", Toast.LENGTH_SHORT).show()
    }
}