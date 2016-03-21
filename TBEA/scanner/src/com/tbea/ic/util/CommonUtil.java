package com.tbea.ic.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class CommonUtil {
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
	
	public static void hintAutoDisappear(EditText et){
		et.setOnFocusChangeListener(onHintAutoDisappearHandler);
	}
	
	public static String getVersion(Activity activity) {
		PackageInfo info = null;
		try {
			PackageManager manager = activity.getPackageManager();
			info = manager.getPackageInfo(activity.getPackageName(), 0);
			return info.versionName.substring(0, 3);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getDeviceId(Activity activity) {
		TelephonyManager tm = (TelephonyManager) activity
				.getSystemService(android.content.Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}

	public static void hideKeyboard(Activity activity){
		InputMethodManager imm = (InputMethodManager) activity
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		boolean isOpen = imm.isActive();
		if (isOpen && null != activity.getCurrentFocus()) {
			imm.hideSoftInputFromWindow(activity.getCurrentFocus()
					.getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
		
}
