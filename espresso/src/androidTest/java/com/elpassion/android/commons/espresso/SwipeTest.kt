package com.elpassion.android.commons.espresso

import android.app.Activity
import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.support.v4.view.GestureDetectorCompat
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
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
        onId(R.id.first).hasText(Direction.LEFT.name)
    }

    @Test
    fun shouldBehaveWhenSwipedRight() {
        onId(R.id.first).swipeRight()
        onId(R.id.first).hasText(Direction.RIGHT.name)
    }

    @Test
    fun shouldBehaveWhenSwipedUp() {
        onId(R.id.first).swipeUp()
        onId(R.id.first).hasText(Direction.UP.name)
    }

    @Test
    fun shouldBehaveWhenSwipedDown() {
        onId(R.id.first).swipeDown()
        onId(R.id.first).hasText(Direction.DOWN.name)
    }

    class FakeActivity : Activity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val button = Button(this)
            button.id = R.id.first
            val detector = GestureDetectorCompat(this, object : GestureDetector.SimpleOnGestureListener() {
                override fun onScroll(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                    button.text = detectDirection(velocityX, velocityY).name
                    return true
                }
            })
            button.setOnTouchListener { v, event -> detector.onTouchEvent(event) }
            setContentView(button)
        }

        private fun detectDirection(velocityX: Float, velocityY: Float): Direction {
            return if (abs(velocityX) > abs(velocityY)) {
                detectHorizontalDirection(velocityX)
            } else {
                detectVerticalDirection(velocityY)
            }
        }

        private fun detectHorizontalDirection(velocityX: Float): Direction {
            return if (velocityX > 0) {
                Direction.RIGHT
            } else {
                Direction.LEFT
            }
        }

        private fun detectVerticalDirection(velocityY: Float): Direction {
            return if (velocityY > 0) {
                Direction.DOWN
            } else {
                Direction.UP
            }
        }
    }

    enum class Direction {
        LEFT, RIGHT, UP, DOWN
    }
}
