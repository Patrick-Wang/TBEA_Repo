package com.example.dataviewer;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FunctionSelectorPage extends AQueryFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.function_selector_page, container, false);
		update(v);
		aq.id(R.id.f1).clicked(this, "onF1Clicked");
		aq.id(R.id.f2).clicked(this, "onF2Clicked");
		aq.id(R.id.f3).clicked(this, "onF3Clicked");
		return v;
	}

	public void onF1Clicked(View button) {
		System.out.println("onF1Clicked");
		FragmentTransaction ft = this.getActivity().getFragmentManager().beginTransaction();
		ft.hide(this);
		ft.replace(R.id.host, new FundsChartPage()).addToBackStack(null);
		
		ft.commit();
	}

	public void onF2Clicked(View button) {
		System.out.println("onF2Clicked");

		FragmentTransaction ft = this.getActivity().getFragmentManager().beginTransaction();
		ft.hide(this);
		ft.replace(R.id.host, new FuturesChartPage()).addToBackStack(null);
		ft.commit();
	}

	public void onF3Clicked(View button) {
		System.out.println("onF3Clicked");

		FragmentTransaction ft = this.getActivity().getFragmentManager().beginTransaction();
		ft.hide(this);
		ft.replace(R.id.host, new QuotaTablePage()).addToBackStack(null);
		ft.commit();
	}
}
