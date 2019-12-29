package com.android.weatherapp;

import android.content.Context;
import android.content.SharedPreferences;

class CityDataActivity {

	static String main(Context context, String parameter, String data, boolean write) {

		SharedPreferences cityAndCountryData = context.getSharedPreferences("Parameter", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = cityAndCountryData.edit();

		if (write) {

			editor.putString(parameter, data);
			editor.apply();

		}

		return cityAndCountryData.getString(parameter, null);

	}
}