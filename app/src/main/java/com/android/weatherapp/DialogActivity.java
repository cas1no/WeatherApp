package com.android.weatherapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class DialogActivity extends DialogFragment {

	static String tempCity;
	static String tempCountry;

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		LayoutInflater inflater = requireActivity().getLayoutInflater();
		View mView = inflater.inflate(R.layout.dialog, null);

		final EditText city = (EditText) mView.findViewById(R.id.editTextCity);
		final EditText country = (EditText) mView.findViewById(R.id.editTextCountry);

		builder.setView(mView)
			.setTitle("Enter your city")
			.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					//MainActivity.urlCity = MainActivity.urlCity.replace(" ", "+");
					//MainActivity.URL = MainActivity.URL.replace(String.format("%s%s", MainActivity.oldUrlCity, MainActivity.oldUrlCountry), Str

					tempCity = city.getText().toString().toUpperCase().replace(" ", "+");
					tempCountry = country.getText().toString().toUpperCase();

					//String tempURL;
					//tempURL = CityDataActivity.main(getContext(), "URL", null, false).replace(CityDataActivity.main(getContext(), "currCity", null, false), tempCity);
					//tempURL = CityDataActivity.main(null, "URL", null, false).replace(CityDataActivity.main(null, "currCountry", null, false), tempCountry);

					//CityDataActivity.main(getContext(), "URL", tempURL, true);

					dialog.cancel();

				}
			});

		return builder.create();

	}
}