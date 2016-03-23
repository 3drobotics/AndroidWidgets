# AndWidgets
Collection of Android custom Views

### AspectRatioVideoView 
```
<com.o3dr.android.lib.andwidgets.views.AspectRatioVideoView
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  app:heightRatio="9"
  app:widthRatio="15"/>
```        

### VerticalSeekBar
```
<com.o3dr.android.lib.andwidgets.views.VerticalSeekBar
  android:layout_width="100dp"
  android:layout_height="match_parent"
  android:max="100"
  android:splitTrack="false"
  android:thumbOffset="-10dp"
  app:customThumb="@drawable/altitude_slider_icon"
  app:onlyThumb="true" />
```

* `app:onlyThumb` allows the options to decide whether touching anywhere on the seekbar or only on the thumb should initiate  the seekbar movement. `true` if only want thumb touch to allow seekbar move. <br/>
* `app:customThumb` is used instead of `android:thumb` to allow full redraw of the thumb.
<br/>

**NOTE:** <br/>
`android:thumb` will be ignored. <br/>
See http://stackoverflow.com/questions/33112277/android-6-0-marshmallow-stops-showing-vertical-seekbar-thumb/36094973

### TextVerticalSeekBar
```
<com.o3dr.android.lib.andwidgets.views.TextVerticalSeekBar
  android:layout_width="90dp"
  android:layout_height="match_parent"
  android:max="100"
  android:splitTrack="false"
  android:textSize="20sp"
  android:thumbOffset="-10dp"
  android:progress="0"
  android:text="text"
  app:customThumb="@drawable/altitude_slider_icon"
  app:disabledThumb="@drawable/disabled_thumb"
  app:onlyThumb="true"
  app:alignText="thumb"
  app:textGravity="center"/>
```        

* `app:textGravity` Location where the text should be drawn horizontally. The text will always be drawn in the center vertically. Possible values are `left`, `right`, and `center`. The default is `center`
* `app:customThumb` See above in `VerticalSeekBar`
* `app:disabledThumb` The thumb to show when the seekbar is disabled.
* `app:alignText` Draws the text on either the thumb or end of seekbar. Possible values are `thumb` or `progress`. Default is `thumb`
