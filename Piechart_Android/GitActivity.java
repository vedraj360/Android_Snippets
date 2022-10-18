package com.example.piechart; 

// Import the required libraries 
import androidx.appcompat.app.AppCompatActivity; 
import android.graphics.Color; 
import android.os.Bundle; 
import android.widget.TextView; 
import org.eazegraph.lib.charts.PieChart; 
import org.eazegraph.lib.models.PieModel; 

public class MainActivity 
	extends AppCompatActivity { 

	// Create the object of TextView 
	// and PieChart class 
	TextView tvR, tvPython, tvCPP, tvJava; 
	PieChart pieChart; 

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{ 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main); 

		// Link those objects with their 
		// respective id's that 
		// we have given in .XML file 
		tvR = findViewById(R.id.tvR); 
		tvPython = findViewById(R.id.tvPython); 
		tvCPP = findViewById(R.id.tvCPP); 
		tvJava = findViewById(R.id.tvJava); 
		pieChart = findViewById(R.id.piechart); 

		// Creating a method setData() 
		// to set the text in text view and pie chart 
		setData(); 
	} 

	private void setData() 
	{ 

		// Set the percentage of language used 
		tvR.setText(Integer.toString(40)); 
		tvPython.setText(Integer.toString(30)); 
		tvCPP.setText(Integer.toString(5)); 
		tvJava.setText(Integer.toString(25)); 

		// Set the data and color to the pie chart 
		pieChart.addPieSlice( 
			new PieModel( 
				"R", 
				Integer.parseInt(tvR.getText().toString()), 
				Color.parseColor("#FFA726"))); 
		pieChart.addPieSlice( 
			new PieModel( 
				"Python", 
				Integer.parseInt(tvPython.getText().toString()), 
				Color.parseColor("#66BB6A"))); 
		pieChart.addPieSlice( 
			new PieModel( 
				"C++", 
				Integer.parseInt(tvCPP.getText().toString()), 
				Color.parseColor("#EF5350"))); 
		pieChart.addPieSlice( 
			new PieModel( 
				"Java", 
				Integer.parseInt(tvJava.getText().toString()), 
				Color.parseColor("#29B6F6"))); 

		// To animate the pie chart 
		pieChart.startAnimation(); 
	} 
} 
