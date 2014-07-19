package com.dataviewer;

import com.androidquery.AQuery;
import com.javaBean.UserBean;
import com.tbea.dataviewer.R;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class HomePage extends AQueryFragment {

	public UserBean userBean = new UserBean();

	@Override
	public View onLoadView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.home_page, null);
	}

	@Override
	protected void onViewPrepared(AQuery aq, View fragView) {
		
		if (userBean.getMenuqx().contains("1"))
		{
			aq.id(R.id.f1).clicked(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					System.out.println("onF1Clicked");
					FragmentTransaction ft = getActivity().getFragmentManager()
							.beginTransaction();
					ft.replace(R.id.host, new FundsChartPage())
							.addToBackStack(null);
					ft.commit();
				}

			});
		}
		else{
			aq.id(R.id.f1t).getTextView().setTextColor(Color.GRAY);
			aq.id(R.id.f1).getView().setEnabled(false);
		}
		
		if (userBean.getMenuqx().contains("2"))
		{
			aq.id(R.id.f2).clicked(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					System.out.println("onF2Clicked");
					FragmentTransaction ft = getActivity().getFragmentManager()
							.beginTransaction();
					ft.replace(R.id.host, new FuturesChartPage()).addToBackStack(
							null);
					ft.commit();
				}

			});
		}
		else{
			aq.id(R.id.f2t).getTextView().setTextColor(Color.GRAY);
			aq.id(R.id.f2).getView().setEnabled(false);
		}
			
		if (userBean.getMenuqx().contains("3"))
		{
			aq.id(R.id.f3).clicked(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					System.out.println("onF3Clicked");

					FragmentTransaction ft = getActivity().getFragmentManager()
							.beginTransaction();
					ft.replace(R.id.host, new QuotaTablePage())
							.addToBackStack(null);
					ft.commit();
				}

			});
		}
		else{
			aq.id(R.id.f3t).getTextView().setTextColor(Color.GRAY);
			aq.id(R.id.f3).getView().setEnabled(false);
		}
		
		
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
