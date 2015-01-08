<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<c:if test="${!(empty day)}">
<script type="text/javascript">
	function changeDay(month){
			if ($('#day') != undefined){
				var year = parseInt($("#year" + " option:selected").val());
				var dayCount = getDaysInMonth(year, month);
				if (month == ${month}){
					dayCount = ${dayCount};
				}
				var selectedOpt = parseInt($("#day" + " option:selected").val());
				if (dayCount < selectedOpt){
					selectedOpt = dayCount;
					instance.onDaySelected(dayCount);
				}

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
</c:if>

<c:if test="${(empty day)}">
<script type="text/javascript">
	function changeDay(month){
			
	}
	</script>
</c:if>

<c:if test="${!(empty month)}">
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
			
			instance.onYearSelected(parseInt(year + ""));
			onMonthChanged(selectedOpt);
		}

		function getDaysInMonth(year,month){ 
			month = parseInt(month,10); //parseInt(number,type) //此函数后面如果不跟第2个参数来表示进制，默认是10进制。 
			var temp = new Date(year,month,0); 
			return temp.getDate(); 
		}
		
	
		function onMonthChanged(month){
			instance.onMonthSelected(parseInt(month + ""));
			changeDay(month);
		}
	</script>
</c:if>



<table id="date_selection"  cellspacing="0" cellpadding="0">
	<tr>
		<c:choose>

			<c:when test="${empty month}">
				<td style="padding-right:5px"><select id="year"
					onchange="instance.onYearSelected(this.value)"
					style="width: 125px;">
						<option value="${year}" selected="selected">${year}年</option>
						<option value="${year - 1}">${year - 1}年</option>
				</select></td>
			</c:when>

			<c:otherwise>
				<td style="padding-right:5px"><select id="year" onchange="onYearChange(this.value)"
					style="width: 125px;">
						<option value="${year}" selected="selected">${year}年</option>
						<option value="${year - 1}">${year - 1}年</option>
				</select></td>
				<td style="padding-right:5px"><select id="month" onchange="onMonthChanged(this.value)"
					style="width: 125px;">
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

				</select></td>
			</c:otherwise>

		</c:choose>

		<c:if test="${!(empty day)}">
			<td style="padding-right:5px"><select id="day"
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

			</select></td>
		</c:if>
	</tr>
</table>
<script type="text/javascript">
	instance.onYearSelected(${year});

</script>
<c:if test="${!(empty month)}">
	<script type="text/javascript">
	onYearChange(${year});

</script>
</c:if>
