package com.elpassion.android.commons.parcelable

import android.os.Parcel
import android.os.Parcelable
import org.junit.Assert
import org.junit.Test

class NullableParcelTest {

    private val parcel = Parcel.obtain()
    private val nullParcelable: ParcelableImpl? = null
    private val parcelable: ParcelableImpl? = ParcelableImpl("id")

    @Test
    fun shouldParcelNull() {
        nullParcelable.writeNullableToParcel(parcel, 0)
        parcel.setDataPosition(0)
        Assert.assertEquals(null, ParcelableImpl.CREATOR.createNullableFromParcel(parcel))
    }

    @Test
    fun shouldParcelNotNullNullable() {
        parcelable.writeNullableToParcel(parcel, 0)
        parcel.setDataPosition(0)
        Assert.assertEquals(parcelable, ParcelableImpl.CREATOR.createNullableFromParcel(parcel))
    }

    private data class ParcelableImpl(val id: String) : Parcelable {

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(id)
        }

        companion object {
            @JvmField
            val CREATOR = createCreator {
                ParcelableImpl(readString())
            }
        }
    }
}