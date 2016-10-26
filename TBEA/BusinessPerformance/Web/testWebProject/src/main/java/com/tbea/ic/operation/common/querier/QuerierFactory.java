package com.tbea.ic.operation.common.querier;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;

public class QuerierFactory {
	
	static ZBStatusQuerier zlApproveQuerier;
	static ZBStatusQuerier zlEntryQuerier;
	static {
		zlApproveQuerier = new ZBStatusQuerier();
		zlApproveQuerier.add(53, ZBStatus.SUBMITTED.ordinal());
		zlApproveQuerier.add(53, ZBStatus.INTER_APPROVED_1.ordinal());
		zlApproveQuerier.add(53, ZBStatus.INTER_APPROVED_2.ordinal());
		zlApproveQuerier.add(53, ZBStatus.INTER_APPROVED_3.ordinal());
		zlApproveQuerier.add(53, ZBStatus.APPROVED.ordinal());
		
		zlApproveQuerier.add(54, ZBStatus.INTER_APPROVED_1.ordinal());
		zlApproveQuerier.add(54, ZBStatus.INTER_APPROVED_2.ordinal());
		zlApproveQuerier.add(54, ZBStatus.INTER_APPROVED_3.ordinal());
		zlApproveQuerier.add(54, ZBStatus.APPROVED.ordinal());
		
		zlApproveQuerier.add(55, ZBStatus.INTER_APPROVED_2.ordinal());
		zlApproveQuerier.add(55, ZBStatus.INTER_APPROVED_3.ordinal());
		zlApproveQuerier.add(55, ZBStatus.APPROVED.ordinal());
		
		zlApproveQuerier.add(AuthType.QualityApprove.ordinal(), ZBStatus.INTER_APPROVED_3.ordinal());
		zlApproveQuerier.add(AuthType.QualityApprove.ordinal(), ZBStatus.APPROVED.ordinal());
	}
	
	static {
		zlEntryQuerier = new ZBStatusQuerier();
		zlEntryQuerier.add(AuthType.QualityEntry.ordinal(), ZBStatus.SAVED.ordinal());
		zlEntryQuerier.add(AuthType.QualityEntry.ordinal(), ZBStatus.SUBMITTED.ordinal());
		zlEntryQuerier.add(AuthType.QualityEntry.ordinal(), ZBStatus.INTER_APPROVED_1.ordinal());
		zlEntryQuerier.add(AuthType.QualityEntry.ordinal(), ZBStatus.INTER_APPROVED_2.ordinal());
		zlEntryQuerier.add(AuthType.QualityEntry.ordinal(), ZBStatus.INTER_APPROVED_3.ordinal());
		zlEntryQuerier.add(AuthType.QualityEntry.ordinal(), ZBStatus.APPROVED.ordinal());
	}
	
	
	public static ZBStatusQuerier createZlApproveQuerier(){
		return zlApproveQuerier;
	}
	
	public static ZBStatusQuerier createZlEntryQuerier(){
		return zlEntryQuerier;
	}
}
