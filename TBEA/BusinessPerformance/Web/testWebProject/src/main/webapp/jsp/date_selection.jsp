<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
		function onYearChange(year){
			var monthCount;
			var curSelected = parseInt($("#month" + " option:selected").val());
			var selectedOpt;
			if (year == ${year}){
				monthCount =  ${month};
				selectedOpt = curSelected > ${month} ? ${month} : curSelected;
			} else{
				monthCount =  12;
				selectedOpt = curSelected;
			}
			
			var select = $("#month");
			select.empty();
			for (var i = 0; i < monthCount; ++i){
				if (i + 1 == selectedOpt){
					select.append("<option value='" + (i + 1) + "' selected='selected'>" + (i +1) + "月</option>");
				}
				else{
					select.append("<option value='" + (i + 1) + "'>" + (i +1) + "月</option>");
				}
			}
			
			instance.onYearSelected(year);
			onMonthSelected(selectedOpt);
		}
		
		
		function getDaysInMonth(year,month){ 
			month = parseInt(month,10); //parseInt(number,type) //此函数后面如果不跟第2个参数来表示进制，默认是10进制。 
			var temp = new Date(year,month,0); 
			return temp.getDate(); 
		}
		
	
		function onMonthSelected(month){
			instance.onMonthSelected(month);
			if ($('#day') != undefined){
				var year = parseInt($("#year" + " option:selected").val());
				var dayCount = getDaysInMonth(year, month);
				var curDaySelected = parseInt($("#day" + " option:selected").val());
				var selectedOpt = dayCount > curDaySelected ? curDaySelected : dayCount;

				var select = $("#day");
				select.empty();
				for (var i = 0; i < dayCount; ++i){
					if (i + 1 == selectedOpt){
						select.append("<option value='" + (i + 1) + "' selected='selected'>" + (i +1) + "日</option>");
					}
					else{
						select.append("<option value='" + (i + 1) + "'>" + (i +1) + "日</option>");
					}
				}
			}
		}
	</script>
<table id="date_selection">
	<tr>
		<td><select id="year" onchange="onYearChange(this.value)"
			style="width: 125px;">
				<option value="${year}" selected="selected">${year}年</option>
				<option value="${year - 1}">${year - 1}年</option>
		</select></td>
		<td><select id="month"
			onchange="onMonthSelected(this.value)" style="width: 125px;">
				<c:forEach begin="0" end="${month - 1}" var="i">
					<c:choose>

						<c:when test="${i == (month - 1)}">
							<option value="${month}" selected="selected">${month}月</option>
						</c:when>

						<c:otherwise>
							<option value="${i + 1}">${i + 1}月</option>
						</c:otherwise>

					</c:choose>

				</c:forEach>

		</select>
		
		</td>
		<c:if test="${!(empty day)}">
			<td><select id="day"
				onchange="instance.onDaySelected(this.value)" style="width: 125px;">
				<c:forEach begin="0" end="${dayCount - 1}" var="i">
					<c:choose>
						<c:when test="${i == (day - 1)}">
							<option value="${i + 1}" selected="selected">${i + 1}日</option>
						</c:when>

						<c:otherwise>
							<option value="${i + 1}">${i + 1}日</option>
						</c:otherwise>

					</c:choose>

				</c:forEach>

		</select>
		
		</td>
		</c:if>
		<td><input type="button" value="更新" onclick="instance.updateUI()"></input>
		</td>
	</tr>
</table>
