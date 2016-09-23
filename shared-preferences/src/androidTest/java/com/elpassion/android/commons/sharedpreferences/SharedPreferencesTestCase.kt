package com.elpassion.android.commons.sharedpreferences

import android.preference.PreferenceManager
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedPreferencesTestCase {

    @Test
    fun shouldSaveStringToRepository() {
        val repository = createSharedPrefs<String>(sharedPreferences)
        val valueSaved = "someValue"
        repository.write("key", valueSaved)

        val valueRead = repository.read("key")
        Assert.assertEquals(valueSaved, valueRead)
    }

    data class SimpleStructure(val value: Int)

    @Test
    fun shouldSaveObjectToRepository() {
        val repository = createSharedPrefs<SimpleStructure>(sharedPreferences)
        val valueSaved = SimpleStructure(1)
        repository.write("key", valueSaved)

        val valueRead = repository.read("key")
        Assert.assertEquals(valueSaved, valueRead)
    }

    @Test
    fun shouldSaveListOfObjectsToRepository() {
        val repository = createSharedPrefs<List<SimpleStructure>>(sharedPreferences)
        val valueSaved = listOf(SimpleStructure(1))
        repository.write("key", valueSaved)

        val valueRead = repository.read("key")
        Assert.assertEquals(valueSaved, valueRead)
    }

    @Test
    fun shouldBePossibleToSaveNull() {
        val repository = createSharedPrefs<SimpleStructure>(sharedPreferences)
        repository.write("key", null)

        Assert.assertEquals(null, repository.read("key"))
    }

    @Test
    fun containsShouldReturnFalseWhenSharedPreferencesIsEmpty() {
        val repository = createSharedPrefs<SimpleStructure>(sharedPreferences)

        Assert.assertFalse(repository.contains("key"))
    }

    @Test
    fun containsShouldReturnTrueWhenSharedPreferencesContainsGivenKey() {
        val repository = createSharedPrefs<SimpleStructure>(sharedPreferences)
        repository.write("key", SimpleStructure(1))

        Assert.assertTrue(repository.contains("key"))
    }

    @After
    fun clearSharedPrefs() {
        sharedPreferences()
                .edit()
                .clear()
                .apply()
    }

    private val sharedPreferences = { PreferenceManager.getDefaultSharedPreferences(getContext()) }
    private fun getContext() = InstrumentationRegistry.getInstrumentation().targetContext
}