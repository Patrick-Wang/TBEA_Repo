package com.dataviewer;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.common.CommonUtil;
import com.tbea.dataviewer.R;
import com.webservice.Server;
import com.webservice.Server.OnPasswordChanged;
import com.webservice.Server.PasswrodChangeResponse;

public class SettingPage extends AQueryFragment {

	@Override
	protected void onViewPrepared(final AQuery aq, View fragView) {
		CommonUtil.hintAutoDisappear(aq.id(R.id.old_password).getEditText());
		CommonUtil.hintAutoDisappear(aq.id(R.id.new_password).getEditText());
		CommonUtil.hintAutoDisappear(aq.id(R.id.confirm_password).getEditText());

		aq.id(R.id.updatepassword).clicked(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String oldPassword = aq.id(R.id.old_password).getText().toString();
				String newPassword = (String) aq.id(R.id.new_password).getText().toString();
				String confirmPassword = (String) aq.id(R.id.confirm_password).getText().toString();

				((LinearLayout) aq.id(R.id.old_password).getView().getParent()).requestFocus();

				CommonUtil.hideKeyboard(getActivity());

				if (validate(oldPassword, newPassword, confirmPassword)) {
					final Dialog dialog = ProgressDialog.show(getActivity(), null, "正在修改密码，请稍侯...");
					Server.getInstance().changePassword(CommonUtil.getDeviceId(getActivity()), oldPassword, newPassword, new OnPasswordChanged() {

						@Override
						public void onPasswordChanged(PasswrodChangeResponse result) {
							dialog.hide();
							if (result != null) {
								if (Server.PasswrodChangeResponse.SUCCESS == result) {
									Toast.makeText(getActivity(), "修改密码成功", Toast.LENGTH_SHORT).show();
									getActivity().getFragmentManager().popBackStackImmediate();
								} else if (Server.PasswrodChangeResponse.FAILED == result) {
									getAQ().id(R.id.tips).text("密码修改失败！");
									getAQ().id(R.id.tips).textColor(Color.RED);
								} else if (Server.PasswrodChangeResponse.OLD_PASSWORD_ERROR == result) {
									getAQ().id(R.id.tips).text("机器未授权或密码错误！");
									getAQ().id(R.id.tips).textColor(Color.RED);
								}
							} else {
								getAQ().id(R.id.tips).text("网络连接错误！");
								getAQ().id(R.id.tips).textColor(Color.RED);
							}
						}
					});
				}
			}
		});
	}

	protected boolean validate(String oldPassword, String newPassword, String confirmPassword) {
		if ("".equals(oldPassword)) {
			getAQ().id(R.id.tips).text("旧密码不能为空");
			getAQ().id(R.id.tips).textColor(Color.RED);
			return false;
		}

		if ("".equals(newPassword)) {
			getAQ().id(R.id.tips).text("新密码不能为空");
			getAQ().id(R.id.tips).textColor(Color.RED);
			return false;
		}

		if ("".equals(confirmPassword)) {
			getAQ().id(R.id.tips).text("确认密码不能为空");
			getAQ().id(R.id.tips).textColor(Color.RED);
			return false;
		}

		if (!newPassword.equals(confirmPassword)) {
			getAQ().id(R.id.tips).text("确认密码不正确");
			getAQ().id(R.id.tips).textColor(Color.RED);
			return false;
		}

		return true;
	}

	@Override
	public View onLoadView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.setting_page, container, false);
	}

}
