package com.vanard.ovotask.view

import android.os.SystemClock
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.vanard.ovotask.R
import com.vanard.ovotask.ui.activity.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        Espresso.onView(ViewMatchers.withId(R.id.tabs))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        SystemClock.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.popular_movie_list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}
