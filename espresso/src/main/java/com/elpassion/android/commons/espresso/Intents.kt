package com.elpassion.android.commons.espresso

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.VerificationModes.noUnverifiedIntents
import android.support.test.espresso.intent.matcher.IntentMatchers

const val autoFinishingIntentActionName = "com.elpassion.android.commons.espresso.AutoFinishingIntent"
const val autoCancelingIntentActionName = "com.elpassion.android.commons.espresso.AutoCancelingIntent"

fun checkIntent(clazz: Class<out Activity>) {
    Intents.intended(IntentMatchers.hasComponent(clazz.name))
}

fun checkNoIntent(clazz: Class<out Activity>) {
    Intents.intended(IntentMatchers.hasComponent(clazz.name), noUnverifiedIntents())
}

fun prepareAutoFinishingIntent(intent: Intent? = null) = Intents.intending(IntentMatchers.hasAction(autoFinishingIntentActionName))
        .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, intent))

fun getAutoFinishingIntent() = Intent(autoFinishingIntentActionName)

fun prepareAutoCancelingIntent(intent: Intent? = null) = Intents.intending(IntentMatchers.hasAction(autoCancelingIntentActionName))
        .respondWith(Instrumentation.ActivityResult(Activity.RESULT_CANCELED, intent))

fun getAutoCancelingIntent() = Intent(autoCancelingIntentActionName)