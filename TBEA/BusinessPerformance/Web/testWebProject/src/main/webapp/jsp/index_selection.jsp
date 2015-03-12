<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<table id="index_selection"  cellspacing="0" cellpadding="0">
	<tr>
	
		<td style="padding-right:5px">
		<select id="indextype"	onchange="instance.onIndexSelected()" style="width: 125px;">
				<option value="1" selected="selected">利润总额</option>
				<option value="6" >销售收入</option>
				<option value="29" >经营性净现金流</option>
				<option value="32" >应收账款</option>
				<option value="35" >存货</option>

		</select>


	</tr>
</table>


