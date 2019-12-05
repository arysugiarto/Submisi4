package com.arysugiarto.submisi.footballmatchschedule


import android.support.test.espresso.Espresso
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click

import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.widget.AutoCompleteTextView
import com.arysugiarto.submisi.footballmatchschedule.ui.main.MainActivity

import org.junit.Rule
import org.junit.Test



class MainActivtyTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun SearchTest() {
        Thread.sleep(5000)

        onView(withId(R.id.actionSearch)).perform(click())
        Thread.sleep(6000)
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(
            ViewActions.typeText(
                "Chelsea"
            )
        )
            .perform(ViewActions.pressImeActionButton())
        Thread.sleep(8000)


        onView(withId(R.id.actionSearch)).perform(click())
        Thread.sleep(6000)
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(
            ViewActions.typeText(
                "Madrid"
            )
        )
            .perform(ViewActions.pressImeActionButton())
        Thread.sleep(8000)

        pressBack()
    }
}