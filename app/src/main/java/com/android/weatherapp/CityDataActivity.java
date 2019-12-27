package com.android.weatherapp;

import android.content.Context;
import android.content.SharedPreferences;

public class CityDataActivity {

	String goodURL = "http://api.openweathermap.org/data/2.5/weather?q=NEW+YORK,US&appid=2252c567272cab39a17aff97fa5b08ac&units=metric";

	public static String main(Context context, String parameter, String data, boolean write) {                        // main(this, .....)

		SharedPreferences cityAndCountryData = context.getSharedPreferences("Parameter", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = cityAndCountryData.edit();

		if (write) {

			editor.putString(parameter, data);
			editor.apply();

		}

		return cityAndCountryData.getString(parameter, null);

	}
}