package com.tbea.ic.operation.service.approve;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.ZBStatus;

public class ZBDispatcher {
	List<String[]> approveList = new ArrayList<String[]>();
	List<String[]> unapproveList = new ArrayList<String[]>();
	int zbArrSize;
	public ZBDispatcher(int zbArrSize){
		this.zbArrSize = zbArrSize;
	}
	
	public String[] createZbArray(){
		return new String[this.zbArrSize];
	}
	
	public void dispatch(ZBStatus status, String[] zbArr){
		switch (status) {
		case APPROVED:
			unapproveList.add(zbArr);
			break;
		case APPROVED_2:
		case SUBMITTED:
		case SUBMITTED_2:
			approveList.add(zbArr);
			break;
		default:
			break;
		}
	}
	
	public List<List<String[]>> getResult(){
		List<List<String[]>> retList = new ArrayList<List<String[]>>();
		retList.add(approveList);
		retList.add(unapproveList);
		return retList;
	}
}
