package com.tbea.ic.scanner.page;

import java.security.NoSuchAlgorithmException;

import org.jdeferred.AlwaysCallback;
import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;
import org.jdeferred.Promise.State;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidquery.callback.AjaxStatus;
import com.page.core.Page;
import com.tbea.ic.scaner.R;
import com.tbea.ic.scanner.net.Server;
import com.tbea.ic.scanner.net.entity.User;
import com.tbea.ic.util.Tool;

public class LoginPage extends Page {

	@Override
	protected void onInitialize() {

		query(R.id.login).clicked(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((LinearLayout) query(R.id.psw).getView().getParent())
						.requestFocus();
				Tool.hideKeyboard(getActivity());
				try {
					auth(query(R.id.usrn).getText().toString(), query(R.id.psw)
							.getText().toString());
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
		});

		Tool.hintAutoDisappear(query(R.id.usrn).getEditText());
		Tool.hintAutoDisappear(query(R.id.psw).getEditText());

		SharedPreferences preferences = getActivity().getSharedPreferences(
				"user", Context.MODE_PRIVATE);
		query(R.id.usrn).text(preferences.getString("username", ""));
		query(R.id.psw).text(preferences.getString("password", ""));
	}

	public void auth(String usn, String psw) throws NoSuchAlgorithmException {
		query(R.id.login).text(getString(R.string.logining)).enabled(false);
		Server server = Server.getInstance();
		server.login(usn, psw).done(new DoneCallback<User>() {

			@Override
			public void onDone(User usr) {
				if (usr.getRights() != null) {
					SharedPreferences preferences = getActivity()
							.getSharedPreferences("user", Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = preferences.edit();
					editor.putString("username", usr.getUserid());
					editor.putString("password", usr.getPwd());
					editor.commit();
					leaveTo(new HomePage());
				} else {
					Toast.makeText(getActivity(),
							getString(R.string.loginfailed), Toast.LENGTH_SHORT)
							.show();
				}
			}

		}).fail(new FailCallback<AjaxStatus>() {

			@Override
			public void onFail(AjaxStatus arg0) {
				Toast.makeText(getActivity(),
						getString(R.string.networkerror) + arg0.getCode(),
						Toast.LENGTH_SHORT).show();
			}

		}).always(new AlwaysCallback<User, AjaxStatus>() {

			@Override
			public void onAlways(State state, User usr, AjaxStatus status) {
				query(R.id.login).text(getString(R.string.login)).enabled(true);

			}
		});
	}

	@Override
	protected int onGetLayoutId() {
		return R.layout.login;
	}
}
