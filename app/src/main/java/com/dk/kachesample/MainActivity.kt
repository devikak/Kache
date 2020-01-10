package com.dk.kachesample

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dk.kache.SelfClearingCache

class MainActivity : AppCompatActivity() {

    val logTag = "SelfClearingCache"

    val cache: SelfClearingCache = SelfClearingCache(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val key = "first"
        val value = "firstValue"

        cache.set(key, value)

        Log.d(logTag, cache.get(key).toString())

        // check after a minute and a half
        Handler().postDelayed({ Log.d(logTag, cache.get(key).toString()) }, 15 * 60 * 100)

    }
}
