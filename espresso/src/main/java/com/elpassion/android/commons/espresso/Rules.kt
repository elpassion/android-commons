package com.elpassion.android.commons.espresso

import android.support.test.espresso.intent.Intents
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class InitIntentsRule : TestRule {

    override fun apply(base: Statement, description: Description?) = object : Statement() {

        override fun evaluate() {
            Intents.init()
            try {
                base.evaluate()
            } finally {
                Intents.release()
            }
        }
    }
}