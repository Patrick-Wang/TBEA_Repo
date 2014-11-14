<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <link rel="stylesheet" type="text/css" media="screen"
          href="../jsp/jqgrid/themes/ui.jqgrid.css">
    <link rel="stylesheet" type="text/css" media="screen"
          href="../jsp/jqgrid/themes/ui.multiselect.css">
    <script src="../jsp/jqgrid/js/jquery.js" type="text/javascript"></script>

    <script src="../jsp/jqgrid/js/jquery.layout.js" type="text/javascript"></script>
    <script src="../jsp/jqgrid/js/i18n/grid.locale-en.js" type="text/javascript"></script>


    <script src="../jsp/jqgrid/js/jquery.jqGrid.js" type="text/javascript"></script>
    <script src="../jsp/jqgrid/js/jquery.tablednd.js" type="text/javascript"></script>
    <script src="../jsp/jqgrid/js/jquery.contextmenu.js" type="text/javascript"></script>
    <script src="../jsp/jqgrid/vector.js" type="text/javascript"></script>
    <script src="../jsp/jqgrid/jqassist.js" type="text/javascript"></script>

    <link rel="stylesheet" type="text/css" media="screen"
          href="../jsp/jqgrid/themes/redmond/jquery-ui-custom.css">
    <script src="../jsp/jqgrid/js/jquery-ui-custom.min.js" type="text/javascript"></script>
    <script src="../jsp/jqgrid/js/ui.multiselect.js" type="text/javascript"></script>
 	<script src="../jsp/util.js" type="text/javascript"></script>
    <script src="../jsp/gcy_zbhz.js" type="text/javascript"></script>

    <script type="text/javascript">
        (function () {
            $(document).ready(function () {
                gcy_zbhz.View.newInstance().init("table", ${month}, ${year});
            });
        })();
    </script>
    <meta charset="UTF-8">

    <title>${year}年${month}月 各产业指标汇总</title>

    <style type="text/css">
        body {
            background-color: rgb(247, 247, 247);
        }

        .panel-content-border {
            height: 350px;
            width: 1000px;
            border: 2px solid #e3e3e3;
            margin: 0;
            padding: 0;
            align: center;
            valign: center;
            text-align: center;
        }

        .panel-content {
            height: 100%;
            width: 100%;
            margin: 0;
            padding: 0;
        }

        .right {
            width: 45%;
            height: 180px;
            float: left;
            padding-top: 20px;
            margin-left: 225px;
        }

        .contract {
            text-align: center;
        }

            .contract h1 {
                display: none;
                color: #003B8F;
            }

        .btn_loading, .btn_detail {
            width: 100px;
            height: 30px;
            padding: 5px, 10px;
            font-size: 12px;
            line-height: 1.5;
            boder-radius: 3px;
            background-color: #5cb85c;
            boder-color: #4cae4c;
            color: #fff;
        }

        .header {
            width: 100%;
            height: 60px;
        }

            .header h1 {
                text-align: center;
            }

        .companyname h1 {
            width: 30px;
            font-size: 30px;
            word-wrap: break-word;
            letter-spacing: 20px;
            color: #5cb85c;
            float: left;
        }

        .lxian {
            margin-left: 30px;
            width: 1px;
            height: 175px;
            background: #5cb85c;
            float: left;
        }

        .hrclass hr {
            width: 1100px;
            height: 1px;
            margin-top: 10px;
            margin-left: 90px;
            border: 0;
            background-color: #5cb85c;
        }

        th.ui-th-column div {
            /* jqGrid columns name wrap  */
            white-space: normal !important;
            height: auto !important;
            padding: 0px;
        }

        th.ui-th-ltr {
            /* jqGrid columns name wrap  */
            font-size: 14px;
        }
    </style>
</head>
<body style="width:1400px">
    <div class=" header">
        <h1>${year}年${month}月 各产业指标汇总</h1>
    </div>
	<script type="text/javascript">
		function onYearChange(year){
			if (year == ${year}){
				$("#month_" + ${year}).css("display", "");
				$("#month_" + ${year - 1}).css("display", "none");
			} else{
				$("#month_" + ${year}).css("display", "none");
				$("#month_" + ${year - 1}).css("display", "");
			}
			;
			gcy_zbhz.View.newInstance().onYearSelected(year);
			gcy_zbhz.View.newInstance().onMonthSelected($("#month_" + year + " option:selected").val());
		}
	</script>
	<table>
		<tr>
			<td>
				<select id= "year"
					onchange="onYearChange(this.value)" style="width: 125px;">
					<option value="${year}" selected="selected">${year}年</option>
					<option value="${year - 1}">${year - 1}年</option>
				</select> 
			</td>
			<td>
				<select id= "month_${year}"
					onchange="gcy_zbhz.View.newInstance().onMonthSelected(this.value)" style="width: 125px;">
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
				<select id= "month_${year - 1}"
					onchange="gcy_zbhz.View.newInstance().onMonthSelected(this.value)" style="width: 125px;display:none">
					<c:forEach begin="0" end="11" var="i">
							<c:choose>
	
							   <c:when test="${i == (month - 1)}">
									<option value="${i + 1}" selected="selected">${month}月</option>
							   </c:when>
							   
							   <c:otherwise>
									<option value="${i + 1}">${i + 1}月</option>
							   </c:otherwise>
							  
							</c:choose>
						
					</c:forEach>
					
				</select> 
			</td>
			<td>
				<input type="button" value="更新" onclick="gcy_zbhz.View.newInstance().updateUI()"></input>
			</td>
		</tr>
	</table>
	<div align="center" id="table">
    </div>
    
	<%@include file="loading.jsp" %>

</body>
   <script src="../jsp/www2/js/echarts-plain-2-0-0.js"></script>

</html>