package com.elpassion.android.commons.recycler_example.common

import java.util.*

fun createManyUsers() = listOf(
        createDoctorHouse(), createDoctorWho(), createDoctorX(),
        createDoctorHouse(), createDoctorWho(), createDoctorX(),
        createDoctorHouse(), createDoctorWho(), createDoctorX(),
        createDoctorHouse(), createDoctorWho(), createDoctorX(),
        createDoctorHouse(), createDoctorWho(), createDoctorX()
)

fun createUsersWithASection() = createManyUsers()
        .filter { it.organization == "A" }

private val random by lazy { Random() }

private fun createDoctorX() = User(id = random.nextLong(), name = "Doctor X", organization = "B")

private fun createDoctorHouse() = User(id = random.nextLong(), name = "Doctor House", organization = "A")

private fun createDoctorWho() = User(id = random.nextLong(), name = "Doctor Who", organization = "A")
