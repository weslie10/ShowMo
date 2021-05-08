package com.weslie10.showmo.ui.main.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.weslie10.showmo.R
import com.weslie10.showmo.ui.main.movie.MovieFragment
import com.weslie10.showmo.ui.main.tvshow.TvShowFragment

class SectionsPagerHomeAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MovieFragment()
            1 -> TvShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_shows)
    }
}