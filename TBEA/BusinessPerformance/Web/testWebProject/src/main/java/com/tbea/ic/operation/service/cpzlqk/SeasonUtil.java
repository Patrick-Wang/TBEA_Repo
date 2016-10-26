package com.tbea.ic.operation.service.cpzlqk;

import com.tbea.ic.operation.common.EasyCalendar;

public class SeasonUtil {
	public static EasyCalendar getStart(EasyCalendar ec){
		if (ec.getSeason() == 3){
			return ec.getMonths(7);
		}
		return ec.getMonths(1);
	}
	
	public static EasyCalendar getEnd(EasyCalendar ec){
		return ec.getSeasonEnd();
	}
}
