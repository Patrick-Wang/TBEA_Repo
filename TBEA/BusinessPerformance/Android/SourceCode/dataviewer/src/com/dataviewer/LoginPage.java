package com.dataviewer;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.common.CommonUtil;
import com.javaBean.UserBean;
import com.tbea.dataviewer.R;
import com.webservice.Server;
import com.webservice.Server.OnLoginResponseListener;

public class LoginPage extends AQueryFragment implements
		OnLoginResponseListener {

//	String convert(String val, int count) {
//		String result = "";
//		for (int i = count * 2; i < val.length(); ++i) {
//			result += "*";
//		}
//
//		result = val.substring(0, count) + result
//				+ val.substring(val.length() - count, val.length());
//		return result;
//	}

	@Override
	protected void onViewPrepared(final AQuery aq, View fragView) {

//		final String deviceId = CommonUtil.getDeviceId(getActivity());
//		String result = "";
//
//		if (deviceId.length() > 8) {
//			result = convert(deviceId, 4);
//		} else {
//			result = convert(deviceId, 2);
//		}

		aq.id(R.id.version).text(
				"信息资源管理中心 V" + CommonUtil.getVersion(getActivity()) + "版本");

		aq.id(R.id.usrn).enabled(false);
		aq.id(R.id.usrn).getEditText().setText(Server.getInstance().getUserName());
		aq.id(R.id.usrn).getEditText().setTextColor(Color.LTGRAY);
		aq.id(R.id.login).clicked(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((LinearLayout) aq.id(R.id.psw).getView().getParent())
				.requestFocus();
				CommonUtil.hideKeyboard(getActivity());
				auth(CommonUtil.getDeviceId(getActivity()), aq.id(R.id.psw).getText().toString());
			}
		});

		TextView tv = aq.id(R.id.changepassword).getTextView();
		tv.setText(Html.fromHtml("<u>" + tv.getText() + "</u>"));
		tv.setTextColor(Color.BLUE);

		tv = aq.id(R.id.help).getTextView();
		tv.setText(Html.fromHtml("<u>" + tv.getText() + "</u>"));
		tv.setTextColor(Color.BLUE);
		
		aq.id(R.id.changepassword).clicked(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CommonUtil.hideKeyboard(getActivity());
				FragmentTransaction ft = getActivity().getFragmentManager()
						.beginTransaction();
				SettingPage settingPage = new SettingPage();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.replace(R.id.host, settingPage);
				ft.addToBackStack(null);
				ft.commit();
			}
		});
		
		aq.id(R.id.help).clicked(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CommonUtil.hideKeyboard(getActivity());
				FragmentTransaction ft = getActivity().getFragmentManager()
						.beginTransaction();
				HelpPage helpPage = new HelpPage();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.replace(R.id.host, helpPage);
				ft.addToBackStack(null);
				ft.commit();
			}
		});
		
		CommonUtil.hintAutoDisappear(aq.id(R.id.usrn).getEditText());
		CommonUtil.hintAutoDisappear(aq.id(R.id.psw).getEditText());

	}

	@Override
	public View onLoadView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.loginpage, container, false);
	}

	public void auth(final String usn, final String psw) {
		// SharedPreferences preferences = getActivity().getSharedPreferences(
		// "user", Context.MODE_PRIVATE);
		// SharedPreferences.Editor editor = preferences.edit();
		// editor.putString("Email", usn);
		// editor.putString("Password", psw);
		// editor.commit();

		getAQ().id(R.id.login).text("登录中...").enabled(false);

		Server server = Server.getInstance();
		server.login(usn, psw, this);
	}

	@Override
	public void onLogin(UserBean userBean, AjaxStatus status) {
		getAQ().id(R.id.login).text("登录").enabled(true);
		if (null != userBean) {
			if (userBean.isLoginFlag()) {
				FragmentTransaction ft = getActivity().getFragmentManager()
						.beginTransaction();
				HomePage homePage = new HomePage();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.replace(R.id.host, homePage);
				ft.commit();

			} else {
				Toast.makeText(getActivity(), "机器未授权或密码错误", Toast.LENGTH_SHORT)
						.show();
			}
		} else {
			Toast.makeText(getActivity(), "网络连接错误", Toast.LENGTH_SHORT).show();
		}
	}
}
