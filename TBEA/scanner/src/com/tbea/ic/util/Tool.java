package com.tbea.ic.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Tool {

	private static String toHexadecimalString(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			sb.append(String.format("%x", b[i]));
		}
		return sb.toString();
	}

	public static String getMD5(String val) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(val.getBytes());
		byte[] m = md5.digest();
		return toHexadecimalString(m);
	}

	public static OnFocusChangeListener onHintAutoDisappearHandler = new OnFocusChangeListener() {
		public void onFocusChange(View v, boolean hasFocus) {
			EditText _v = (EditText) v;
			if (!hasFocus) {// 失去焦点
				_v.setHint(_v.getTag().toString());
			} else {
				String hint = _v.getHint().toString();
				_v.setTag(hint);
				_v.setHint("");
			}
		}
	};

	public static void hintAutoDisappear(EditText et) {
		et.setOnFocusChangeListener(onHintAutoDisappearHandler);
	}

	public static int getVersionCode(Activity activity) {
		PackageInfo info = null;
		try {
			PackageManager manager = activity.getPackageManager();
			info = manager.getPackageInfo(activity.getPackageName(), 0);
			return info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static String getDeviceId(Activity activity) {
		TelephonyManager tm = (TelephonyManager) activity
				.getSystemService(android.content.Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}

	public static void hideKeyboard(Activity activity) {
		InputMethodManager imm = (InputMethodManager) activity
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		boolean isOpen = imm.isActive();
		if (isOpen && null != activity.getCurrentFocus()) {
			imm.hideSoftInputFromWindow(activity.getCurrentFocus()
					.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

}
