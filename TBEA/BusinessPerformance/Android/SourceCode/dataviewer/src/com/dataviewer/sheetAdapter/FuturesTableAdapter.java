package com.dataviewer.sheetAdapter;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;

import com.excel.ContentLinearLayout;
import com.excel.Sheet;
import com.excel.Sheet.SheetArea;

public class FuturesTableAdapter extends GreenCellAdapter {

	Paint grayBKPaint = null;
	Paint lightGrayBKPaint = null;

	public FuturesTableAdapter() {
		super();
		grayBKPaint = new Paint(Paint.DITHER_FLAG);
		grayBKPaint.setColor(Color.GRAY);

		lightGrayBKPaint = new Paint(Paint.DITHER_FLAG);
		lightGrayBKPaint.setColor(Color.LTGRAY);
	}

	@Override
	public ContentLinearLayout getContent(LayoutInflater inflater, Sheet sheet,
			SheetArea sheetArea, int index) {

		ContentLinearLayout cll = super.getContent(inflater, sheet, sheetArea,
				index);
		if (index % 2 == 0) {
			cll.setBKPaint(grayBKPaint);
		} else {
			cll.setBKPaint(lightGrayBKPaint);
		}

		return cll;
	}

}
