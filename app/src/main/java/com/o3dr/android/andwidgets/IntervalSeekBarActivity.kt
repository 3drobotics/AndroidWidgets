package com.o3dr.android.andwidgets

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.o3dr.android.lib.andwidgets.views.IntervalSeekBar

/**
 * Sample IntervalSeekBar
 */
class IntervalSeekBarActivity : AppCompatActivity() {
    var intervalSeekBar: IntervalSeekBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_interval_seek_bar)

        val dotLocations: IntArray = intArrayOf(0, 50, 75, 100)
        intervalSeekBar = findViewById(R.id.interval_seek_bar) as IntervalSeekBar
        intervalSeekBar?.dotLocations = dotLocations
    }

}