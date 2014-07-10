package com.dataviewer;

import com.example.dataviewer.R;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class FuturesChartPage extends AQueryFragment implements
		OnCheckedChangeListener {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater
				.inflate(R.layout.futures_chart_page, container, false);
		update(v);
		((RadioGroup) aq.id(R.id.rg_tab_ac).getView())
				.setOnCheckedChangeListener(this);

		aq.id(R.id.detailbtn).clicked(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				FragmentTransaction ft = getActivity().getFragmentManager()
						.beginTransaction();
				ft.hide(FuturesChartPage.this);
				ft.replace(R.id.host, new FuturesTablePage()).addToBackStack(
						null);
				ft.commit();
			}
		});

		return v;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.cu:
			break;
		case R.id.al:
			break;
		default:
			break;
		}
	}

}
