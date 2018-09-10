package com.elpassion.android.commons.espresso

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.anyIntent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.rule.ActivityTestRule
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert.assertTrue
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
        val intent = Intent().apply { data = Uri.parse("AUTO_FINISHING_INTENT_DATA") }
        prepareAutoFinishingIntent(intent)
        testActivityRule.startActivity()
        onId(FINISHING_BUTTON_ID).click()
        assertTrue(testActivityRule.activity.intentData == Uri.parse("AUTO_FINISHING_INTENT_DATA"))
    }

    @Test
    fun shouldAutoFinishingIntentHaveCorrectResult() {
        prepareAutoFinishingIntent()
        testActivityRule.startActivity()
        onId(FINISHING_BUTTON_ID).click()
        assertTrue(testActivityRule.activity.resultCode == Activity.RESULT_OK)
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
        val intent = Intent().apply { data = Uri.parse("AUTO_CANCELING_INTENT_DATA") }
        prepareAutoCancelingIntent(intent)
        testActivityRule.startActivity()
        onId(CANCELING_BUTTON_ID).click()
        assertTrue(testActivityRule.activity.intentData == Uri.parse("AUTO_CANCELING_INTENT_DATA"))
    }

    @Test
    fun shouldAutoCancelingIntentHaveCorrectResult() {
        prepareAutoCancelingIntent()
        testActivityRule.startActivity()
        onId(CANCELING_BUTTON_ID).click()
        assertTrue(testActivityRule.activity.resultCode == Activity.RESULT_CANCELED)
    }

    class TestActivity : Activity() {

        var resultCode: Int? = null
        var intentData: Uri? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val linearLayout = LinearLayout(this)
            linearLayout.apply {
                addView(Button(context).apply {
                    setOnClickListener {
                        startActivity(getAutoFinishingIntent())
                    }
                    id = FINISHING_BUTTON_ID
                })
                addView(Button(context).apply {
                    setOnClickListener {
                        startActivity(getAutoCancelingIntent())
                    }
                    id = CANCELING_BUTTON_ID
                })
            }
            setContentView(linearLayout)
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            this.resultCode = resultCode
            this.intentData = data?.data
        }
    }
}