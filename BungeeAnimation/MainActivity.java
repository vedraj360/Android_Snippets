<?xml version="1.0" encoding="utf-8"?> 
<LinearLayout
	xmlns:android="http://schemas.android.com/apk/res/android"
	xmlns:tools="http://schemas.android.com/tools"
	android:layout_width="match_parent"
	android:layout_height="match_parent"
	tools:context=".MainActivity"
	android:orientation="vertical"> 

	<Button
		android:layout_marginTop="40dp"
		android:textAllCaps="false"
		android:layout_marginBottom="20dp"
		android:layout_gravity="center"
		android:id="@+id/zoom_button"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:text="open with zoom animation"
		android:onClick="Open"
		/> 

	<Button
		android:textAllCaps="false"
		android:layout_margin="20dp"
		android:layout_gravity="center"
		android:id="@+id/split_button"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:text="open with split animation"
		android:onClick="Open"
		/> 

	<Button
		android:textAllCaps="false"
		android:layout_margin="20dp"
		android:layout_gravity="center"
		android:id="@+id/shrink_button"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:text="open with shrink animation"
		android:onClick="Open"
		/> 

	<Button
		android:textAllCaps="false"
		android:layout_margin="20dp"
		android:layout_gravity="center"
		android:id="@+id/card_button"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:text="open with card animation"
		android:onClick="Open"
		/> 

	<Button
		android:textAllCaps="false"
		android:layout_margin="20dp"
		android:layout_gravity="center"
		android:id="@+id/fade_button"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:text="open with fade animation"
		android:onClick="Open"
		/> 

	<Button
		android:textAllCaps="false"
		android:layout_margin="20dp"
		android:layout_gravity="center"
		android:id="@+id/diagonal_button"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:text="open with diagonal animation"
		android:onClick="Open"
		/> 
</LinearLayout> 
