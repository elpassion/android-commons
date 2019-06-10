package com.elpassion.android.commons.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

infix fun AppCompatActivity.findNavHostFragment(id: Int) = this.supportFragmentManager.findFragmentById(id)
fun Fragment?.currentFragment(): Fragment? = this?.childFragmentManager?.fragments?.firstOrNull()
fun Fragment.navigate(destinationId: Int) = navController()?.navigate(destinationId)
fun Fragment.navController() = view?.findNavController()
