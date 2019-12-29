package com.android.weatherapp;

import android.content.Context;
import android.text.Html;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.GregorianCalendar;

class NetworkActivity {

	private static JSONObject jsonReceivedWeatherData = new JSONObject();

	static void main(final Context context) {

		RequestQueue queue = Volley.newRequestQueue(context);
		JsonObjectRequest jsonObjectRequestWeatherData = new JsonObjectRequest(Request.Method.GET, CityDataActivity.main(context, "URL", null, false), null,
			new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {

					jsonReceivedWeatherData = response;

					responseAction(context, jsonReceivedWeatherData);

				}
			}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {

				Toast.makeText(context, "Location or network issue.", Toast.LENGTH_LONG).show();

			}

		});

		queue.add(jsonObjectRequestWeatherData);

	}

	public interface TextViewUpdateListener {

		void textViewUpdate(String textView, String string);

	}

	private static void responseAction(Context context, JSONObject weatherData) {

		try {

			java.util.Date currentDate = new java.util.Date(Long.valueOf(weatherData.getString("dt")) * 1000L);
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(currentDate);

			((TextViewUpdateListener) context).textViewUpdate("locationText", weatherData.getString("name"));
			((TextViewUpdateListener) context).textViewUpdate("dateText", String.format("Updated %s", currentDate));
			((TextViewUpdateListener) context).textViewUpdate("temperatureText", String.format("%s%s", weatherData.getJSONObject("main").getString("temp"), Html.fromHtml("&#176;C")));
			((TextViewUpdateListener) context).textViewUpdate("weatherConditionText", weatherData.getJSONArray("weather").getJSONObject(0).getString("main"));
			((TextViewUpdateListener) context).textViewUpdate("windDataText", String.format("%s m/s, %s deg", weatherData.getJSONObject("wind").getString("speed"), weatherData.getJSONObject("wind").getString("deg")));
			((TextViewUpdateListener) context).textViewUpdate("humidityDataText", String.format("%s %%", weatherData.getJSONObject("main").getString("humidity")));
			((TextViewUpdateListener) context).textViewUpdate("feelsLikeText", String.format("%s%s", weatherData.getJSONObject("main").getString("feels_like"), Html.fromHtml("&#176;C")));
			((TextViewUpdateListener) context).textViewUpdate("pressureText", String.format("%s hPa", weatherData.getJSONObject("main").getString("pressure")));
			((TextViewUpdateListener) context).textViewUpdate("background", weatherData.getJSONArray("weather").getJSONObject(0).getString("icon"));

		} catch (JSONException e) {

			Toast.makeText(context, "Server problems. Try later.", Toast.LENGTH_LONG).show();

		}
	}
}