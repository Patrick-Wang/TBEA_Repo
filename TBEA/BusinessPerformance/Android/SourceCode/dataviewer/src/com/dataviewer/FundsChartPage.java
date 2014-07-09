package com.dataviewer;

import com.example.dataviewer.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class FundsChartPage extends AQueryFragment implements
OnCheckedChangeListener{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.funds_chart_page, container, false);
		update(v);
		((RadioGroup) aq.id(R.id.rg_tab).getView())
		.setOnCheckedChangeListener(this);
		return v;
	}
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.moneyback:
			break;
		case R.id.day_signed:
			break;
		case R.id.month_sigend:
			break;
		default:
			break;
		}
	}
}