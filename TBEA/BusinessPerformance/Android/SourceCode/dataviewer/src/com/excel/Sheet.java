package com.excel;

import java.util.HashSet;
import java.util.Set;

import com.ctrl.AssociationHorizontalScrollView;
import com.ctrl.AssociationVerticalScrollView;
import com.ctrl.OnScrollFinished;
import com.excel.SizeManager.SizeChangeObserver;
import com.tbea.dataviewer.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
	public enum SheetArea {
		Independent, Row_title, Colum_title, Content
	}

	public int getLockRowCount() {
		return lockRowCount;
	}

	public int getLockColumCount() {
		return lockColumCount;
	}

	public interface Adapter {
		CellTextView getCell(LayoutInflater inflater, Sheet Sheet, int row,
				int colum, String text);

		ContentLinearLayout getContent(LayoutInflater inflater, Sheet Sheet,
				SheetArea sheetArea, int index);

		void adjustWidth(Sheet sheet, CellTextView cell, int row, int colum,
				int width);

		void adjustHeight(Sheet sheet, CellTextView cell, int row, int colum,
				int height);

		CellTextView cellAt(Sheet sheet, int row, int colum);
	}

	private Adapter adapter = new StandardAdapter();
	private LinearLayout lv_title_row = null;
	private LinearLayout lv_independent_title = null;
	private LinearLayout lv_title_colum = null;
	private LinearLayout lv_content = null;

	private LinearLayout last_content = null;
	private LinearLayout last_title_row = null;
	private LinearLayout last_title_colum = null;

	private SizeManager sizeManager = new SizeManager();

	private int lockRowCount = 0;
	private int lockColumCount = 0;
	private LayoutInflater inflater = null;
	static Paint paint = null;

	final public static int blank_row_width = 2000;
	final public static int blank_row_height = 2000;

	public void setAdapter(Adapter adapter) {
		this.adapter = adapter;
	}

	public SizeManager getSizeManager() {
		return sizeManager;
	}

	public static void setEdgeColor(int color) {
		getEdgePaint().setColor(color);
	}

	public static Paint getEdgePaint() {
		if (paint == null) {
			paint = new Paint(Paint.DITHER_FLAG);
			paint.setColor(Color.BLACK);
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

	public void lockColum(int count) {
		this.lockColumCount = count;
	}

	public void lockRow(int count) {
		this.lockRowCount = count;
	}


	private void init() {
		this.setWillNotDraw(false);// ����
		inflater = LayoutInflater.from(getContext());
		addView(inflater.inflate(R.layout.title, null));
		addView(inflater.inflate(R.layout.body, null));
		lv_title_row = (LinearLayout) findViewById(R.id.title_row);
		lv_independent_title = (LinearLayout) findViewById(R.id.independent_title);
		lv_title_colum = (LinearLayout) findViewById(R.id.title_colum);
		lv_content = (LinearLayout) findViewById(R.id.content);
		last_content = new LinearLayout(this.getContext());
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		last_content.setVisibility(VISIBLE);
		last_content.setLayoutParams(param);
		lv_content.addView(last_content);

		last_title_colum = new LinearLayout(this.getContext());
		param = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		last_title_colum.setVisibility(VISIBLE);
		last_title_colum.setLayoutParams(param);
		lv_title_colum.addView(last_title_colum);

		last_title_row = new LinearLayout(this.getContext());
		param = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		last_title_row.setVisibility(VISIBLE);
		last_title_row.setLayoutParams(param);
		lv_title_row.addView(last_title_row);

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

	
	public LinearLayout getSheetLayout(SheetArea area) {
		if (SheetArea.Independent == area) {
			return lv_independent_title;
		} else if (SheetArea.Colum_title == area) {
			return lv_title_colum;
		} else if (SheetArea.Content == area) {
			return lv_content;
		} else{
			return lv_title_row;
		}
	}
	
	public CellTextView cell(int row, int colum) {
		return (CellTextView) adapter.cellAt(this, row, colum);
	}

	private void updateSize(View view, int row, int colum) {

		ViewGroup.LayoutParams param = view.getLayoutParams();
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		sizeManager.setColum(colum, view.getMeasuredWidth());
		sizeManager.setRow(row, view.getMeasuredHeight());
	}

	public int getContentWidth() {
		int lockWidth = 0;
		for (int i = 0; i < this.lockColumCount; ++i) {
			lockWidth += sizeManager.getWidth(i);
		}

		return sizeManager.getWidth() - lockWidth;
	}

	public int getContentHeight() {
		int lockHeight = 0;
		for (int i = 0; i < this.lockRowCount; ++i) {
			lockHeight += sizeManager.getHeight(i);
		}

		return sizeManager.getHeight() - lockHeight;

	}

	public void clean() {
		lv_title_row.removeAllViews();
		lv_independent_title.removeAllViews();
		lv_title_colum.removeAllViews();
		lv_content.removeAllViews();
		last_content.removeAllViews();
		last_title_colum.removeAllViews();
		last_title_row.removeAllViews();
		last_content.removeAllViews();
		sizeManager = new SizeManager();
		lv_content.addView(last_content);
		lv_title_colum.addView(last_title_colum);
		lv_title_row.addView(last_title_row);
	}

	private void commit(Set<Integer> changedColum, Set<Integer> changedRow,
			int start) {

		last_content.getLayoutParams().height = blank_row_height;
		last_title_colum.getLayoutParams().height = blank_row_height;
		last_title_row.getLayoutParams().width = getContentWidth()
				+ blank_row_width;
		last_content.getLayoutParams().width = getContentWidth()
				+ blank_row_width;

		final Set<Integer> changedNewColum = new HashSet<Integer>();
		final Set<Integer> changedNewRow = new HashSet<Integer>();

		sizeManager.registerObserver(new SizeChangeObserver() {

			@Override
			public void onWidthChanged(SizeManager mgr, int colum, int newSize) {
				changedNewColum.add(colum);
			}

			@Override
			public void onHeightChanged(SizeManager mgr, int row, int newSize) {
				changedNewRow.add(row);
			}
		});

		for (Integer colum : changedColum) {
			int width = sizeManager.getWidth(colum);
			for (int row = start - 1; row >= 0; --row) {
				adapter.adjustWidth(this, cell(row, colum), row, colum, width);
			}
		}

		for (Integer row : changedRow) {
			int height = sizeManager.getHeight(row);
			for (int colum = sizeManager.getColumCount() - 1; colum >= 0; --colum) {
				adapter.adjustHeight(this, cell(row, colum), row, colum, height);
			}
		}

		for (int row = sizeManager.getRowCount() - 1; row >= start; --row) {
			for (int colum = sizeManager.getColumCount() - 1; colum >= 0; --colum) {
				adapter.adjustWidth(this, cell(row, colum), row, colum,
						sizeManager.getWidth(colum));
			}
		}

		if (!changedNewColum.isEmpty() || !changedNewRow.isEmpty()) {
			commit(changedNewColum, changedNewRow, start);
		}

	}

	private boolean validate(TableSource tableBlock) {
		if (sizeManager.isActive()) {
			if (tableBlock.getColumCount() != sizeManager.getColumCount()) {
				return false;
			}
		} else {
			sizeManager.active(tableBlock.getColumCount());
		}
		return true;
	}

	private ContentLinearLayout createContentLineLayout(SheetArea sheetArea,
			int index) {
		ContentLinearLayout ll = adapter.getContent(inflater, this, sheetArea,
				index);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		ll.setLayoutParams(param);
		ll.setLinePos(sheetArea, index);
		return ll;
	}

	private void addRecord(TableSource tableBlock, int row) {
		ContentLinearLayout unlockedLine = null;
		ContentLinearLayout lockedLine = null;
		CellTextView cell = null;

		if (this.lockRowCount > this.lv_title_row.getChildCount() - 1) {

			lockedLine = createContentLineLayout(SheetArea.Independent,
					lv_independent_title.getChildCount());
			this.lv_independent_title.addView(lockedLine,
					lv_independent_title.getChildCount());

			unlockedLine = createContentLineLayout(SheetArea.Row_title,
					lv_title_row.getChildCount() - 1);
			this.lv_title_row.addView(unlockedLine,
					lv_title_row.getChildCount() - 1);

		} else {

			lockedLine = createContentLineLayout(SheetArea.Colum_title,
					lv_title_colum.getChildCount() - 1);
			this.lv_title_colum.addView(lockedLine,
					lv_title_colum.getChildCount() - 1);

			unlockedLine = createContentLineLayout(SheetArea.Content,
					lv_content.getChildCount() - 1);
			this.lv_content.addView(unlockedLine,
					lv_content.getChildCount() - 1);

		}

		int columCount = sizeManager.getColumCount();

		for (int i = 0; i < this.lockColumCount && i < columCount; ++i) {
			cell = adapter.getCell(this.inflater, this,
					sizeManager.getRowCount() - 1, i,
					tableBlock.getItem(row, i));
			lockedLine.addView(cell);
			updateSize(cell, sizeManager.getRowCount() - 1, i);
		}

		for (int i = this.lockColumCount; i < columCount; ++i) {
			cell = adapter.getCell(inflater, this,
					sizeManager.getRowCount() - 1, i,
					tableBlock.getItem(row, i));
			unlockedLine.addView(cell);
			updateSize(cell, sizeManager.getRowCount() - 1, i);
		}

		LinearLayout llfill = new LinearLayout(this.getContext());
		llfill.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));
		unlockedLine.addView(llfill);
	}

	public void hideRow(int row) {
		if (sizeManager.getRowCount() > row) {
			if (this.lockColumCount > 0 && sizeManager.getColumCount() > 0) {
				((ViewGroup) cell(0, row).getParent()).setVisibility(View.GONE);
			}

			if (sizeManager.getColumCount() > this.lockColumCount) {
				((ViewGroup) cell(this.lockColumCount, row).getParent())
						.setVisibility(View.GONE);
			}

		}
	}

	public void showRow(int row) {
		if (sizeManager.getRowCount() > row) {
			if (this.lockColumCount > 0 && sizeManager.getColumCount() > 0) {
				((ViewGroup) cell(0, row).getParent())
						.setVisibility(View.VISIBLE);
			}

			if (sizeManager.getColumCount() > this.lockColumCount) {
				((ViewGroup) cell(this.lockColumCount, row).getParent())
						.setVisibility(View.VISIBLE);
			}
		}
	}

	public boolean addTable(TableSource tableBlock) {

		int start = sizeManager.getRowCount();

		if (!validate(tableBlock)) {
			return false;
		}

		final Set<Integer> changedColum = new HashSet<Integer>();
		final Set<Integer> changedRow = new HashSet<Integer>();

		sizeManager.registerObserver(new SizeChangeObserver() {

			@Override
			public void onWidthChanged(SizeManager mgr, int colum, int newSize) {
				changedColum.add(colum);
			}

			@Override
			public void onHeightChanged(SizeManager mgr, int row, int newSize) {
				changedRow.add(row);
			}
		});

		for (int i = 0, len = tableBlock.getRowCount(); i < len; ++i) {
			sizeManager.increaseRow(1);
			addRecord(tableBlock, i);
		}

		commit(changedColum, changedRow, start);

		return true;
	}

	public void onScrollFinished(View scroll) {
		if (scroll instanceof AssociationVerticalScrollView) {
			int scrollY = (int) scroll.getScrollY();
			if (scrollY > 0) {
				int sumHeight = 0;
				int cellHeight = 0;

				if (scrollY > getContentHeight()) {
					scroll.scrollTo(
							scroll.getScrollX(),
							getContentHeight()
									- sizeManager.getHeight(sizeManager
											.getRowCount() - 1));
				} else {
					for (int i = this.lockRowCount, rowCount = sizeManager
							.getRowCount(); i < rowCount; ++i) {
						cellHeight = sizeManager.getHeight(i);
						sumHeight += cellHeight;
						if (sumHeight > scrollY) {
							if (cellHeight / 2 < (sumHeight - scrollY)
									|| (i + 1) == sizeManager.getRowCount()) {
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
					scroll.scrollTo(
							getContentWidth()
									- sizeManager.getWidth(sizeManager
											.getColumCount() - 1),
							scroll.getScrollY());
				} else {
					for (int i = this.lockColumCount, columCount = sizeManager
							.getColumCount(); i < columCount; ++i) {
						cellWidth = sizeManager.getWidth(i);
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
