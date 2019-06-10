package com.livestreamfails.streams.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

private const val MAX_ITEMS = 1000 // there is no way to scrap total stream items count from https://livestreamfails.com/

class StreamItemPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return StreamItemFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return MAX_ITEMS
    }
}