package com.vanard.ovotask.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.vanard.ovotask.R
import com.vanard.ovotask.ui.fragment.favorite.FavoriteFragment
import com.vanard.ovotask.ui.fragment.popular.PopularFragment
import com.vanard.ovotask.ui.fragment.toprated.TopRatedFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return PopularFragment.newInstance(position)
            1 -> return TopRatedFragment.newInstance(position)
            2 -> return FavoriteFragment.newInstance(position)
        }
        return PopularFragment.newInstance(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 3
    }
}