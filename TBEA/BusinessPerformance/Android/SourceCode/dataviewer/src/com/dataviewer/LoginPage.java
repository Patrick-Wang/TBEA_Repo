package com.dataviewer;

import com.example.dataviewer.R;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class LoginPage extends AQueryFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.loginpage, container, false);
		update(v);

		OnClickListener l = new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (auth(aq.id(R.id.usrn).getText().toString(), aq.id(R.id.psw)
						.getText().toString())) {
					FragmentTransaction ft = getActivity().getFragmentManager()
							.beginTransaction();
					ft.hide(LoginPage.this);
					ft.commit();

					ft = getActivity().getFragmentManager().beginTransaction();
					ft.replace(R.id.host, new FunctionSelectorPage());
					ft.commit();

					ft = getActivity().getFragmentManager().beginTransaction();
					ft.remove(LoginPage.this);
					ft.commit();
					update(null);
				}
			}
		};

		aq.id(R.id.login).clicked(l);
		return v;
	}

	private boolean auth(String usn, String psw) {
		return true;
	}
}
