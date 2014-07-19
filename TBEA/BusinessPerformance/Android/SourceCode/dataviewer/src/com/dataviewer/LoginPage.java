package com.dataviewer;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.common.JsonUtil;
import com.javaBean.UserBean;
import com.tbea.dataviewer.R;

public class LoginPage extends AQueryFragment {

	private static String outerUrl = "http://218.84.134.160:8081/mobile/loginServlet";

	private static String innerUrl = "http://192.168.7.22/mobile/loginServlet";

	@Override
	protected void onViewPrepared(final AQuery aq, View fragView) {

		aq.id(R.id.login).clicked(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// String url = "http://192.168.7.22/mobile/loginServlet";
				auth(aq.id(R.id.usrn).getText().toString(), aq.id(R.id.psw)
						.getText().toString(), outerUrl);
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

	public void auth(final String usn, final String psw, final String url) {
		// String url = "http://192.168.7.22/mobile/loginServlet";

		Map<String, String> map = new HashMap<String, String>();

		map.put("username", usn);
		map.put("password", psw);
		mAq.ajax(url, map, JSONObject.class, new AjaxCallback<JSONObject>() {

			@Override
			public void callback(String urlret, JSONObject json,
					AjaxStatus status) {
				try {
					if (json != null) {
						UserBean userBean = (UserBean) JsonUtil.jsonToBean(
								json, UserBean.class);

						// successful ajax call, show status code and json
						// content
						if (userBean.isLoginFlag()) {

							FragmentTransaction ft = getActivity()
									.getFragmentManager().beginTransaction();
							HomePage homePage = new HomePage();
							homePage.setUserBean(userBean);
							ft.replace(R.id.host, homePage);
							ft.commit();
						} else {
							Toast.makeText(getActivity(), "用户名或密码错误",
									Toast.LENGTH_LONG).show();
						}
					} else {
						// ajax error, show error code
						if (urlret.equals(outerUrl)) {
							LoginPage.this.auth(usn, psw, innerUrl);
						} else {
							Toast.makeText(getActivity(), "网络连接错误，请检查您的网络",
									Toast.LENGTH_LONG).show();
						}
					}

				} catch (Exception e) {

				}
			}

		});
	}
}
