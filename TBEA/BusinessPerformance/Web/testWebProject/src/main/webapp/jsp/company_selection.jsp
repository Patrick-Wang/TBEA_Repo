<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${onlytop}">
	<script type="text/javascript">
	function onComp_categorySelected(sel, val){
		instance.onCompanySelected(val);
	}
	</script>
</c:if>
<c:if test="${both}">
	<script type="text/javascript">
			
		function onComp_categorySelected(sel, val){
			
			var subCompId;
			var len = ${fn:length(topComp[0])};
			for (var i = 0; i < len; ++i){
				subCompId = "subcomp" + i;
				$("#comp_selection #" + subCompId).css("display", "none");	
				if (sel.options[i].value == val){
					$('#comp_selection #subcomp' + i).css("display", "");	
					var curSel = $('#comp_selection #subcomp' + i + " option:selected").val();
					instance.onCompanySelected(curSel);
				}
			}
		}
		
	</script>
</c:if>
<table id="comp_selection">
	<tr>

		<td><select id="comp_category"
			onchange="onComp_categorySelected(this, this.value)" style="width: 125px;">
				<c:forEach begin="0" end="${fn:length(topComp[0]) - 1}" var="i">
					<c:choose>

						<c:when test="${i == 0}">
							<option value="${topComp[1][i]}" selected="selected">${topComp[0][i]}</option>
						</c:when>

						<c:otherwise>
							<option value="${topComp[1][i]}">${topComp[0][i]}</option>
						</c:otherwise>

					</c:choose>
				</c:forEach>

		</select></td>

		<c:if test="${both}">
			<td><c:forEach begin="0" end="${fn:length(topComp[0]) - 1}"
					var="i">
					<c:choose>

						<c:when test="${i == 0}">
							<select id="subcomp${i}"
								onchange="instance.onCompanySelected(this.value)"
								style="width: 125px;">
						</c:when>

						<c:otherwise>
							<select id="subcomp${i}"
								onchange="instance.onCompanySelected(this.value})"
								style="width: 125px; display: none;">
						</c:otherwise>


					</c:choose>
					
					<c:forEach begin="0" end="${fn:length(subComp[i][0]) - ((null != emptyComp[topComp[1][i]]) ? 1 : 0)}" var="j">
						
								<c:choose>
									<c:when test="${j == fn:length(subComp[i][0])}">
										<option value="${topComp[1][i]}" selected="selected">全部</option>
									</c:when>
									<c:otherwise>
										<option value="${subComp[i][1][j]}">${subComp[i][0][j]}</option>
									</c:otherwise>
								</c:choose>
				
					</c:forEach>

					</select>

				</c:forEach></td>
		</c:if>
	</tr>
</table>

<c:if test="${both}">
	<script type="text/javascript">
		onComp_categorySelected($("#comp_category")[0], $("#comp_category")[0].value);
	</script>
</c:if>
