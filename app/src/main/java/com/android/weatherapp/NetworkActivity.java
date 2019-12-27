package com.android.weatherapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class NetworkActivity {

	static JSONObject jsonReceivedWeatherData = new JSONObject();

	public static void main(Context context) {

		RequestQueue queue = Volley.newRequestQueue(context);
		JsonObjectRequest jsonObjectRequestWeatherData = new JsonObjectRequest(Request.Method.GET, CityDataActivity.main(context, "URL", null, false), null,
			new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {

					jsonReceivedWeatherData = response;

				}
			}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {

				//

			}

		});

		queue.add(jsonObjectRequestWeatherData);

	}
}
