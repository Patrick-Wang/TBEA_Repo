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

	private int responseCount = 0;
	private int successCount = 0;
	private boolean user_psw_error = false;
	private ProgressDialog dialog = null;

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
		responseCount = 0;
		successCount = 0;
		user_psw_error = false;
		SharedPreferences preferences = getActivity().getSharedPreferences(
				"user", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("Email", usn);
		editor.putString("Password", psw);
		editor.commit();
		Server server = Server.getInstance();
		server.login_outer(usn, psw, this);
		server.login_inner(usn, psw, this);
		dialog = ProgressDialog.show(getActivity(), null, "正在登陆");
	}

	@Override
	public void onLogin(UserBean userBean, AjaxStatus status) {
		++responseCount;
		if (null != userBean) {
			if (userBean.isLoginFlag()) {
				++successCount;

			} else {
				user_psw_error = true;
			}
		}

		if (responseCount == 2) {
			dialog.hide();
			if (successCount > 0) {
				FragmentTransaction ft = getActivity().getFragmentManager()
						.beginTransaction();
				HomePage homePage = new HomePage();
				//homePage.setUserBean(userBean);
				ft.replace(R.id.host, homePage);
				ft.commit();
			} else if (user_psw_error) {
				Toast.makeText(getActivity(), "用户名或密码错误", Toast.LENGTH_SHORT)
						.show();
			} else {
				Toast.makeText(getActivity(), "网络连接错误", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}
}
