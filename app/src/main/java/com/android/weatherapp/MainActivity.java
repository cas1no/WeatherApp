// http://api.openweathermap.org/data/2.5/weather?q=Minsk&appid=2252c567272cab39a17aff97fa5b08ac&units=metric

package com.android.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

		if (CityDataActivity.main(this, "URL", null, false) == null) {

			CityDataActivity.main(this, "currCity", "NEW+YORK", true);
			CityDataActivity.main(this, "currCountry", "US", true);
			CityDataActivity.main(this, "URL", "http://api.openweathermap.org/data/2.5/weather?q=NEW+YORK,US&appid=2252c567272cab39a17aff97fa5b08ac&units=metric", true);

		}

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


		//onStart();
		//updateWeatherData();
		//setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		super.onResume();
		updateWeatherData();
		//setContentView();
	}

	@Override
	protected void onStart() {
		super.onStart();
		updateWeatherData();
		//setContentView();
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

			case R.id.buttonUpdate:

				//CityDataActivity.main(this, "URL", "http://api.openweathermap.org/data/2.5/weather?q=MINSK,BY&appid=2252c567272cab39a17aff97fa5b08ac&units=metric", true);
				updateWeatherData();//CityDataActivity.main(this, "URL", "http://api.openweathermap.org/data/2.5/weather?q=LOS+ANGELES,US&appid=2252c567272cab39a17aff97fa5b08ac&units=metric", true);

				break;
			case R.id.buttonSelectCity:

				DialogActivity dialog = new DialogActivity();
				dialog.show(getSupportFragmentManager(), "custom");
				

				test();

				break;

		}
	}


	public void test() {

		String tempURL = CityDataActivity.main(this, "URL", null, false).replace(CityDataActivity.main(this, "currCity", null, false), DialogActivity.tempCity);
		CityDataActivity.main(this, "URL", tempURL, true);
		updateWeatherData();
		dateText.setText(CityDataActivity.main(this, "URL", null, false));

	}

	public void responseAction(JSONObject weatherData) {

		try {

			java.util.Date currentDate = new java.util.Date(Long.valueOf(weatherData.getString("dt")) * 1000L);
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(currentDate);

			locationText.setText(weatherData.getString("name"));
			dateText.setText(String.format("Updated %s", currentDate));
			temperatureText.setText(weatherData.getJSONObject("main").getString("temp"));

			switch (weatherData.getJSONArray("weather").getJSONObject(0).getString("main")) {

				case "Thunderstorm":
					getWindow().setBackgroundDrawableResource(R.drawable.thunderstorm);
					weatherConditionText.setText(String.format("%s", "Thunderstorm"));
					break;
				case "Drizzle":
					getWindow().setBackgroundDrawableResource(R.drawable.drizzle);
					weatherConditionText.setText(String.format("%s", "Drizzle"));
					break;
				case "Rain":
					getWindow().setBackgroundDrawableResource(R.drawable.rain);
					weatherConditionText.setText(String.format("%s", "Rain"));
					break;
				case "Snow":
					getWindow().setBackgroundDrawableResource(R.drawable.snow);
					weatherConditionText.setText(String.format("%s", "Snow"));
					break;
				case "Clear":
					if (calendar.get(Calendar.HOUR_OF_DAY) <= 20 && calendar.get(Calendar.HOUR_OF_DAY) >= 4)
						getWindow().setBackgroundDrawableResource(R.drawable.clear_day);
					else
						getWindow().setBackgroundDrawableResource(R.drawable.clear_night);
					weatherConditionText.setText(String.format("%s", "Clear"));
					break;
				case "Clouds":
					if (calendar.get(Calendar.HOUR_OF_DAY) <= 20 && calendar.get(Calendar.HOUR_OF_DAY) >= 4)
						getWindow().setBackgroundDrawableResource(R.drawable.clouds_day);
					else
						getWindow().setBackgroundDrawableResource(R.drawable.clouds_night);
					weatherConditionText.setText(String.format("%s", "Clouds"));
					break;
				default:
					getWindow().setBackgroundDrawableResource(R.drawable.atmosphere);
					weatherConditionText.setText(String.format("%s", "Atmosphere"));
					break;

			}

			windDataText.setText(String.format("%s m/s, %s deg", weatherData.getJSONObject("wind").getString("speed"), weatherData.getJSONObject("wind").getString("deg")));
			humidityDataText.setText(String.format("%s %%", weatherData.getJSONObject("main").getString("humidity")));
			feelsLikeText.setText(String.format("%s %s", weatherData.getJSONObject("main").getString("feels_like"), Html.fromHtml("&#176;C")));
			pressureText.setText(String.format("%s hPa", weatherData.getJSONObject("main").getString("pressure")));

		} catch (JSONException e) {
			//
		}
	}

	public void updateWeatherData() {

		NetworkActivity.main(this);
		responseAction(NetworkActivity.jsonReceivedWeatherData);

	}
}