package com.o3dr.android.andwidgets

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.CompoundButton
import android.widget.SeekBar
import android.widget.Switch
import com.o3dr.android.lib.andwidgets.views.TextVerticalSeekBar
import com.o3dr.android.lib.andwidgets.views.VerticalSeekBar

/**
 * Table of contents for the different sections of the style guide.
 * Created by Fredia Huya-Kouadio on 11/15/15.
 */
class TableOfContents : AppCompatActivity() {
    var verticalSeek : VerticalSeekBar? = null
    var textVerticalSeek : TextVerticalSeekBar? = null
    var textOnProgressSeek : TextVerticalSeekBar? = null
    var verticalSeekSwitch : Switch? = null
    var textVerticalSeekSwitch : Switch? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_of_contents)

        verticalSeek = findViewById(R.id.vertical_seek_bar) as VerticalSeekBar
        textVerticalSeek = findViewById(R.id.text_vertical_seek_bar) as TextVerticalSeekBar
        verticalSeekSwitch = findViewById(R.id.vertical_seek_switch) as Switch
        textVerticalSeekSwitch = findViewById(R.id.text_vertical_seek_switch) as Switch

        verticalSeekSwitch?.setOnCheckedChangeListener({ compoundButton: CompoundButton, b: Boolean ->
            verticalSeek?.isOnlyThumb = b;
        })

        textVerticalSeekSwitch?.setOnCheckedChangeListener({ compoundButton: CompoundButton, b: Boolean ->
            textVerticalSeek?.isEnabled = b;
        })

        textVerticalSeek?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textVerticalSeek?.text = progress.toString();
            }
        })

        textOnProgressSeek = findViewById(R.id.text_on_thumb_seek) as TextVerticalSeekBar
        textOnProgressSeek?.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textOnProgressSeek?.text = progress.toString()
            }

        })
    }
}