package com.android.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NetworkActivity.TextViewUpdateListener {

	TextView locationText;
	TextView dateText;
	TextView temperatureText;
	TextView weatherConditionText;
	TextView windDataText;
	TextView humidityDataText;
	TextView feelsLikeText;
	TextView pressureText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.buttonUpdate).setOnClickListener(this);
		findViewById(R.id.buttonSelectCity).setOnClickListener(this);

		locationText = findViewById(R.id.textLocation);
		dateText = findViewById(R.id.textDate);
		temperatureText = findViewById(R.id.textTemperature);
		weatherConditionText = findViewById(R.id.textWeatherCondition);
		windDataText = findViewById(R.id.textWindData);
		humidityDataText = findViewById(R.id.textHumidityData);
		feelsLikeText = findViewById(R.id.textFeelsLikeData);
		pressureText = findViewById(R.id.textPressureData);

		if (CityDataActivity.main(this, "URL", null, false) == null) {

			CityDataActivity.main(this, "currCity", "NEW+YORK", true);
			CityDataActivity.main(this, "currCountry", "US", true);
			CityDataActivity.main(this, "URL", "https://api.openweathermap.org/data/2.5/weather?q=NEW+YORK,US&appid=2252c567272cab39a17aff97fa5b08ac&units=metric", true);

		}

		NetworkActivity.main(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

			case R.id.buttonUpdate:

				NetworkActivity.main(this);

				break;

			case R.id.buttonSelectCity:

				FragmentManager manager = getSupportFragmentManager();
				DialogActivity dialog = new DialogActivity();
				dialog.show(manager, "dialog");

				break;

		}
	}

	@Override
	public void textViewUpdate(String location, String string) {

		switch (location) {

			case "locationText":

				locationText.setText(string);

				break;

			case "dateText":

				dateText.setText(string);

				break;

			case "temperatureText":

				temperatureText.setText(string);

				break;

			case "weatherConditionText":

				weatherConditionText.setText(string);

				break;

			case "windDataText":

				windDataText.setText(string);

				break;

			case "humidityDataText":

				humidityDataText.setText(string);

				break;

			case "feelsLikeText":

				feelsLikeText.setText(string);

				break;

			case "pressureText":

				pressureText.setText(string);

				break;

			case "background":

				switch (string) {

					case "11d":
					case "11n":

						getWindow().setBackgroundDrawableResource(R.drawable.thunderstorm);

						break;

					case "09d":
					case "09n":

						getWindow().setBackgroundDrawableResource(R.drawable.drizzle);

						break;

					case "10d":
					case "10n":

						getWindow().setBackgroundDrawableResource(R.drawable.rain);

						break;

					case "13d":
					case "13n":

						getWindow().setBackgroundDrawableResource(R.drawable.snow);

						break;

					case "50d":
					case "50n":

						getWindow().setBackgroundDrawableResource(R.drawable.atmosphere);

						break;


					case "01d":

						getWindow().setBackgroundDrawableResource(R.drawable.clear_day);

						break;

					case "01n":

						getWindow().setBackgroundDrawableResource(R.drawable.clear_night);

						break;

					case "02d":
					case "03d":
					case "04d":

						getWindow().setBackgroundDrawableResource(R.drawable.clouds_day);

						break;

					case "02n":
					case "03n":
					case "04n":

						getWindow().setBackgroundDrawableResource(R.drawable.clouds_night);

						break;

				}

				break;

		}
	}
}