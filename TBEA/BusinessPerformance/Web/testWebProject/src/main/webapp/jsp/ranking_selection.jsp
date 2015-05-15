<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<table id="ranking_selection"  cellspacing="0" cellpadding="0">
	<tr>
	
		<td style="padding-right:5px">
		<select id="indextype"	onchange="instance.onIndexSelected()" style="width: 200px;">
				<option value="1" selected="selected">利润计划完成率排名</option>
				<option value="2" >利润指标完成同比增长排名</option>
				<option value="3" >经营性净现金流实际完成排名</option>

		</select>


	</tr>
</table>


