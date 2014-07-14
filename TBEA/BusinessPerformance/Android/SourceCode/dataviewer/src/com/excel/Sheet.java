package com.excel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.ctrl.AssociationHorizontalScrollView;
import com.ctrl.AssociationVerticalScrollView;
import com.ctrl.OnScrollFinished;
import com.example.dataviewer.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
class DensityUtil { 

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */ 
    public static int dip2px(Context context, float dpValue) { 
        final float scale = context.getResources().getDisplayMetrics().density; 
        return (int) (dpValue * scale + 0.5f); 
    } 

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */ 
    public static int px2dip(Context context, float pxValue) { 
        final float scale = context.getResources().getDisplayMetrics().density; 
        return (int) (pxValue / scale + 0.5f); 
    } 
}  
public class Sheet extends LinearLayout implements OnScrollFinished {

	private LinearLayout lh_title_row = null;
	private LinearLayout lv_independent_title = null;
	private LinearLayout lv_title_colum = null;
	private ContentLinearLayout lv_content = null;

	private LinearLayout last_content = null;
	private LinearLayout last_title_row = null;
	private LinearLayout last_title_colum = null;

	private int sumWidth = 0;
	private int sumHeight = 0;
	private int columCount = 0;
	private int rowCount = 0;
	private List<Integer> columWidth = new LinkedList<Integer>();
	private List<Integer> rowHeight = new LinkedList<Integer>();
	private LayoutInflater inflater = null;
	static Paint paint = null;// ����һ������

	final public static int blank_row_width = 2000;
	final public static int blank_row_height = 2000;

	public static void setEdgeColor(int color) {
		getEdgePaint().setColor(color);
	}

	public static Paint getEdgePaint() {
		if (paint == null) {
			paint = new Paint(Paint.DITHER_FLAG);// ����һ������
			paint.setColor(Color.BLACK);// ����Ϊ���
		}
		return paint;
	}

	public Sheet(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public Sheet(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Sheet(Context context) {
		super(context);
		init();
	}

	private void init() {
		this.setWillNotDraw(false);// ����
		inflater = LayoutInflater.from(getContext());
		addView(inflater.inflate(R.layout.title, null));
		addView(inflater.inflate(R.layout.body, null));
		lh_title_row = (LinearLayout) findViewById(R.id.title_row);
		lv_independent_title = (LinearLayout) findViewById(R.id.independent_title);
		lv_title_colum = (LinearLayout) findViewById(R.id.title_colum);
		lv_content = (ContentLinearLayout) findViewById(R.id.content);
		last_content = new LinearLayout(this.getContext());
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		last_content.setVisibility(VISIBLE);
		last_content.setLayoutParams(param);
		lv_content.addView(last_content);
		lv_content.setSheet(this);

		last_title_colum = new LinearLayout(this.getContext());
		param = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		last_title_colum.setVisibility(VISIBLE);
		last_title_colum.setLayoutParams(param);
		lv_title_colum.addView(last_title_colum);

		last_title_row = new LinearLayout(this.getContext());
		param = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		last_title_row.setVisibility(VISIBLE);
		last_title_row.setLayoutParams(param);
		lh_title_row.addView(last_title_row);

		AssociationHorizontalScrollView title_row_scr = (AssociationHorizontalScrollView) findViewById(R.id.title_row_scr);
		AssociationHorizontalScrollView content_hscr = (AssociationHorizontalScrollView) findViewById(R.id.content_hscr);
		title_row_scr.association(content_hscr);
		content_hscr.association(title_row_scr);
		title_row_scr.setOnScrollFinished(this);
		content_hscr.setOnScrollFinished(this);
		AssociationVerticalScrollView title_colum_scr = (AssociationVerticalScrollView) findViewById(R.id.title_colum_vscr);
		AssociationVerticalScrollView content_vscr = (AssociationVerticalScrollView) findViewById(R.id.content_vscr);
		title_colum_scr.association(content_vscr);
		content_vscr.association(title_colum_scr);
		content_vscr.setOnScrollFinished(this);
		title_colum_scr.setOnScrollFinished(this);
	}

	public List<Integer> getColumWidth() {
		return columWidth;
	}

	public List<Integer> getRowHeight() {
		return rowHeight;
	}

	public CellTextView cell(int row, int colum) {
		View ret = null;
		if (row == 0 && colum == 0) {
			ret = lv_independent_title.getChildAt(0);
		} else if (colum == 0) {
			ret = lv_title_colum.getChildAt(row - 1);
		} else if (row == 0) {
			ret = lh_title_row.getChildAt(colum - 1);
		} else {
			ret = ((LinearLayout) lv_content.getChildAt(row - 1))
					.getChildAt(colum - 1);
		}
		return (CellTextView) ret;
	}

	public int getColumCount() {
		return columCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	private void updateTableSize(List<Integer> tmpColumWidth,
			List<Integer> tmpRowHeight) {

		for (int i = 0, len = tmpColumWidth.size(); i < len; ++i) {
			if (!columWidth.isEmpty() && columWidth.size() >= (i + 1)) {
				if (columWidth.get(i) < tmpColumWidth.get(i)) {
					sumWidth = sumWidth - columWidth.get(i)
							+ tmpColumWidth.get(i);
					columWidth.set(i, tmpColumWidth.get(i));
				}
			} else {
				columWidth.add(tmpColumWidth.get(i));
				sumWidth += tmpColumWidth.get(i);
			}
		}

		for (int i = 0, len = tmpRowHeight.size(); i < len; ++i) {
			if (!rowHeight.isEmpty() && rowHeight.size() >= (i + 1)) {
				if (rowHeight.get(i) < tmpRowHeight.get(i)) {
					sumHeight = sumHeight - rowHeight.get(i)
							+ tmpRowHeight.get(i);
					rowHeight.set(i, tmpRowHeight.get(i));

				}
			} else {
				rowHeight.add(tmpRowHeight.get(i));
				sumHeight += tmpRowHeight.get(i);
			}
		}
	}

	private void updateSizeList(View view, int row, int colum,
			List<Integer> tmpColumWidth, List<Integer> tmpRowHeight) {
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		tmpRowHeight.set(row, view.getMeasuredHeight());
		tmpColumWidth.set(colum, view.getMeasuredWidth());
	}

	public int getColumWidth(int i) {
		return columWidth.get(i);
	}

	public int getRowHeight(int i) {
		return rowHeight.get(i);
	}

	public int getContentWidth() {
		return sumWidth - columWidth.get(0);
	}

	public int getContentHeight() {
		return sumHeight - rowHeight.get(0);
	}

	public void clean() {
		lh_title_row.removeAllViews();
		lv_independent_title.removeAllViews();
		lv_title_colum.removeAllViews();
		lv_content.removeAllViews();
		last_content.removeAllViews();
		last_title_colum.removeAllViews();
		last_title_row.removeAllViews();
		last_content.removeAllViews();
		sumWidth = 0;
		sumHeight = 0;
		columCount = 0;
		rowCount = 0;
		columWidth = new LinkedList<Integer>();
		rowHeight = new LinkedList<Integer>();
	}

	public void fix() {

		last_content.getLayoutParams().height = blank_row_height;
		last_title_colum.getLayoutParams().height = blank_row_height;
		last_title_row.getLayoutParams().width = blank_row_width;
		last_content.getLayoutParams().width = getContentWidth()
				+ blank_row_width;

		for (int i = 0; i < getRowCount(); ++i) {
			for (int j = 0; j < getColumCount(); ++j) {
				CellTextView view = cell(i, j);
				view.getLayoutParams().width = columWidth.get(j);
				view.getLayoutParams().height = rowHeight.get(i);
			}
		}
	}

	public boolean AddRecord(String[] record) {

		if (columCount == 0) {
			columCount = record.length;
		}

		if (record.length != columCount) {
			return false;
		}

		CellTextView tv = null;
		++rowCount;
		List<Integer> tmpColumWidth = new ArrayList<Integer>(columCount);
		List<Integer> tmpRowHeight = new ArrayList<Integer>(rowCount);
		for (int i = 0; i < columCount; ++i) {
			tmpColumWidth.add(0);
		}
		for (int i = 0; i < rowCount; ++i) {
			tmpRowHeight.add(0);
		}

		tv = (CellTextView) inflater.inflate(R.layout.cell, null);
		tv.setText(record[0]);
		if (lv_independent_title.getChildCount() == 0) {
			lv_independent_title.addView(tv);
			tv.adjust(1, 1, 1, 1);
			tv.setTextColor(Color.WHITE);
			tv.setBKColor(Color.BLACK);
			//tv.setPadding(2, DensityUtil.dip2px(this.getContext(), 10), 2, DensityUtil.dip2px(getContext(), 10));
		} else {
			lv_title_colum.addView(tv, lv_title_colum.getChildCount() - 1);
			tv.adjust(1, 0, 1, 1);
		}
		updateSizeList(tv, rowCount - 1, 0, tmpColumWidth, tmpRowHeight);

		if (rowCount == 1) {
			for (int i = 1, len = record.length; i < len; ++i) {
				tv = (CellTextView) inflater.inflate(R.layout.cell, null);
				tv.setText(record[i]);
				tv.setTextColor(Color.WHITE);
				updateSizeList(tv, rowCount - 1, i, tmpColumWidth, tmpRowHeight);
				lh_title_row.addView(tv, lh_title_row.getChildCount() - 1);
				tv.adjust(0, 1, 1, 1);
				tv.setBKColor(Color.BLACK);
				//tv.setPadding(2, DensityUtil.dip2px(this.getContext(), 10), 2, DensityUtil.dip2px(getContext(), 10));

			}
		} else {
			LinearLayout ll = new ContentLineLinearLayout(getContext());
			ll.setOrientation(LinearLayout.HORIZONTAL);
			LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			ll.setLayoutParams(param);

			LinearLayout llfill = new LinearLayout(this.getContext());
			llfill.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT));

			ll.addView(llfill);
			lv_content.addView(ll, lv_content.getChildCount() - 1);

			for (int i = 1, len = record.length; i < len; ++i) {
				tv = (CellTextView) inflater.inflate(R.layout.cell, null);
				tv.setText(record[i]);
				updateSizeList(tv, rowCount - 1, i, tmpColumWidth, tmpRowHeight);
				ll.addView(tv, ll.getChildCount() - 1);
				tv.adjust(0, 0, 1, 1);
			}
		}

		updateTableSize(tmpColumWidth, tmpRowHeight);

		return true;
	}

	public void onScrollFinished(View scroll) {
		if (scroll instanceof AssociationVerticalScrollView) {
			int scrollY = (int) scroll.getScrollY();
			if (scrollY > 0) {
				int sumHeight = 0;
				int cellHeight = 0;

				if (scrollY > getContentHeight()) {
					scroll.scrollTo(scroll.getScrollX(), getContentHeight()
							- getRowHeight(rowCount - 1));
				} else {
					for (int i = 1; i < rowCount; ++i) {
						cellHeight = getRowHeight(i);
						sumHeight += cellHeight;
						if (sumHeight > scrollY) {
							if (cellHeight / 2 < (sumHeight - scrollY)
									|| (i + 1) == rowCount) {
								scroll.scrollTo(scroll.getScrollX(), sumHeight
										- cellHeight);
							} else {
								scroll.scrollTo(scroll.getScrollX(), sumHeight);
							}
							break;
						}
					}
				}

			}
		} else {
			int scrollX = (int) scroll.getScrollX();
			if (scrollX > 0) {
				int sumWidth = 0;
				int cellWidth = 0;

				if (scrollX > getContentWidth()) {
					scroll.scrollTo(getContentWidth()
							- getColumWidth(columCount - 1),
							scroll.getScrollY());
				} else {
					for (int i = 1; i < columCount; ++i) {
						cellWidth = getColumWidth(i);
						sumWidth += cellWidth;
						if (sumWidth > scrollX) {
							if (cellWidth / 2 < (sumWidth - scrollX)
									|| (i + 1) == columCount) {
								scroll.scrollTo(sumWidth - cellWidth,
										scroll.getScrollY());
							} else {
								scroll.scrollTo(sumWidth, scroll.getScrollY());
							}
							break;
						}
					}
				}

			}
		}
	}

}
