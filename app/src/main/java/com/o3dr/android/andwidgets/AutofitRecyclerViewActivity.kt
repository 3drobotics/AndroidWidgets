package com.o3dr.android.andwidgets

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.o3dr.android.andwidgets.adapter.RecyclerViewAdapter
import com.o3dr.android.lib.andwidgets.views.AutofitRecyclerView

/**
 * Sample AutofitRecyclerView
 */
class AutofitRecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_auto_fit_recycler_view)

        val recyclerView = findViewById(R.id.auto_fit_recycler_view) as AutofitRecyclerView
        recyclerView.adapter = RecyclerViewAdapter(applicationContext)
    }
}
