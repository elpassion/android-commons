package com.elpassion.android.commons.recycler_example.common

import com.elpassion.android.commons.recycler.basic.WithStableId

data class User(
        override val id: Long,
        val name: String,
        val organization: String
) : WithStableId