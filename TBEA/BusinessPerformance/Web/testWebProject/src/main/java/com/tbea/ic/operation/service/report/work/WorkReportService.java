package com.tbea.ic.operation.service.report.work;

import java.sql.Date;
import java.util.List;


public interface WorkReportService {

	List<Double[]> getJydwztzb(Integer compId, Date date);

	List<Double[]> getLrsrzb(Integer compId, Date date);

	List<List<String>> getJyxxjl(Integer compId, Date date);

	List<String> getJyxxjlYdsj(Integer compId, Date date);

	List<List<String>> getYszkzmb(Integer compId, Date date);

	List<List<String>> getYszkzlbhDy(Integer compId, Date date);

	List<List<String>> getYszkYqysDy(Integer compId, Date date);

	List<List<String>> getYszkYjtzDy(Integer compId, Date date);

	List<List<String>> getYszkKxxzDy(Integer compId, Date date);

	List<List<String>> getChzmb(Integer compId, Date date);

	List<List<String>> getChxzDy(Integer compId, Date date);

	List<List<String>> getChjykc(Integer compId, Date date);

	List<List<String>> getChzljgDy(Integer compId, Date date);

	List<Double[]> getStandardZb(Integer compId, Date date, List<Integer> zbs);

	List<List<String>> getXfscqy(Integer compId, Date date);

	List<List<String>> getXlXfcpqy(Integer compId, Date date);

	List<List<String>> getByqXfcpqy(Integer compId, Date date);

	List<List<String>> getByqCzwcqk(Integer compId, Date date);

	List<List<String>> getXlCzwcqk(Integer compId, Date date);

	List<List<String>> getXlTlyl(Integer compId, Date date);

	List<List<String>> getXlWgcpylnlMll(Integer compId, Date date);

	List<List<String>> getByqWgcpylnlMll(Integer compId, Date date);

	List<List<String>> getXlWlyddmlspcs(Integer compId, Date date);

	List<List<String>> getByqWlyddmlspcs(Integer compId, Date date);

	List<List<String>> getXlKglyddcplbkj(Integer compId, Date date);

	List<List<String>> getXlKglyddscdykj(Integer compId, Date date);

	List<List<String>> getByqKglyddcplbkj(Integer compId, Date date);

	List<List<String>> getByqKglyddscdykj(Integer compId, Date date);

	List<List<String>> getYclbfqk(Integer compId, Date date);

	List<List<String>> getByqClwcqk(Integer compId, Date date);
}
