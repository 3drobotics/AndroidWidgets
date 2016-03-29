package com.o3dr.android.andwidgets.adapter;

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.o3dr.android.andwidgets.PagerFragment


class PagerAdapter(fragmentMgr: FragmentManager) : FragmentStatePagerAdapter(fragmentMgr) {
    override fun getCount(): Int {
        return 6
    }

    override fun getItem(position: Int): Fragment? {
        val args = Bundle()
        args.putInt(PagerFragment.ARG_PAGE_NUMBER, position)
        val pagerFrag = PagerFragment()
        pagerFrag.arguments = args
        return pagerFrag
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Page " + position
    }

}