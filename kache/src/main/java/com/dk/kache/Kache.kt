package com.dk.kache

interface Kache {

    val size: Int

    fun setCacheTimeInMin(time: Int)

    fun set(key: Any, value: Any)

    fun get(key: Any): Any?

    fun deleteKey(key: Any): Any?

    fun clearCache()
}