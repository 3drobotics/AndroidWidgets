package com.o3dr.android.andwidgets

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Table of contents for the different sections of the style guide.
 * Created by Fredia Huya-Kouadio on 11/15/15.
 */
class TableOfContents : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_of_contents)

        findViewById(R.id.setting_list_item_view_button).setOnClickListener {
            startActivity(Intent(this, SettingListItemViewActivity::class.java))
        }

        findViewById(R.id.vertical_seek_bars_button).setOnClickListener {
            startActivity(Intent(this, VerticalSeekBarsActivity::class.java))
        }

        findViewById(R.id.interval_seek_bar_button).setOnClickListener {
            startActivity(Intent(this, IntervalSeekBarActivity::class.java))
        }

        findViewById(R.id.auto_fit_recycler_view_button).setOnClickListener {
            startActivity(Intent(this, AutofitRecyclerViewActivity::class.java))
        }

        findViewById(R.id.custom_view_pager_button).setOnClickListener {
            startActivity(Intent(this, CustomSwipeViewPagerActivity::class.java))
        }
    }
}