package com.elpassion.android.commons.sharedpreferences

import androidx.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class AsPropertyWithDefaultExtensionTestCase {

    private val repository = mock<SharedPreferenceRepository<String>>()
    // tag::sharedpreferences-as-property-with-default-declaration[]
    private var secretKeyWithDefault by repository.asPropertyWithDefault("secret-key", "12345678")
    // end::sharedpreferences-as-property-with-default-declaration[]

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