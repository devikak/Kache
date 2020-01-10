package com.dk.kache

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class SelfClearingCacheTest {

    private var kache: Kache = SelfClearingCache(TimeUnit.SECONDS.toMillis(3))

    @Before
    fun setup() {
        for (i in 1..10) {
            kache.set(i, i)
        }

    }

    @Test
    fun testSize_when10_returnTrue() {
        Assert.assertTrue(kache.size == 10)

    }

    @Test
    fun testset_whenSetValue_returnSameGetValue() {
        val key = 700
        val value = 1080
        kache.set(key, value)
        val storedValue = kache.get(key)

        Assert.assertEquals(value, storedValue)
    }

    @Test
    fun testset_whenDeleteValue_returnNull() {
        val key = 700
        val value = 1080
        kache.set(key, value)

        kache.deleteKey(key)
        val storedValue = kache.get(key)

        Assert.assertNull(storedValue)
    }

    @Test
    fun testClearCache_returnSizeAsZero() {
        kache.clearCache()
        Assert.assertEquals(0, kache.size)
    }

    @Test
    fun testifexpiring() {
        Thread.sleep(TimeUnit.SECONDS.toMillis(3))
        Assert.assertEquals(0, kache.size)
    }


    @After
    fun clearData() {
        kache.clearCache()
    }
}