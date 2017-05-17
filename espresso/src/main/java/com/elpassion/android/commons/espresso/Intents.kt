package com.elpassion.android.commons.espresso

import android.app.Activity
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.VerificationModes.noUnverifiedIntents
import android.support.test.espresso.intent.matcher.IntentMatchers

fun checkIntent(clazz: Class<out Activity>) {
    Intents.intended(IntentMatchers.hasComponent(clazz.name))
}

fun checkNoIntent(clazz: Class<out Activity>) {
    Intents.intended(IntentMatchers.hasComponent(clazz.name), noUnverifiedIntents())
}
