package com.dk.kache

import java.util.concurrent.TimeUnit

class SelfClearingCache(private var cacheTime: Long = TimeUnit.MINUTES.toMillis(5)) : Kache {

    //entire cache will be cleared after this time period
    private var lastClearedTimeStamp = System.nanoTime()

    private val cache = HashMap<Any, Any>()

    override val size: Int
        get() {
            updateCache()
            return cache.size
        }

    override fun setCacheTimeInMin(time: Int) {
        cacheTime = TimeUnit.MINUTES.toMillis(time.toLong())
    }

    override fun set(key: Any, value: Any) {
        updateCache()
        cache.put(key, value)
    }

    override fun get(key: Any): Any? {
        updateCache()
        return cache.get(key)
    }

    override fun deleteKey(key: Any): Any? {
        updateCache()
        return this.cache.remove(key)
    }

    override fun clearCache() {
        this.cache.clear()
    }

    private fun updateCache() {
        val timePassed = System.nanoTime() - lastClearedTimeStamp

        if (timePassed < TimeUnit.MILLISECONDS.toNanos(cacheTime)) {
            //still time left. Exit condition.
            return
        }
        lastClearedTimeStamp = System.nanoTime()
        clearCache()
    }

}