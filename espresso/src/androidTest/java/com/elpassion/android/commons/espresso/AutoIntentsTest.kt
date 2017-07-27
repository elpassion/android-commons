package com.elpassion.android.commons.espresso

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.*
import android.support.test.rule.ActivityTestRule
import android.widget.Button
import android.widget.LinearLayout
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test

const val FINISHING_BUTTON_ID = 665
const val CANCELING_BUTTON_ID = 666

class AutoIntentsTest {

    @JvmField @Rule
    val testActivityRule = ActivityTestRule(TestActivity::class.java, false, false)

    @JvmField @Rule
    val intentsRule = InitIntentsRule()

    @Test
    fun shouldCreateAutoFinishingIntent() {
        prepareAutoFinishingIntent()
        testActivityRule.startActivity()
        onId(FINISHING_BUTTON_ID).click()
        intended(allOf(hasAction(autoFinishingIntentActionName), anyIntent()))
    }

    @Test
    fun shouldAutoFinishingIntentUseCorrectData() {
        prepareAutoFinishingIntent()
        testActivityRule.startActivity()
        onId(FINISHING_BUTTON_ID).click()
        intended(allOf(hasAction(autoFinishingIntentActionName), hasData(Uri.parse("AUTO_FINISHING_INTENT_DATA"))))
    }

    @Test
    fun shouldCreateAutoCancelingIntent() {
        prepareAutoCancelingIntent()
        testActivityRule.startActivity()
        onId(CANCELING_BUTTON_ID).click()
        intended(allOf(hasAction(autoCancelingIntentActionName), anyIntent()))
    }

    @Test
    fun shouldAutoCancelingIntentUseCorrectData() {
        prepareAutoCancelingIntent()
        testActivityRule.startActivity()
        onId(CANCELING_BUTTON_ID).click()
        intended(allOf(hasAction(autoCancelingIntentActionName), hasData(Uri.parse("AUTO_CANCELING_INTENT_DATA"))))
    }

    class TestActivity : Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val linearLayout = LinearLayout(this)
            linearLayout.apply {
                addView(Button(context).apply {
                    setOnClickListener {
                        val intent = getAutoFinishingIntent().apply { data = Uri.parse("AUTO_FINISHING_INTENT_DATA") }
                        startActivity(intent)
                    }
                    id = FINISHING_BUTTON_ID
                })
                addView(Button(context).apply {
                    setOnClickListener {
                        val intent = getAutoCancelingIntent().apply { data = Uri.parse("AUTO_CANCELING_INTENT_DATA") }
                        startActivity(intent)
                    }
                    id = CANCELING_BUTTON_ID
                })
            }
            setContentView(linearLayout)
        }
    }
}