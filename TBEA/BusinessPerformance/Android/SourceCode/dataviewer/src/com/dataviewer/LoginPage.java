package com.dataviewer;

import com.androidquery.AQuery;
import com.tbea.dataviewer.R;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class LoginPage extends AQueryFragment {

	@Override
	protected void onViewPrepared(final AQuery aq, View fragView) {

		aq.id(R.id.login).clicked(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (auth(aq.id(R.id.usrn).getText().toString(), aq.id(R.id.psw)
						.getText().toString())) {
					FragmentTransaction ft = getActivity().getFragmentManager()
							.beginTransaction();
					ft.replace(R.id.host, new HomePage());
					ft.commit();
				}
			}
		});
	}

	@Override
	public View onLoadView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.loginpage, container, false);
	}

	private boolean auth(String usn, String psw) {
		return true;
	}
}
