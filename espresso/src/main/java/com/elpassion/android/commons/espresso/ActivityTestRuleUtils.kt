package com.elpassion.android.commons.espresso

import android.content.Intent
import androidx.test.rule.ActivityTestRule

fun ActivityTestRule<*>.startActivity() {
    startActivity(Intent(Intent.ACTION_MAIN))
}

fun ActivityTestRule<*>.startActivity(intent: Intent) {
    launchActivity(intent)
}
