package com.elpassion.android.commons.sharedpreferences

import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AsPropertyExtensionTestCase {

    val repository = mock<SharedPreferenceRepository<String>>()
    // tag::sharedpreferences-as-property-declaration[]
    var secretKey by repository.asProperty("secret-key")
    // end::sharedpreferences-as-property-declaration[]

    @Test
    fun shouldReadNullValueFromRepository() {
        Assert.assertEquals(null, secretKey)
    }

    @Test
    fun shouldReadValueFromRepository() {
        whenever(repository.read(any())).thenReturn("12345678")
        Assert.assertEquals("12345678", secretKey)
    }

    @Test
    fun shouldWriteValueToRepository() {
        secretKey = "new-key"
        verify(repository).write("secret-key", "new-key")
    }
}