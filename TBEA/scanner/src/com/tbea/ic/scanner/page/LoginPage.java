package com.tbea.ic.scanner.page;

import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.page.core.Page;
import com.tbea.ic.scaner.R;
import com.tbea.ic.scanner.net.Server;
import com.tbea.ic.scanner.net.bean.User;
import com.tbea.ic.util.CommonUtil;

public class LoginPage extends Page {


	@Override
	protected void onInitialize() {

		final AQuery aq = query();
		aq.id(R.id.login).clicked(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((LinearLayout) aq.id(R.id.psw).getView().getParent()).requestFocus();
				CommonUtil.hideKeyboard(getActivity());
				auth(aq.id(R.id.usrn).getText().toString(), aq.id(R.id.psw).getText().toString());
			}
		});		
		
		CommonUtil.hintAutoDisappear(aq.id(R.id.usrn).getEditText());
		CommonUtil.hintAutoDisappear(aq.id(R.id.psw).getEditText());

	}

	public void auth(final String usn, final String psw) {
		// SharedPreferences preferences = getActivity().getSharedPreferences(
		// "user", Context.MODE_PRIVATE);
		// SharedPreferences.Editor editor = preferences.edit();
		// editor.putString("Email", usn);
		// editor.putString("Password", psw);
		// editor.commit();

		query().id(R.id.login).text("登录中...").enabled(false);

		Server server = Server.getInstance();
		server.login(usn, psw).done(new DoneCallback<User>() {

			@Override
			public void onDone(User usr) {
				if(null != usr){
					leaveTo(new HomePage());
				}else{
					Toast.makeText(getActivity(), "机器未授权或密码错误", Toast.LENGTH_SHORT)
					.show();
				}
			}

		}).fail(new FailCallback<AjaxStatus>() {

			@Override
			public void onFail(AjaxStatus arg0) {
				Toast.makeText(getActivity(), "网络连接错误", Toast.LENGTH_SHORT).show();
			}

		});
	}

	@Override
	protected int onGetLayoutId() {
		return R.layout.login;
	}
}
