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

### SettingListItemView
![alt text](https://github.com/3drobotics/AndroidWidgets/blob/develop/images/setting_list_item_view.png)

```    
<com.o3dr.android.lib.andwidgets.views.SettingListItemView
  android:layout_width="match_parent"
  android:layout_height="wrap_content"
  app:header="Test"
  app:subHeader="Sub Test"/>
```

### VerticalSeekBar
![alt text](https://github.com/3drobotics/AndroidWidgets/blob/develop/images/vertical_seek_bars.png)

```
<com.o3dr.android.lib.andwidgets.views.VerticalSeekBar
  android:layout_width="100dp"
  android:layout_height="match_parent"
  android:max="100"
  android:splitTrack="false"
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
* 

### IntervalSeekBar
![alt text](https://github.com/3drobotics/AndroidWidgets/blob/develop/images/interval_seek_bar.png)

```
<com.o3dr.android.lib.andwidgets.views.IntervalSeekBar
  android:id="@+id/interval_seek_bar"
  android:layout_width="match_parent"
  android:layout_height="wrap_content"
  android:max="100"
  app:dotColor="@color/blue"
  app:dotRadius="5dp"/>
```

* `app:dotColor` sets the color for the interval circles
* `app:dotRadius` the radisu for the interval circles
* In code, you can set the locations of the dots. The values indicate the progress where the dots should be drawn. 
```
  int[] dotLocations = new int[]{0, 50, 75, 100};
  intervalSeekBar.setDotLocations(dotLocations);
```

### AutofitRecyclerView
![alt text](https://github.com/3drobotics/AndroidWidgets/blob/develop/images/auto_fit_recycler.png)

```
<com.o3dr.android.lib.andwidgets.views.AutofitRecyclerView
  android:id="@+id/auto_fit_recycler_view"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  app:columnWidth="100dp"/>
```

* `app:columnWidth` specifies the width of each recycler view item. The code will calcuate how many columns there should be

### CustomSwipeViewPager

```
<com.o3dr.android.lib.andwidgets.views.CustomSwipeViewPager
  android:id="@+id/custom_swipe_view_pager"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  app:swipeEnabled="false"/>
```

* `app:swipeEnabled` set whether the viewPager can be swiped or not.
