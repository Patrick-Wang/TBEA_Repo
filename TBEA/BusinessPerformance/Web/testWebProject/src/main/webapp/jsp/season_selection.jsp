<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript">
	instance.onYearSelected(${year});
	instance.onSeasonChange(1);
	instance.onMonthDelegateSelected(1);
</script>



<table id="season_selection"  cellspacing="0" cellpadding="0">
	<tr>
	
		<td style="padding-right:5px">
		<select id="year"	onchange="instance.onyearChange(this.value) style="width: 125px;">
				<option value="2014" >2014</option>
				<option value="2015" selected="selected">2015</option>

		</select>

		<td style="padding-right:5px">
		<select id="season"	onchange="instance.onSeasonChange(this.value) style="width: 125px;">
				<option value="1" selected="selected">第一季度</option>
				<option value="2">第二季度</option>
				<option value="3">第三季度</option>
				<option value="4">第四季度</option>
		</select>
		</td>

		<td style="padding-right:5px">
		<select id="month" onchange="instance.onMonthDelegateSelected(this.value)"style="width: 125px;">
				<option value="1" selected="selected">首月</option>
				<option value="2">次月</option>
				<option value="3">末月</option>
		</select>
		</td>

	</tr>
</table>


