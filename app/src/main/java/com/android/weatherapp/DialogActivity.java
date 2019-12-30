package com.android.weatherapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class DialogActivity extends DialogFragment {

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		LayoutInflater inflater = requireActivity().getLayoutInflater();
		View mView = inflater.inflate(R.layout.dialog, null);

		final EditText city = mView.findViewById(R.id.editTextCity);
		final EditText country = mView.findViewById(R.id.editTextCountry);

		builder.setView(mView)
			.setTitle("Enter your location")
			.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					if (city.getText().length() != 0 && country.getText().length() != 0) {

						String tempCity = city.getText().toString().toUpperCase().replace(" ", "+");
						String tempCountry = country.getText().toString().toUpperCase();
						String tempURL = CityDataActivity.main(requireActivity(), "URL", null, false);

						String currCity = CityDataActivity.main(requireActivity(), "currCity", null, false);
						String currCountry = CityDataActivity.main(requireActivity(), "currCountry", null, false);

						tempURL = tempURL.replace(currCity + "," + currCountry, tempCity + "," + tempCountry);

						CityDataActivity.main(requireActivity(), "currCity", tempCity, true);
						CityDataActivity.main(requireActivity(), "currCountry", tempCountry, true);
						CityDataActivity.main(requireActivity(), "URL", tempURL, true);

						NetworkActivity.main(requireActivity());

						dialog.cancel();

					} else {

						Toast.makeText(getActivity(), "Fill the form.", Toast.LENGTH_LONG).show();

						dialog.cancel();

					}
				}
			});

		return builder.create();

	}
}