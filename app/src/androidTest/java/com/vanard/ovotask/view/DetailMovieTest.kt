package com.vanard.ovotask.view

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.vanard.ovotask.ui.activity.detail.DetailActivity
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailMovieTest {

    @Rule
    @JvmField
    var mRule = ActivityTestRule(DetailActivity::class.java)
}