package com.o3dr.android.andwidgets

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class PagerFragment : Fragment() {
    companion object {
        const val ARG_PAGE_NUMBER = "page_number";
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_pager, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pageNumber = arguments?.getInt(ARG_PAGE_NUMBER, 0)
        val textView = view?.findViewById(R.id.pager_text_view) as TextView
        textView.text = "Page " + pageNumber
    }
}