package com.example.myalarmclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setAlarm.setOnClickListener(){
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.add(Calendar.SECOND,5)
            setAlarmManager(calendar)
        }
        cancelAlarm.setOnClickListener(){
            cancelAlarmManager()
        }
    }


    private fun setAlarmManager(calendar: Calendar){
        val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent  = Intent(this,AlarmBroadcastReceiver::class.java)
        val pending = PendingIntent.getBroadcast(this,0,intent,0)
        when{
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                val info = AlarmManager.AlarmClockInfo(
                    calendar.timeInMillis,null
                )
                am.setAlarmClock(info,pending)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ->{
                am.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pending)
            }
            else -> {
                am.set(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pending)
            }
        }
    }

    private fun cancelAlarmManager(){
        val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this,AlarmBroadcastReceiver::class.java)
        val pending = PendingIntent.getBroadcast(this,0,intent,0)
        am.cancel(pending)
    }




}
