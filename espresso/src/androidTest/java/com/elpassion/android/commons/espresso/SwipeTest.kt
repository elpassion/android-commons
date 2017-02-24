package com.elpassion.android.commons.espresso

import android.app.Activity
import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.support.v4.view.GestureDetectorCompat
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import com.elpassion.android.commons.espresso.matchers.createObjectMatcher
import com.elpassion.android.commons.espresso.test.R
import org.junit.Rule
import org.junit.Test
import java.lang.Math.abs

class SwipeTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(FakeActivity::class.java)

    @Test
    fun shouldBehaveWhenSwipedLeft() {
        onId(R.id.first).swipeLeft()
        onId(R.id.first).hasTag(withVelocity(velocityLeft, "x negative"))
    }

    @Test
    fun shouldBehaveWhenSwipedRight() {
        onId(R.id.first).swipeRight()
        onId(R.id.first).hasTag(withVelocity(velocityRight, "x positive"))
    }

    @Test
    fun shouldBehaveWhenSwipedUp() {
        onId(R.id.first).swipeUp()
        onId(R.id.first).hasTag(withVelocity(velocityUp, "y negative"))
    }

    @Test
    fun shouldBehaveWhenSwipedDown() {
        onId(R.id.first).swipeDown()
        onId(R.id.first).hasTag(withVelocity(velocityDown, "y positive"))
    }

    class FakeActivity : Activity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val button = Button(this)
            button.id = R.id.first
            val detector = GestureDetectorCompat(this, object : GestureDetector.SimpleOnGestureListener() {
                override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                    button.tag = Velocity(velocityX, velocityY)
                    return true
                }
            })
            button.setOnTouchListener { v, event -> detector.onTouchEvent(event) }
            setContentView(button)
        }
    }

    data class Velocity(val x: Float, val y: Float)

    private val velocityLeft: (Velocity) -> Boolean = { it.x < -1.0f && abs(it.y) < 1.0f }
    private val velocityRight: (Velocity) -> Boolean = { it.x > 1.0f && abs(it.y) < 1.0f }
    private val velocityUp: (Velocity) -> Boolean = { abs(it.x) < 1.0f && it.y < -1.0f }
    private val velocityDown: (Velocity) -> Boolean = { abs(it.x) < 1.0f && it.y > 1.0f }

    private fun withVelocity(predicate: (Velocity) -> Boolean, predicateDescription: String)
            = createObjectMatcher<Any, Velocity>(predicate, { it.appendText("is Velocity with $predicateDescription") })
}
