package com.elpassion.android.commons.espresso

import android.content.Intent
import android.support.test.rule.ActivityTestRule

fun ActivityTestRule<*>.startActivity() {
    startActivity(Intent(Intent.ACTION_MAIN))
}

private fun ActivityTestRule<*>.startActivity(intent: Intent) {
    launchActivity(intent)
}
