package com.elpassion.sharedpreferences.moshiadapter

import android.preference.PreferenceManager
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.elpassion.android.commons.sharedpreferences.createSharedPrefs
import com.squareup.moshi.Types
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedPreferencesTestCase {

    @Test
    fun shouldSaveStringToRepository() {
        // tag::sharedpreferences-create-moshi-converter-adapter[]
        val jsonAdapter = moshiConverterAdapter<String>()
        // end::sharedpreferences-create-moshi-converter-adapter[]
        val repository = createSharedPrefs(sharedPreferences, jsonAdapter)
        val valueSaved = "someValue"
        repository.write("key", valueSaved)

        val valueRead = repository.read("key")
        Assert.assertEquals(valueSaved, valueRead)
    }

    data class SimpleStructure(val value: Int)

    @Test
    fun shouldSaveObjectToRepository() {
        val repository = createSharedPrefs<SimpleStructure>(sharedPreferences, moshiConverterAdapter())
        val valueSaved = SimpleStructure(1)
        repository.write("key", valueSaved)

        val valueRead = repository.read("key")
        Assert.assertEquals(valueSaved, valueRead)
    }

    @Test
    fun shouldSaveListOfObjectsToRepository() {
        val valueSaved = listOf(SimpleStructure(1))
        val repository = createSharedPrefs<List<SimpleStructure>>(sharedPreferences, moshiConverterAdapter(type = Types.newParameterizedType(List::class.java, SimpleStructure::class.java)))
        repository.write("key", valueSaved)

        val valueRead = repository.read("key")
        Assert.assertEquals(valueSaved, valueRead)
    }

    @Test
    fun shouldBePossibleToSaveNull() {
        val repository = createSharedPrefs<SimpleStructure>(sharedPreferences, moshiConverterAdapter())
        repository.write("key", null)

        Assert.assertEquals(null, repository.read("key"))
    }

    @Test
    fun containsShouldReturnFalseWhenSharedPreferencesIsEmpty() {
        val repository = createSharedPrefs<SimpleStructure>(sharedPreferences, moshiConverterAdapter())

        Assert.assertFalse(repository.contains("key"))
    }

    @Test
    fun containsShouldReturnTrueWhenSharedPreferencesContainsGivenKey() {
        val repository = createSharedPrefs<SimpleStructure>(sharedPreferences, moshiConverterAdapter())
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