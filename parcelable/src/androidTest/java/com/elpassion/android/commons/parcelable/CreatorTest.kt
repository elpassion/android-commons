package com.elpassion.android.commons.parcelable

import android.os.Parcel
import android.os.Parcelable
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertSame
import org.junit.Test

class CreatorTest {

    private val parcel = Parcel.obtain()
    private val parcelable = ParcelableImpl()
    private val createObject: Parcel.() -> ParcelableImpl = { parcelable }

    @Test
    fun shouldCreateArrayOfNulls() {
        val CREATOR = createCreator(createObject)
        val createdArray = CREATOR.newArray(4)
        assertArrayEquals(arrayOfNulls(4), createdArray)
    }

    @Test
    fun shouldCreateParcelable() {
        val CREATOR = createCreator(createObject)
        val createdParcelable = CREATOR.createFromParcel(parcel)
        assertSame(parcelable, createdParcelable)
    }

    private class ParcelableImpl : Parcelable {

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
        }
    }
}
