package com.o3dr.android.andwidgets

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import com.o3dr.android.andwidgets.adapter.PagerAdapter
import com.o3dr.android.lib.andwidgets.views.CustomSwipeViewPager

/**
 * Sample disabled swipe ViewPager
 */
class CustomSwipeViewPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_custom_swipe_view_pager)

        val viewPager = findViewById(R.id.custom_swipe_view_pager) as CustomSwipeViewPager

        val tabLayout = findViewById(R.id.tab_layout) as TabLayout

        viewPager.adapter = PagerAdapter(supportFragmentManager)

        tabLayout.setTabsFromPagerAdapter(viewPager.adapter)
        tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }
}