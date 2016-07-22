<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<table id="index_selection"  cellspacing="0" cellpadding="0">
	<tr>
	
		<td style="padding-right:5px">
		<select id="indextype"	onchange="instance.onIndexSelected()" style="width: 125px;">
				<option value="2" selected="selected">20号指标</option>
				<option value="3" >28号指标</option>
				<option value="4" >实际指标</option>
				<option value="0" >年度计划指标</option>
				<option value="1" >月度计划指标</option>
		</select>


	</tr>
</table>


