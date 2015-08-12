<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<table id="index_selection"  cellspacing="0" cellpadding="0">
	<tr>	
		<td style="padding-right:5px">
		<select id="companytype"	onchange="instance.onCompanysSelected()" style="width: 170px;">
				<option value="1" selected="selected">全部经营单位</option>
				<option value="2" >输变电产业集团项目公司</option>
				<option value="3" >能源产业集团项目公司</option>
				<option value="4" >新能源产业集团项目公司</option>
		</select>
	</tr>
</table>


