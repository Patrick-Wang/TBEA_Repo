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
import com.webservice.Server.OnRegistered;

public class RegisterPage extends AQueryFragment implements OnRegistered {

	@Override
	protected void onViewPrepared(final AQuery aq, View fragView) {
		aq.id(R.id.register).clicked(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((LinearLayout) aq.id(R.id.newpasswrod).getView().getParent()).requestFocus();
				CommonUtil.hideKeyboard(getActivity());
				String newPasswrod = aq.id(R.id.newpasswrod).getText().toString();
				String confirmPassword = aq.id(R.id.confirmpassword).getText().toString();

				if (newPasswrod.equals(confirmPassword)) {
					String validationCode = aq.id(R.id.validationcode).getText().toString();
					getAQ().id(R.id.register).text("正在注册...").enabled(false);
					Server.getInstance().register(CommonUtil.getDeviceId(getActivity()), validationCode, newPasswrod,
							RegisterPage.this);
				} else {
					Toast.makeText(getActivity(), "确认密码错误", Toast.LENGTH_SHORT).show();
				}
			}
		});

		TextView tv = aq.id(R.id.help).getTextView();
		tv.setText(Html.fromHtml("<u>" + tv.getText() + "</u>"));
		tv.setTextColor(Color.BLUE);
		
		
		aq.id(R.id.help).clicked(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentTransaction ft = getActivity().getFragmentManager()
						.beginTransaction();
				CommonUtil.hideKeyboard(getActivity());
				HelpPage helpPage = new HelpPage();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.replace(R.id.host, helpPage);
				ft.addToBackStack(null);
				ft.commit();
			}
		});
		
		CommonUtil.hintAutoDisappear(aq.id(R.id.newpasswrod).getEditText());
		CommonUtil.hintAutoDisappear(aq.id(R.id.confirmpassword).getEditText());
		CommonUtil.hintAutoDisappear(aq.id(R.id.validationcode).getEditText());
	}

	@Override
	public View onLoadView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.register_page, container, false);
	}

	@Override
	public void onRegistered(Boolean ret) {
		if (null != ret) {
			if (ret.booleanValue()) {
				String newPasswrod = getAQ().id(R.id.newpasswrod).getText().toString();
				getAQ().id(R.id.register).text("正在登录...").enabled(false);
				Server.getInstance().login(CommonUtil.getDeviceId(getActivity()), newPasswrod,
						new OnLoginResponseListener() {

							private void enterLogin() {
								FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
								LoginPage loginPage = new LoginPage();
								ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
								ft.replace(R.id.host, loginPage);
								ft.commit();
							}

							@Override
							public void onLogin(UserBean userBean, AjaxStatus status) {
								getAQ().id(R.id.register).text("注册").enabled(true);
								if (null != userBean) {
									if (userBean.isLoginFlag()) {
										FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
										HomePage homePage = new HomePage();
										ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
										ft.replace(R.id.host, homePage);
										ft.commit();
									} else {
										Toast.makeText(getActivity(), "机器未授权或密码错误, 请重新登录", Toast.LENGTH_SHORT).show();
										enterLogin();
									}
								} else {
									Toast.makeText(getActivity(), "网络连接错误, 请重新登录", Toast.LENGTH_SHORT).show();
									enterLogin();
								}
							}
						});
			} else {
				getAQ().id(R.id.register).text("注册").enabled(true);
				Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(getActivity(), "网络连接错误", Toast.LENGTH_SHORT).show();
			getAQ().id(R.id.register).text("注册").enabled(true);
		}
	}
}
