package com.tbea.ic.operation.service.util.pipe.filter.composite;

import java.util.ArrayList;
import java.util.List;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;


class RowPointer{
	int base;
	int offset;
	
	public RowPointer(int base, int offset) {
		super();
		this.base = base;
		this.offset = offset;
	}
	public int getBase() {
		return base;
	}
	public int getOffset() {
		return offset;
	}
}

public abstract class AbstractPipeFilter implements IPipeFilter {
	protected List<RowPointer> rowPtrs = new ArrayList<RowPointer>();
	
	public AbstractPipeFilter includeRow(int rowStart, int step){
		rowPtrs.add(new RowPointer(rowStart, step));
		return this;
	}

	protected boolean contains(int r){
		for(RowPointer ptr : rowPtrs){
			if (ptr.base <= r && (r - ptr.base) % ptr.offset == 0){
				return true;
			}
		}
		return false;
	}
	
	protected int getBase(int prtRef){
		return rowPtrs.get(prtRef).base;
	}
	
	protected int getStep(int prtRef){
		return rowPtrs.get(prtRef).offset;
	}
	
	protected int rowInner2Outer(int innerRow, int ptrRef){
		RowPointer ptr = rowPtrs.get(ptrRef);
		return innerRow * ptr.offset + ptr.base;
	}
	
	protected int rowOuter2Inner(int outerRow, int ptrRef){
		RowPointer ptr = rowPtrs.get(ptrRef);
		return (outerRow - ptr.base) / ptr.offset;
	}
}
