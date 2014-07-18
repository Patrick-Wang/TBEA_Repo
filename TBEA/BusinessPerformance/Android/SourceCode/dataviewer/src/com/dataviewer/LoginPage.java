package com.dataviewer;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
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
				auth(aq.id(R.id.usrn).getText().toString(), aq.id(R.id.psw)
						.getText().toString());
			}
		});
	}

	@Override
	public View onLoadView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.loginpage, container, false);
	}

	private void auth(String usn, String psw) {
		String url = "http://218.84.134.160:8081/mobile/loginServlet";
		try {

			Map<String, String> map = new HashMap<String, String>();

			map.put("username", "admin");
			map.put("password", "123");
			long id1 = Thread.currentThread().getId();
			mAq.ajax(url, map, JSONObject.class,
					new AjaxCallback<JSONObject>() {

						@Override
						public void callback(String url, JSONObject json,
								AjaxStatus status) {
							long id = Thread.currentThread().getId();
							if (json != null) {
								// successful ajax call, show status code and
								// json content
								FragmentTransaction ft = getActivity()
										.getFragmentManager()
										.beginTransaction();
								ft.replace(R.id.host, new HomePage());
								ft.commit();
							} else {
								// ajax error, show error code
								// Toast.makeText(aq.getContext(), "Error:" +
								// status.getCode(), Toast.LENGTH_LONG).show();
							}
						}

					});

			// AsyncHttpClient client = new AsyncHttpClient();
			// RequestParams param = new RequestParams();
			// param.add("username", "admin");
			// param.add("password", "123");
			// client.post(url, param, new TextHttpResponseHandler() {
			//
			// @Override
			// public void onFailure(int arg0, Header[] arg1, String arg2,
			// Throwable arg3) {
			// Toast.makeText(getActivity(), "网络异常",
			// Toast.LENGTH_SHORT).show();
			// }
			//
			// @SuppressWarnings("unused")
			// @Override
			// public void onSuccess(int arg0, Header[] arg1, String arg2) {
			//
			// try {
			// JSONObject json = new JSONObject(arg2);
			// int k = 0;
			// ++k;
			// } catch (JSONException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			//
			// FragmentTransaction ft = getActivity().getFragmentManager()
			// .beginTransaction();
			// ft.replace(R.id.host, new HomePage());
			// ft.commit();
			//
			// }
			//
			// });

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
