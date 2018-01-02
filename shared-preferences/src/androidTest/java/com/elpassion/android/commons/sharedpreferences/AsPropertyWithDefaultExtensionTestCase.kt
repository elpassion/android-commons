package com.elpassion.android.commons.sharedpreferences

import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class AsPropertyWithDefaultExtensionTestCase {

    val repository = mock<SharedPreferenceRepository<String>>()
    var secretKeyWithDefault by repository.asPropertyWithDefault("secret-key", "12345678")

    @Test
    fun shouldReadDefaultValueWhenRepositoryIsEmpty() {
        Assert.assertEquals("12345678", secretKeyWithDefault)
    }

    @Test
    fun shouldReadValueFromRepository() {
        whenever(repository.read(any())).thenReturn("12345678")
        Assert.assertEquals("12345678", secretKeyWithDefault)
    }

    @Test
    fun shouldWriteValueToRepository() {
        secretKeyWithDefault = "new-key"
        verify(repository).write("secret-key", "new-key")
    }
}