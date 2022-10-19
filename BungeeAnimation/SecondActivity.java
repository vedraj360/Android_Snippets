package org.geeksforgeeks.gfgexcuseme; 

import androidx.appcompat.app.AppCompatActivity; 
import android.os.Bundle; 
import spencerstudios.com.bungeelib.Bungee; 

public class SecondActivity extends AppCompatActivity { 

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_second); 
	} 

	// whenever user taps on BackButton 
	// slideLeft animation will be 
	// shown to the user 
	@Override
	public void onBackPressed() { 
		super.onBackPressed(); 
		Bungee.slideLeft(this); 
	} 
}	 
