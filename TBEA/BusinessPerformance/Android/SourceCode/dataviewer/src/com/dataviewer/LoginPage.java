package com.dataviewer;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.javaBean.UserBean;
import com.tbea.dataviewer.R;
import com.webservice.Server;
import com.webservice.Server.OnLoginResponseListener;

public class LoginPage extends AQueryFragment implements
		OnLoginResponseListener {

	@Override
	protected void onViewPrepared(final AQuery aq, View fragView) {

		SharedPreferences preferences = getActivity().getSharedPreferences(
				"user", Context.MODE_PRIVATE);
		aq.id(R.id.usrn).getEditText()
				.setText(preferences.getString("Email", null));
		aq.id(R.id.psw).getEditText()
				.setText(preferences.getString("Password", null));

		aq.id(R.id.login).clicked(new OnClickListener() {

			@Override
			public void onClick(View v) {
				auth(aq.id(R.id.usrn).getText().toString(), aq.id(R.id.psw)
						.getText().toString());
				InputMethodManager imm = (InputMethodManager) getActivity()
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				boolean isOpen = imm.isActive();
				if (isOpen && null != getActivity().getCurrentFocus()) {
					imm.hideSoftInputFromWindow(getActivity().getCurrentFocus()
							.getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
				}
			}
		});
	}

	@Override
	public View onLoadView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.loginpage, container, false);
	}

	public void auth(final String usn, final String psw) {
		SharedPreferences preferences = getActivity().getSharedPreferences(
				"user", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("Email", usn);
		editor.putString("Password", psw);
		editor.commit();
		Server server = Server.getInstance();
		server.login(usn, psw, this);
	}

	@Override
	public void onLogin(UserBean userBean, AjaxStatus status) {

		if (null != userBean) {
			if (userBean.isLoginFlag()) {
				FragmentTransaction ft = getActivity().getFragmentManager()
						.beginTransaction();
				HomePage homePage = new HomePage();
				ft.replace(R.id.host, homePage);
				ft.commit();

			} else {
				Toast.makeText(getActivity(), "用户名或密码错误", Toast.LENGTH_SHORT)
						.show();
			}
		} else {
			Toast.makeText(getActivity(), "网络连接错误", Toast.LENGTH_SHORT).show();
		}
	}
}
