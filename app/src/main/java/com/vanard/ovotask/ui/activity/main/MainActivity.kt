package com.vanard.ovotask.ui.activity.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.vanard.ovotask.R

class MainActivity : AppCompatActivity() {

    var badge : View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter =
            SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

//        val bot = tabs.getChildAt(0) as TabLayout
//        val v = bot.getChildAt(2) as TabLayout
//        badge = LayoutInflater.from(this).inflate(R.layout.badge_favorite, v, true)
    }
}