package com.example.tp2exo2

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val isSDPresent = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        val isSDSupportedDevice = Environment.isExternalStorageRemovable()

        var sdcard = "Not present"

        if(isSDSupportedDevice && isSDPresent)
        {
            sdcard = "Present"
        }

        tvText.text = "Device : "+Build.MODEL +"\n" +"Version : "+Build.VERSION.RELEASE+"\n" +""+getMemoryInfo()+"SD Card : "+sdcard

    }


    private fun getMemoryInfo(): CharSequence? {

        val memoryInfo = ActivityManager.MemoryInfo()
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.getMemoryInfo(memoryInfo)
        val runtime = Runtime.getRuntime()
        val builder = StringBuilder()

        builder.append("Available Memory: ").append(memoryInfo.availMem).append("\n")
                .append("Total Memory: ").append(memoryInfo.totalMem).append("\n").append("Runtime Maximum Memory: ")
                .append(runtime.maxMemory()).append("\n").append("Runtime Total Memory:")
                .append(runtime.totalMemory()).append("\n").append("Runtime Free Memory:")
                .append(runtime.freeMemory()).append("\n")
        return builder.toString()
    }
}