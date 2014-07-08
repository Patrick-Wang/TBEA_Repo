package com.example.dataviewer;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LoginPage extends AQueryFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.loginpage, container, false);
		update(v);
		aq.id(R.id.login).clicked(this, "onLoginClicked");
		return v;
	}

	public void onLoginClicked(View button) {
		if (auth(aq.id(R.id.usrn).getText().toString(), aq.id(R.id.psw)
				.getText().toString())) {
			FragmentTransaction ft = this.getActivity().getFragmentManager()
					.beginTransaction();
			ft.hide(this);
			ft.commit();

			ft = this.getActivity().getFragmentManager().beginTransaction();
			ft.replace(R.id.host, new FunctionSelectorPage());
			ft.commit();

			ft = this.getActivity().getFragmentManager().beginTransaction();
			ft.remove(this);
			ft.commit();
		}
	}

	private boolean auth(String usn, String psw) {
		return true;
	}
}
